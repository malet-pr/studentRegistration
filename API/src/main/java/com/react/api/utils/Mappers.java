package com.react.api.utils;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.react.api.dto.CourseCompleteDTO;
import com.react.api.dto.CourseDTO;
import com.react.api.dto.ProfessorCompleteDTO;
import com.react.api.dto.ProfessorDTO;
import com.react.api.dto.StudentCompleteDTO;
import com.react.api.dto.StudentDTO;
import com.react.api.dto.SubjectCompleteDTO;
import com.react.api.dto.SubjectDTO;
import com.react.api.model.Course;
import com.react.api.model.Professor;
import com.react.api.model.Student;
import com.react.api.model.Subject;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {MapLists.class})
public interface Mappers {

    ProfessorDTO professorToProfessorDTO(Professor entity);

    Professor professorDTOToProfessor(ProfessorDTO dto);

    CourseDTO courseToCourseDTO(Course entity);
    
    Course courseDTOToCourse(CourseDTO dto);

    StudentDTO studentToStudentDTO(Student entity);

    Student studentDTOToStudent(StudentDTO dto);

    SubjectDTO subjectToSubjectDTO(Subject entity);

    Subject subjectDTOToSubject(SubjectDTO dto);

    ProfessorCompleteDTO professorToProfessorCompleteDTO(Professor entity);

    Professor professorCompleteDTOToProfessor(ProfessorCompleteDTO dto);

    CourseCompleteDTO courseToCourseCompleteDTO(Course entity);
    
    Course courseCompleteDTOToCourse(CourseCompleteDTO dto);

    StudentCompleteDTO studentToStudentCompleteDTO(Student entity);

    Student studentCompleteDTOToStudent(StudentCompleteDTO dto);

    SubjectCompleteDTO subjectToSubjectCompleteDTO(Subject entity);

    Subject subjectCompleteDTOToSubject(SubjectCompleteDTO dto);
    
    
}
