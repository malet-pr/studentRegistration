package com.react.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.react.api.exceptions.custom.BadRequestException;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Course;
import com.react.api.model.Professor;
import com.react.api.model.Student;
import com.react.api.model.Subject;
import com.react.api.repository.CourseRepository;
import com.react.api.repository.ProfessorRepository;
import com.react.api.repository.StudentRepository;
import com.react.api.repository.SubjectRepository;
import com.react.api.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	public CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Course getById(Long id) {
		Course course = courseRepository.findById(id).orElse(null);
		return course;
	}

	@Override
	public List<Course> getAll() {
		return (List<Course>) courseRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
	}

	@Override
	public void save(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		courseRepository.deleteById(id);
	}

	@Override
	public List<Course> getAvailable() {
		List<Course> all = courseRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<Course> available = new ArrayList<Course>();
		for (Course course : all) {
			if (course.getStudents().size() < course.getCapacity()) {
				available.add(course);
			}
		}
		return available;
	}

	@Override
	public Course editCourse(Long id, Course newCourse) throws NotFoundException {
		Course course = courseRepository.getById(id);
		if (newCourse.getCode() != null) {
			course.setCode(newCourse.getCode());
		}
		if (newCourse.getTime() != null) {
			course.setTime(newCourse.getTime());
		}
		if (newCourse.getDay() != null) {
			course.setDay(newCourse.getDay());
		}
		if (newCourse.getCapacity() != 0) {
			course.setCapacity(newCourse.getCapacity());
		}
		return course;
	}

	@Override
	public void addStudent(Long courseId, Long studentId) throws NotFoundException {
		Course course = courseRepository.getById(courseId);
		Student student = studentRepository.getById(studentId);
		if (course.getStudents() == null) {
			course.setStudents(new ArrayList<>());
		}
		int availableSeats = course.getCapacity() - course.getStudents().size();
		if (availableSeats > 0 && student != null) {
			student.getCourses().add(course);
			course.getStudents().add(student);
		} else {
            throw new BadRequestException("No se pudo inscribir al alumno por falta de cupo");
        }

	}

	@Override
	public void addProfessor(Long courseId, Long professorId) throws NotFoundException {
		Course course = courseRepository.getById(courseId);
		Professor professor = professorRepository.getById(professorId);
		if (course.getProfessors() == null) {
			course.setProfessors(new ArrayList<>());
		}
		if (professor != null) {
			if (professor.isActive()) {
				course.getProfessors().add(professor);
				courseRepository.save(course);
			} else {
				throw new BadRequestException(
						"Este profesor no está activo por lo que no puede ser asignado a un curso");
			}
		}
	}

	@Override
	public void addOrChangeSubject(Long courseId, Long subjectId) throws NotFoundException {
		Course course = courseRepository.getById(courseId);
		Subject subject = subjectRepository.getById(subjectId);
		course.setSubject(subject);
		courseRepository.save(course);
	}

	@Override
	public void removeStudent(Long courseId, Long studentId) throws NotFoundException {
		Student student = studentRepository.getById(studentId);
		Course course = courseRepository.getById(courseId);
		if (course != null && student != null) {
			if (course.getStudents().contains(student)) {
				course.getStudents().remove(student);
				courseRepository.save(course);
				student.getCourses().remove(course);
				studentRepository.save(student);
			} else {
				throw new NotFoundException("Ese estudiante no está registrado en el curso");
			}
		}
	}

	@Override
	public void removeProfessor(Long courseId, Long professorId) throws NotFoundException {
		Professor professor = professorRepository.getById(professorId);
		Course course = courseRepository.getById(courseId);
		if (course != null && professor != null) {
			if (course.getProfessors().contains(professor)) {
				course.getProfessors().remove(professor);
				courseRepository.save(course);
				professor.getCourses().remove(course);
				professorRepository.save(professor);
			} else {
				throw new NotFoundException("Ese docente no tiene asignado el curso");
			}
		}
	}

	@Override
	public void removeSubject(Long courseId, Long subjectId) throws NotFoundException {
		Subject subject = subjectRepository.getById(subjectId);
		Course course = courseRepository.getById(courseId);
		if (course != null && subject != null) {
			if (course.getSubject().equals(subject)) {
				course.setSubject(null);
				courseRepository.save(course);
				subject.getCourses().remove(course);
				subjectRepository.save(subject);
			} else {
				throw new NotFoundException("Esa materia no corresponde al curso");
			}
		}

	}

}
