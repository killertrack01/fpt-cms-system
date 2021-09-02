package com.example.fptacademysystem.repository;

import java.util.List;

import com.example.fptacademysystem.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
    @Query("SELECT d FROM Department d WHERE d.removeat='No'")
    List<Department> findall();
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Department d SET d.removeat='Yes' WHERE d.depid= :id")
    void deleteid(@Param("id")int id);
}
