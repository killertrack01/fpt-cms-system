package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    @Query("SELECT e from Result e where e.stuid= :stuid and e.examid= :examid and e.removeat='No'")
    Result findExamByStudentIdAndExamId(@Param("stuid") Student stuid,@Param("examid") Exam examid);

    @Query("SELECT e.examid from Exam e join SubjectDetails sd on sd.subjdetailsid = e.subjdetailsid where e.stugroid= :stugroid and sd.semid= :semid and e.removeat='No' ")
    List<Integer> findExamByStudentGroupAndSemId(@Param("stugroid") StudentGroup stugroid,@Param("semid") Semester semid);

    @Query(value="select st.stuid from student st join attendance att on att.stuid = st.stuid join timetable tt on tt.timetableid = att.timetableid where st.stuid = :stuid and tt.subjdetailsid= :subjdetailsid and att.present = 1 and st.removeat='No' ",nativeQuery = true)
    List<Integer> getStudentAttendance(@Param("stuid") Integer stuid,@Param("subjdetailsid") int subjdetailsid);

    @Query(value="select tt.timetableid from timetable tt JOIN student_group sg on sg.stugroid = tt.stugroid where sg.stugroid = :stugroid and tt.subjdetailsid = :subjdetailsid and sg.removeat = 'No' and tt.removeat='No'",nativeQuery = true)
    List<Integer> getAllSubjectCount(@Param("stugroid") Integer stugroid,@Param("subjdetailsid") int subjdetailsid);
}
