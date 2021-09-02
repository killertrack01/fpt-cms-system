package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

    @Query("SELECT c FROM Courses c WHERE c.removeat='No' AND c.courid!=1")
    List<Courses> findall();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Courses c SET c.removeat='Yes' WHERE c.courid= :id")
    void deleteid(@Param("id") int id);

    // list all courses for validation
    @Query("SELECT c FROM Courses c WHERE c.courid!=1")
    List<Courses> listAll();


    // list all courses by branch id
    @Query(value = "SELECT c FROM Courses c WHERE c.branchid= :branchid")
    List<Courses> listCoursesByBranchId(@Param("branchid") Branch branchid);


    // Modify Function Find All
    @Override
    @Query("SELECT c FROM Courses c WHERE c.removeat='No' and c.courid!=1 ")
    List<Courses> findAll();

    @Query("SELECT c FROM Courses c WHERE c.removeat='No' and c.courid!=1 AND c.cournm = :cournm")
    Courses findCoursesByName(@Param("cournm")String cournm);

    @Query("SELECT c FROM Courses c WHERE c.removeat='No' and c.courid!=1 AND c.courid = :courid AND c.branchid = :branchid")
    List<Courses> findCoursesByCourseAndBranchId(@Param("courid")int courid, @Param("branchid")Branch branchid);

    @Query("SELECT c FROM Courses c WHERE c.removeat='No' AND c.courid!=1 AND c.branchid = :branchid")
    List<Courses> findCoursesByBranchId(@Param("branchid")Branch branchid);



}
