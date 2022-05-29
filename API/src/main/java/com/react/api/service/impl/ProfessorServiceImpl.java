package com.react.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.react.api.exceptions.custom.BadRequestException;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Course;
import com.react.api.model.Professor;
import com.react.api.model.Subject;
import com.react.api.repository.CourseRepository;
import com.react.api.repository.ProfessorRepository;
import com.react.api.repository.SubjectRepository;
import com.react.api.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Professor> getAll() {
		return (List<Professor>) professorRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
	}

	@Override
	@Transactional(readOnly = true)
	public Professor getById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Professor professor) {
		professorRepository.save(professor);
	}

	@Override
	@Transactional
	public void delete(Long id) throws NotFoundException {
		professorRepository.deleteById(id);
	}

	@Override
	public List<Professor> findByLastNameLikeIgnoreCase(String term) {
		return professorRepository.findByLastNameLikeIgnoreCase(term);
	}

	@Override
	public List<Professor> findByLastNameLikeIgnoreCaseAndIsActive(String term) {
		return professorRepository.findByLastNameLikeIgnoreCaseAndIsActive(term);
	}

	@Override
	public List<Professor> getAllActive() {
		return professorRepository.getAllActive();
	}

	@Override
	public Professor editProfessor(Professor newProfessor, Long id) throws NotFoundException {
		Professor professor = professorRepository.getById(id);
		if (newProfessor.getEmployeeID() != null) {
			professor.setEmployeeID(newProfessor.getEmployeeID());
		}
		if (newProfessor.getNationalID() != null) {
			professor.setNationalID(newProfessor.getNationalID());
		}
		if (newProfessor.getFirstName() != null) {
			professor.setFirstName(newProfessor.getFirstName());
		}
		if (newProfessor.getLastName() != null) {
			professor.setLastName(newProfessor.getLastName());
		}
		return professor;
	}

	@Override
	public Boolean isActive(Long id) throws NotFoundException {
		Professor professor = professorRepository.getById(id);
		return professor.isActive();
	}

	@Override
	public void toggleIsActive(Long id) throws NotFoundException {
		Professor professor = professorRepository.getById(id);
		professor.setActive(!professor.isActive());
		if (!professor.isActive()) {
			for (Course c : professor.getCourses()) {
				c.getProfessors().remove(professor);
			}
			professor.getCourses().clear();
		}
	}

	@Override
	public void addSubject(Long professorId, Long subjectId) throws NotFoundException {
		Professor professor = professorRepository.getById(professorId);
		Subject subject = subjectRepository.getById(subjectId);
		professor.getSubjects().add(subject);
		subject.getProfessors().add(professor);
	}

	@Override
	public void removeSubject(Long professorId, Long subjectId) throws NotFoundException {
		Professor professor = professorRepository.getById(professorId);
		Subject subject = subjectRepository.getById(subjectId);
		try {
			professor.getCourses().remove(subject);
			subject.getProfessors().remove(professor);
		} catch (Exception e) {
			throw new BadRequestException("No se pudo eliminar la materia");
		}
	}
}
