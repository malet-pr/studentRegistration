package com.react.api.repository;

import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.react.api.model.Course;

@PersistenceContext
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
