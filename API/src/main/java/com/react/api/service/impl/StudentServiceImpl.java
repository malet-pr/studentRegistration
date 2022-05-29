package com.react.api.service.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Student;
import com.react.api.repository.StudentRepository;
import com.react.api.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAll() {
		return (List<Student>) studentRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
	}

	@Override
	public Student getById(Long id) {
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> findByLastNameLikeIgnoreCase(String term) {
		return studentRepository.findByLastNameLikeIgnoreCase(term);
	}

	@Override
	public Student getByNationalID(String term) throws NotFoundException {
		return studentRepository.getByNationalID(term);
	}

	@Override
	public Student editStudent(Student newStudent, Long id) throws NotFoundException {
		Student student = studentRepository.getById(id);
		if (newStudent.getNationalID() != null) {
			student.setNationalID(newStudent.getNationalID());
		}
		if (newStudent.getFirstName() != null) {
			student.setFirstName(newStudent.getFirstName());
		}
		if (newStudent.getLastName() != null) {
			student.setLastName(newStudent.getLastName());
		}
		return student;
	}

}
