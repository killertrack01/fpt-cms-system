package com.example.fptacademysystem.repository;

import javax.transaction.Transactional;

import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, Integer>{
    
    @Query("SELECT s FROM StudentAccount s WHERE s.stuid = :student")
    StudentAccount findPStudentAccountByStudentId(Student student);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE StudentAccount s SET s.removeat = 'Yes' WHERE s.stuid = :stuid")
    void deleteStudentAccountByStudentid(@Param("stuid") Student stuid);

    @Query(value = "SELECT * FROM student_account WHERE rollnum = :rollnum AND pass = :pass AND removeat = 'No'", nativeQuery = true)
    StudentAccount checkStudentLogin(@Param("rollnum") String rollnum, @Param("pass") String pass);
}
