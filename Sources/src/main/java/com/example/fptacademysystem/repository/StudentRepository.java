package com.example.fptacademysystem.repository;

import javax.transaction.Transactional;

import com.example.fptacademysystem.model.RenderTimetable;
import com.example.fptacademysystem.model.Student;

import com.example.fptacademysystem.model.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    @Query(value = "select * from student where rollnum = ?1 and removeat = 'No'", nativeQuery = true)
    Student findStudentByRollnum(String rollnum);

    @Query("SELECT max(stuid) FROM Student s")
    int findNewStudent();

    @Query("SELECT max(stuid) FROM Student s")
    Integer findNewStudentId();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Student s SET s.removeat = 'Yes' WHERE s.stuid = :student")
    void deleteStudent(@Param("student") int student);

    @Query("SELECT s FROM StudentResult s WHERE s.stuid=:student ORDER BY subjnm,examtype,bout,resultid")
    List<StudentResult> findAllStudentResult(@Param("student") int student);

    @Query("select s from RenderTimetable s WHERE s.stugroid = :stugroid order by s.timetableid")
    List<RenderTimetable> findAllStudentTimetable(@Param("stugroid") int stugroid);

    @Query(value = "SELECT ex.examid FROM exam ex LEFT JOIN result rs ON rs.examid = ex.examid JOIN student_group sg ON sg.stugroid = ex.stugroid JOIN student_class sc ON sc.stugroid = sg.stugroid WHERE sc.stuid = :stuid AND ex.stugroid = rs.examid IS NULL AND ex.removeat = 'No' ",nativeQuery = true)
    List<Integer> findExamSchedule(@Param("stuid") int stuid);


}