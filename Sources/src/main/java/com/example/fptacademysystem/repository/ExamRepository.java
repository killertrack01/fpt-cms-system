package com.example.fptacademysystem.repository;

import java.util.Date;
import java.util.List;

import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.StudentGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    @Query("SELECT e from Exam e where e.stugroid= :stugroid AND e.subjdetailsid= :subjdetailsid and e.removeat='No'")
    List<Exam> findExamByStudentGroupAndSubject(@Param("stugroid")StudentGroup stugroid,@Param("subjdetailsid") int subjdetailsid);

    @Query(value = " SELECT max(resultid) from Result where examid= :examid and removeat='No'",nativeQuery = true)
    Integer checkCompleteExam(@Param("examid") int examid);

    @Query(value = "SELECT DISTINCT rtt.subjdetailsid from timetable rtt where rtt.stugroid= :stugroid and rtt.semid = :semid and rtt.subjdate < Date(:currdate) and rtt.subjdetailsid !=1 and rtt.removeat = 'No'",nativeQuery = true)
    List<Integer> findCurrentAndCompleteSubject(@Param("stugroid") int stugroid, @Param("semid") int semid, @Param("currdate") Date currdate);
}