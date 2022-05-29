package com.react.api.service;

import java.util.List;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Subject;

public interface SubjectService {

	public List<Subject> getAll();

	public Subject getById(Long id);

	public void save(Subject subject);

	public void delete(Long id) throws NotFoundException;

	public Subject getByName(String name) throws NotFoundException;

	public List<Subject> findByNameLikeIgnoreCase(String term);

	public Subject edit(Long id, Subject newSubject) throws NotFoundException;

}