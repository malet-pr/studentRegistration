package com.react.api.repository;

import java.util.List;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.react.api.model.Professor;


@PersistenceContext
@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {

   
    @Query("select p from Professor p where p.lastName like %?1%")  
    public List<Professor> findByLastNameLikeIgnoreCase(String term);
    
    @Query("select p from Professor p where p.lastName like %?1% and  p.isActive=true")  
    public List<Professor> findByLastNameLikeIgnoreCaseAndIsActive(String term);
    
    @Query("select p from Professor p where p.isActive=true")  
    public List<Professor> getAllActive();

}
