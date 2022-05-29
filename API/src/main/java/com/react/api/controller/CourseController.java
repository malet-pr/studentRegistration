package com.react.api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import com.react.api.service.CourseService;
import com.react.api.service.ProfessorService;
import com.react.api.service.StudentService;
import com.react.api.service.SubjectService;
import com.react.api.utils.MapLists;
import com.react.api.utils.Mappers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.react.api.dto.CourseCompleteDTO;
import com.react.api.dto.CourseDTO;
import com.react.api.dto.ProfessorDTO;
import com.react.api.dto.StudentDTO;
import com.react.api.dto.SubjectDTO;
import com.react.api.exceptions.custom.BadRequestException;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Course;
import com.react.api.model.Professor;
import com.react.api.model.Student;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private Mappers mappers;

    @Autowired
    private MapLists mapLists;

    @GetMapping("/{id}")
    public ResponseEntity<CourseCompleteDTO> getCourseById(@PathVariable Long id)
            throws BadRequestException {
        Course entity = courseService.getById(id);
        if (entity != null) {
            CourseCompleteDTO course = mappers.courseToCourseCompleteDTO(entity);
            course.setProfessorNames(mapLists.professorsToProfessorsNames(entity.getProfessors()));
            course.setSubjectName(entity.getSubject().getName());
            course.setAvailableSeats(entity.getCapacity() - entity.getStudents().size());
            return new ResponseEntity<CourseCompleteDTO>(course, HttpStatus.OK);
        } else {
            throw new BadRequestException("No se encontró ese curso");
        }
    }

    @GetMapping("/coursesAdmin")
    public ResponseEntity<List<CourseCompleteDTO>> getCoursesListA() {
        List<Course> list = courseService.getAll();
        List<CourseCompleteDTO> courses = new ArrayList<>();
        for (Course c : list) {
            CourseCompleteDTO cc = mappers.courseToCourseCompleteDTO(c);
            cc.setProfessorNames(mapLists.professorsToProfessorsNames(c.getProfessors()));
            cc.setSubjectName(c.getSubject().getName());
            cc.setAvailableSeats(c.getCapacity() - c.getStudents().size());
            courses.add(cc);
        }
        return new ResponseEntity<List<CourseCompleteDTO>>(courses, HttpStatus.OK);
    }

    @GetMapping("/coursesStudent")
    public ResponseEntity<List<CourseCompleteDTO>> getCoursesListS() {
        List<Course> list = courseService.getAvailable();
        List<CourseCompleteDTO> courses = new ArrayList<>();
        for (Course c : list) {
            CourseCompleteDTO cc = mappers.courseToCourseCompleteDTO(c);
            cc.setProfessorNames(mapLists.professorsToProfessorsNames(c.getProfessors()));
            cc.setSubjectName(c.getSubject().getName());
            cc.setAvailableSeats(c.getCapacity() - c.getStudents().size());
            courses.add(cc);
        }
        return new ResponseEntity<List<CourseCompleteDTO>>(courses, HttpStatus.OK);
    }

    @PostMapping(value = "/addCourse")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDto) throws BadRequest {
        courseService.save(mappers.courseDTOToCourse(courseDto));
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id) throws NotFoundException {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/editCourse/{id}")
    public ResponseEntity<CourseDTO> editCourse(@PathVariable Long id, @RequestBody CourseDTO courseDto)
            throws BadRequest, NotFoundException {
        Course course = courseService.editCourse(id, mappers.courseDTOToCourse(courseDto));
        CourseDTO dto = mappers.courseToCourseDTO(course);
        return new ResponseEntity<CourseDTO>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/professors")
    public ResponseEntity<List<ProfessorDTO>> professorList(@PathVariable Long id) throws NotFoundException {
        Course course = courseService.getById(id);
        List<Professor> pr = new ArrayList<>(course.getProfessors());
        List<ProfessorDTO> professors = pr.stream().map(p -> mappers.professorToProfessorDTO(p))
                .collect(Collectors.toList());
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping("/{id}/subject")
    public ResponseEntity<SubjectDTO> subject(@PathVariable Long id) throws NotFoundException {
        Course course = courseService.getById(id);
        SubjectDTO subject = mappers.subjectToSubjectDTO(course.getSubject());
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> studentList(@PathVariable Long id) throws NotFoundException {
        Course course = courseService.getById(id);
        List<Student> st = new ArrayList<>(course.getStudents());
        List<StudentDTO> students = st.stream().map(s -> mappers.studentToStudentDTO(s)).collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/addStudent/{courseId}/{studentId}")
    public ResponseEntity<StudentDTO> addStudent(@PathVariable Long courseId, @PathVariable Long studentId)
            throws NotFoundException {
        courseService.addStudent(courseId, studentId);
        StudentDTO student = mappers.studentToStudentDTO(studentService.getById(studentId));
        return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);

    }

    @PostMapping("/addProfessor/{courseId}/{professorId}")
    public ResponseEntity<ProfessorDTO> addProfessor(@PathVariable Long courseId, @PathVariable Long professorId)
            throws NotFoundException {
        courseService.addProfessor(courseId, professorId);
        ProfessorDTO professor = mappers.professorToProfessorDTO(professorService.getById(professorId));
        return new ResponseEntity<ProfessorDTO>(professor, HttpStatus.OK);
    }

    @PostMapping("/addOrChangeSubject/{courseId}/{subjectId}")
    public ResponseEntity<SubjectDTO> addSubject(@PathVariable Long courseId, @PathVariable Long subjectId)
            throws NotFoundException {
        courseService.addOrChangeSubject(courseId, subjectId);
        SubjectDTO subject = mappers.subjectToSubjectDTO(subjectService.getById(subjectId));
        return new ResponseEntity<SubjectDTO>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/removeStudent/{courseId}/{studentId}")
    public ResponseEntity removeStudent(@PathVariable Long courseId, @PathVariable Long studentId)
            throws NotFoundException {
        courseService.removeStudent(courseId, studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removeProfessor/{courseId}/{professorId}")
    public ResponseEntity removeProfessor(@PathVariable Long courseId, @PathVariable Long professorId)
            throws NotFoundException {
        courseService.removeProfessor(courseId, professorId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removeSubject/{courseId}/{subjectId}")
    public ResponseEntity removeSubject(@PathVariable Long courseId, @PathVariable Long subjectId)
            throws NotFoundException {
        courseService.removeSubject(courseId, subjectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}