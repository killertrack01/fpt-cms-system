package com.example.fptacademysystem.repository;

import javax.transaction.Transactional;

import com.example.fptacademysystem.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer>{

    @Query("SELECT p FROM Parent p WHERE p.stuid = :student")
    Parent findParentByStudentId(Student student);

    @Query("SELECT max(parid) FROM Parent p")
    int findNewParent();
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Parent s SET s.removeat = 'Yes' WHERE s.stuid = :stuid")
    void deleteParentByStudentid(@Param("stuid") Student stuid);

    @Query("SELECT r FROM Result r WHERE r.stuid= :studentid")
    Result findStuResult(@Param("studentid") int studentid);

    //Find parent by parent id

    @Query("SELECT p FROM Parent p WHERE p.parid= :parid")
    Parent findParent(@Param("parid")int id);

    // Find student with parent id with student id

    @Query("SELECT sc FROM StudentClass sc WHERE sc.stuid= :stuid")
    List<StudentClass> findStudentclass(@Param("stuid")Student id);

    // Show student result

    @Query("SELECT pr FROM ParentResults pr WHERE pr.parid = :id ORDER BY pr.subjnm,pr.examtype, pr.bout,pr.resultid")
    List<ParentResults> parentResult(@Param("id")int id);


    //Render student timetable

    @Query("SELECT rt FROM RenderTimetable rt WHERE rt.stugroid= :stugroid order by rt.timetableid")
    List<RenderTimetable> renderTimetable(@Param("stugroid") int stugroid);

}