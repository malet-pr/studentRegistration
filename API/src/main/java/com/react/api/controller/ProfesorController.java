package com.react.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import com.react.api.dto.CourseDTO;
import com.react.api.dto.ProfessorDTO;
import com.react.api.dto.SubjectDTO;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Course;
import com.react.api.model.Professor;
import com.react.api.model.Subject;
import com.react.api.service.ProfessorService;
import com.react.api.service.SubjectService;
import com.react.api.utils.Mappers;

@RestController
@RequestMapping("/api/v1/professor")
public class ProfesorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private Mappers mappers;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long id) {
        ProfessorDTO professor = mappers.professorToProfessorDTO(professorService.getById(id));
        return new ResponseEntity<ProfessorDTO>(professor, HttpStatus.OK);
    }

    @GetMapping("/professors")
    public ResponseEntity<List<ProfessorDTO>> getProfessorListAll() {
        List<Professor> pr = new ArrayList<>(professorService.getAll());
        List<ProfessorDTO> professors = pr.stream().map(p -> mappers.professorToProfessorDTO(p))
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
    }

    @GetMapping("/professorsByLastName")
    public ResponseEntity<List<ProfessorDTO>> getProfessorListByLastName(@RequestParam String term) {
        List<Professor> pr = new ArrayList<>(professorService.findByLastNameLikeIgnoreCase(term));
        List<ProfessorDTO> professors = pr.stream().map(p -> mappers.professorToProfessorDTO(p))
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
    }

    @GetMapping("/activeProfessorsByLastName")
    public ResponseEntity<List<ProfessorDTO>> getProfessorListByNameAndActive(@RequestParam String term) {
        List<Professor> pr = new ArrayList<>(professorService.findByLastNameLikeIgnoreCaseAndIsActive(term));
        List<ProfessorDTO> professors = pr.stream().map(p -> mappers.professorToProfessorDTO(p))
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
    }

    @GetMapping("/activeProfessors")
    public ResponseEntity<List<ProfessorDTO>> getProfessorListActive() {
        List<Professor> pr = new ArrayList<>(professorService.getAllActive());
        List<ProfessorDTO> professors = pr.stream().map(p -> mappers.professorToProfessorDTO(p))
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
    }

    @PostMapping("/addProfessor")
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody ProfessorDTO professorDto) throws BadRequest {
        professorService.save(mappers.professorDTOToProfessor(professorDto));
        return new ResponseEntity<>(professorDto, HttpStatus.CREATED);
    }

    @PatchMapping("/editProfessor/{id}")
    public ResponseEntity<ProfessorDTO> editProfessorById(@PathVariable Long id,
            @RequestBody ProfessorDTO professorDto) throws BadRequest, NotFoundException {
        Professor professor = professorService.editProfessor(mappers.professorDTOToProfessor(professorDto), id);
        ProfessorDTO dto = mappers.professorToProfessorDTO(professor);
        return new ResponseEntity<ProfessorDTO>(dto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProfessor/{id}")
    public ResponseEntity deleteProfessorById(@PathVariable Long id) throws NotFoundException {
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/isActive/{id}")
    public ResponseEntity<Boolean> isActiveById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<Boolean>(professorService.isActive(id), HttpStatus.OK);
    }

    @GetMapping("/toggleIsActive/{id}")
    public ResponseEntity toggleIsActiveById(@PathVariable Long id) throws NotFoundException {
        professorService.toggleIsActive(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/getAllCourses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(@PathVariable Long id) throws NotFoundException {
        Professor professor = professorService.getById(id);
        List<Course> c = new ArrayList<>(professor.getCourses());
        List<CourseDTO> courses = c.stream().map(co -> mappers.courseToCourseDTO(co))
                                .collect(Collectors.toList());
        return new ResponseEntity<List<CourseDTO>>(courses, HttpStatus.OK);
    }

    @PostMapping("/addSubject/{professorId}/{subjectId}")
    public ResponseEntity<SubjectDTO> addSubject(@PathVariable Long professorId, @PathVariable Long subjectId)
            throws NotFoundException {
        professorService.addSubject(professorId, subjectId);
        SubjectDTO dto = mappers.subjectToSubjectDTO(subjectService.getById(subjectId));
        return new ResponseEntity<SubjectDTO>(dto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteSubject/{professorId}/{subjectId}")
    public ResponseEntity deleteSubject(@PathVariable Long professorId, @PathVariable Long subjectId)
            throws NotFoundException {
        professorService.removeSubject(professorId, subjectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/getAllSubjects")
    public ResponseEntity<List<String>> getAllSubjects(@PathVariable Long id) throws NotFoundException {
        Professor professor = professorService.getById(id);
        List<Subject> sb = new ArrayList<>(professor.getSubjects());
        List<String> subjects = sb.stream().map(s -> s.getName()).collect(Collectors.toList());
        return new ResponseEntity<List<String>>(subjects, HttpStatus.OK);
    }

}
