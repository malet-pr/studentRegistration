package com.react.api.service;

import java.util.List;

import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Student;

public interface StudentService {

	public List<Student> getAll();

	public Student getById(Long id);

	public void save(Student student);

	public void delete(Long id) throws NotFoundException;

	public List<Student> findByLastNameLikeIgnoreCase(String term);

	public Student getByNationalID(String term) throws NotFoundException;

	public Student editStudent(Student newStudent, Long id) throws NotFoundException;


}
