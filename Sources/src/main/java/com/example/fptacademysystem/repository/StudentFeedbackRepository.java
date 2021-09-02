package com.example.fptacademysystem.repository;

import java.util.List;

import com.example.fptacademysystem.model.Feedback;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentFeedbackRepository extends JpaRepository<StudentFeedback, Integer> {

    @Query(value = "select * from student_feedback where removeat = 'No'", nativeQuery = true)
    @Override
    List<StudentFeedback> findAll();

    @Query(value = "select * from student_feedback where feedbackid = :feedbackid and stuid = :stuid and removeat = 'No'", nativeQuery = true)
    StudentFeedback findStudentFeedbackByFeedandStuId(@Param("feedbackid")int feedbackid, @Param("stuid") int stuid);

    @Query(value = "select * from student_feedback where feedbackid = :feedbackid", nativeQuery = true)
    List<StudentFeedback> findStudentFeedbackByFeedId(@Param("feedbackid")Feedback feedbackid);

    @Query("SELECT s FROM StudentClass s where s.stuid = :stuid and s.cass !='old class'")
    List<StudentClass> findCurrentClassByStudentId(Student stuid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update student_feedback set note = :note where stufeedbackid = :stufeedbackid", nativeQuery = true)
    void updateNote(@Param("note") String note, @Param("stufeedbackid") int stufeedbackid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from student_feedback where feedbackid = :feedbackid", nativeQuery = true)
    void deleteStudentFeedback(@Param("feedbackid")int feedbackid);

    @Query(value = "select count(*) from student_feedback where feedbackid = :feedbackid", nativeQuery = true)
    int countStudentFeedbackByFeedId(@Param("feedbackid") int feedbackid);
}
