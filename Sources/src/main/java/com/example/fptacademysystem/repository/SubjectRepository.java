package com.example.fptacademysystem.repository;

import java.util.List;

import com.example.fptacademysystem.model.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.removeat='No' AND s.subjid!=1")
    List<Subject> findall();

    @Query("SELECT MAX(s.subjid) FROM Subject s WHERE s.removeat='No' AND s.subjid!=1")
    int findMaxSubjectId();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("Update Subject s SET s.removeat='Yes' WHERE s.subjid= :id")
    void deleteid(@Param("id")int id);
    
    
}
