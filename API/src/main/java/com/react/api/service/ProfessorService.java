package com.react.api.service;

import java.util.List;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Professor;

public interface ProfessorService {

	public List<Professor> getAll();

	public Professor getById(Long id) throws NotFoundException;

	public void save(Professor professor);

	public void delete(Long id) throws NotFoundException;

	public List<Professor> findByLastNameLikeIgnoreCase(String term);

	public List<Professor> findByLastNameLikeIgnoreCaseAndIsActive(String term);

	public List<Professor> getAllActive();

	public Professor editProfessor(Professor newProfessor, Long id) throws NotFoundException;

	public Boolean isActive(Long id) throws NotFoundException;

	public void toggleIsActive(Long id) throws NotFoundException;

	public void addSubject(Long professorId, Long subjectId) throws NotFoundException;

	public void removeSubject(Long professorId, Long courseId) throws NotFoundException;

}
