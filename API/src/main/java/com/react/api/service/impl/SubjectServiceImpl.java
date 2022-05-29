package com.react.api.service.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.react.api.exceptions.custom.NotFoundException;
import com.react.api.model.Subject;
import com.react.api.repository.SubjectRepository;
import com.react.api.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Subject> getAll() {
		return (List<Subject>) subjectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	@Transactional(readOnly = true)
	public Subject getById(Long id) {
		return subjectRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Subject subject) {
		subjectRepository.save(subject);
	}

	@Override
	@Transactional
	public void delete(Long id) throws NotFoundException {
		subjectRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Subject getByName(String name) throws NotFoundException {
		return subjectRepository.getByName(name);
	}

	@Override
	@Transactional
	public List<Subject> findByNameLikeIgnoreCase(String term) {
		return subjectRepository.findByNameLikeIgnoreCase(term);
	}

	@Override
	@Transactional
	public Subject edit(Long id, Subject newSubject) throws NotFoundException {
		Subject subject = subjectRepository.getById(id);
		if (newSubject.getName() != null) {
			subject.setName(newSubject.getName());
		}
		if (newSubject.getDescription() != null) {
			subject.setDescription(newSubject.getDescription());
		}
		return subject;
	}

}
