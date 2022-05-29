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
import com.react.api.service.SubjectService;
import com.react.api.utils.Mappers;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    Mappers mappers;

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        SubjectDTO subject = mappers.subjectToSubjectDTO(subjectService.getById(id));
        return new ResponseEntity<SubjectDTO>(subject, HttpStatus.OK);
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDTO>> getSubjectList() {
        List<Subject> sb = new ArrayList<>(subjectService.getAll());
        List<SubjectDTO> subjects = sb.stream().map(s -> mappers.subjectToSubjectDTO(s)).collect(Collectors.toList());
        return new ResponseEntity<List<SubjectDTO>>(subjects, HttpStatus.OK);
    }

    @PostMapping("/addSubject")
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectDTO subjectDto) throws BadRequest {
        subjectService.save(mappers.subjectDTOToSubject(subjectDto));
        return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteSubject/{id}")
    public ResponseEntity deleteSubjectById(@PathVariable Long id) throws NotFoundException {
        subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/editSubject/{id}")
    public ResponseEntity<SubjectDTO> editExistingSubjectById(@PathVariable Long id, @RequestBody SubjectDTO subjectDto)
            throws BadRequest, NotFoundException {
        Subject subject = subjectService.edit(id, mappers.subjectDTOToSubject(subjectDto));
        SubjectDTO dto = mappers.subjectToSubjectDTO(subject);
        return new ResponseEntity<SubjectDTO>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getSubjectByCompleteName")
    public ResponseEntity<SubjectDTO> getSubjectByCompleteName(@RequestParam String name) throws NotFoundException {
        Subject subject = subjectService.getByName(name);
        SubjectDTO dto =  mappers.subjectToSubjectDTO(subject);
        return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findSubjectByPartialName")
    public ResponseEntity<List<SubjectDTO>> getSubjectByPartialName(@RequestParam String term) throws NotFoundException {
        List<Subject> sb = new ArrayList<>(subjectService.findByNameLikeIgnoreCase(term));
        List<SubjectDTO> dtos = sb.stream().map(s -> mappers.subjectToSubjectDTO(s)).collect(Collectors.toList());
        return new ResponseEntity<List<SubjectDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}/listCourses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(@PathVariable Long id) throws NotFoundException {
        Subject subject = subjectService.getById(id);
        List<Course> co = subject.getCourses();
        List<CourseDTO> dtos = co.stream().map(c -> mappers.courseToCourseDTO(c)).collect(Collectors.toList());
        return new ResponseEntity<List<CourseDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}/listProfessors")
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors(@PathVariable Long id) throws NotFoundException {
        Subject subject = subjectService.getById(id);
        List<Professor> pr = subject.getProfessors();
        List<ProfessorDTO> dtos = pr.stream().map(p -> mappers.professorToProfessorDTO(p)).collect(Collectors.toList());
        return new ResponseEntity<List<ProfessorDTO>>(dtos, HttpStatus.OK);
    }
}
