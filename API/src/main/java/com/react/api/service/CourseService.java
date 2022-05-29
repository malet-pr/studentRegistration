package com.react.api.service;

import java.util.List;

import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Course;

public interface CourseService {

	public List<Course> getAll();

	public Course getById(Long id);

	public void save(Course course);

	public void delete(Long id) throws NotFoundException;

	public void addStudent(Long courseId, Long studentId) throws NotFoundException;

	public void addProfessor(Long courseId, Long professorId) throws NotFoundException;

	public void removeStudent(Long courseId, Long studentId) throws NotFoundException;

	public void removeProfessor(Long courseId, Long professorId) throws NotFoundException;

	public List<Course> getAvailable();

	public Course editCourse(Long id, Course course) throws NotFoundException;

	public void addOrChangeSubject(Long subjectId, Long courseId);

	public void removeSubject(Long subjectId, Long courseId);

}
