package com.example.fptacademysystem.repository;


import javax.transaction.Transactional;

import com.example.fptacademysystem.model.Parent;
import com.example.fptacademysystem.model.ParentAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentAccountRepository extends JpaRepository<ParentAccount, Integer>{

    @Query("SELECT p FROM ParentAccount p WHERE p.parid = :parent")
    ParentAccount findParentAccountByStudentId(Parent parent);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ParentAccount s SET s.removeat = 'Yes' WHERE s.parid = :parid")
    void deleteParentAccountByParentid(@Param("parid") Parent parid);

    // Query check login account
    @Query(value = "SELECT p FROM ParentAccount p WHERE p.rollnum = :rollnum AND p.pass = :pass AND p.removeat = 'No' ")
    ParentAccount checkLogin(@Param("rollnum") String rollnum, @Param("pass") String pass);

}