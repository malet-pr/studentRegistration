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
import com.react.api.dto.StudentDTO;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Course;
import com.react.api.model.Student;
import com.react.api.service.CourseService;
import com.react.api.service.StudentService;
import com.react.api.utils.Mappers;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private Mappers mappers;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = mappers.studentToStudentDTO(studentService.getById(id));
        return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudentList() {
        List<Student> st = new ArrayList<>(studentService.getAll());
        List<StudentDTO> students = st.stream().map(s -> mappers.studentToStudentDTO(s)).collect(Collectors.toList());
        return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDto) throws BadRequest {
        studentService.save(mappers.studentDTOToStudent(studentDto));
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity deleteStudentById(@PathVariable Long id) throws NotFoundException {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/editStudent/{id}")
    public ResponseEntity<StudentDTO> editExistingStudentById(@PathVariable Long id, @RequestBody StudentDTO studentDto)
            throws BadRequest, NotFoundException {
        Student student = studentService.editStudent(mappers.studentDTOToStudent(studentDto), id);
        StudentDTO dto = mappers.studentToStudentDTO(student);
        return new ResponseEntity<StudentDTO>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/getAllCourses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(@PathVariable Long id) throws NotFoundException {
        Student student = studentService.getById(id);
        List<Course> c = new ArrayList<>(student.getCourses());
        List<CourseDTO> courses = c.stream().map(co -> mappers.courseToCourseDTO(co))
                .collect(Collectors.toList());
        return new ResponseEntity<List<CourseDTO>>(courses, HttpStatus.OK);
    }

    @GetMapping("/findByLastName")
    public ResponseEntity<List<StudentDTO>> findStudentsByLastName(@RequestParam String term)
            throws NotFoundException {
        List<Student> st = new ArrayList<>(studentService.findByLastNameLikeIgnoreCase(term));
        List<StudentDTO> students = st.stream().map(s -> mappers.studentToStudentDTO(s))
                .collect(Collectors.toList());
        return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
    }

    @GetMapping("/findByNationalID")
    public ResponseEntity<StudentDTO> findByNationaID(@RequestParam String term)
            throws NotFoundException {
        StudentDTO student = mappers.studentToStudentDTO(studentService.getByNationalID(term));
        return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);
    }

}
