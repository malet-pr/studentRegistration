package com.react.api.utils;

import java.util.List;
import java.util.stream.Collectors;
import com.react.api.model.Course;
import com.react.api.model.Professor;
import com.react.api.model.Student;
import com.react.api.model.Subject;

import org.springframework.stereotype.Component;

@Component
public class MapLists {

    public List<Long> coursesToCoursesIds(List<Course> courses) {
        return courses.stream().map(c -> c.getCourseId()).collect(Collectors.toList());
    }

    public List<Long> studentsToStudentsIds(List<Student> students) {
        return students.stream().map(s -> s.getStudentId()).collect(Collectors.toList());
    }

    public List<Long> professorsToProfessorsIds(List<Professor> professors) {
        return professors.stream().map(s -> s.getProfessorId()).collect(Collectors.toList());
    }

    public List<Long> subjectsToSubjectsIds(List<Subject> subjects) {
        return subjects.stream().map(s -> s.getSubjectId()).collect(Collectors.toList());
    }

    public List<String> coursesToCoursesCodes(List<Course> courses) {
        return courses.stream().map(c -> c.getCode()).collect(Collectors.toList());
    }

    public List<String> studentsToStudentsNames(List<Student> students) {
        return students.stream().map(s -> s.getLastName() + ", " + s.getFirstName()).collect(Collectors.toList());
    }

    public List<String> professorsToProfessorsNames(List<Professor> professors) {
        return professors.stream().map(s -> s.getLastName() + ", " + s.getFirstName()).collect(Collectors.toList());
    }

    public List<String> subjectsToSubjectsNames(List<Subject> subjects) {
        return subjects.stream().map(s -> s.getName()).collect(Collectors.toList());
    }

}
