package com.react.api.repository;

import java.util.List;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.react.api.model.Student;

@PersistenceContext
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	public Student getByNationalID(String term);

	public Student getByLastName(String term);
    
    @Query("select s from Student s where s.lastName like %?1%")  
    public List<Student> findByLastNameLikeIgnoreCase(String term);
	
}
