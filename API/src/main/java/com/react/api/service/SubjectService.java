package com.react.api.service;

import java.util.List;
import com.react.api.model.Subject;

public interface SubjectService{
	
	public List<Subject> getAll();
 	public Subject getById(Long id);
	public void save(Subject subject);
	public void delete(Long id);
	public Subject getByName(String name);
	public List<Subject> findByNameLikeIgnoreCase(String term);
	
}


