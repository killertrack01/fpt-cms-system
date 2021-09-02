package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query(value = "select * from feedback where removeat = 'No'", nativeQuery = true)
    @Override
    List<Feedback> findAll();

    @Query(value = "select * from feedback where feedbackid = :feedbackid and removeat = 'No'", nativeQuery = true)
    Feedback findFeedback(@Param("feedbackid") int feedbackid);

    @Query(value = "select max(subjdate) FROM timetable WHERE stugroid = :stugroid and subjdetailsid = :subjdetailsid", nativeQuery = true)
    Date getMaxDate(@Param("stugroid") int stugroid, @Param("subjdetailsid") int subjdetailsid);

    @Query(value = "select * from feedback where subjdetailsid = :subjdetailsid and stugroid = :stugroid and removeat = 'No'", nativeQuery = true)
    Feedback findFeedbackBySucjectID(@Param("subjdetailsid") int subjdetailsid, @Param("stugroid") int stugroid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update feedback set removeat = 'Yes' where feedbackid = :feedbackid", nativeQuery = true)
    void deleteFeedback(@Param("feedbackid") int feedbackid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update feedback set feedbackstatus = :feedbackstatus where feedbackid = :feedbackid", nativeQuery = true)
    void updateStatusFeedback(@Param("feedbackstatus") String feedbackstatus, @Param("feedbackid") int feedbackid);

    @Query(value = "SELECT s FROM Semester s WHERE s.removeat='No' AND s.semid!=1 AND SUBSTRING(s.semnm,LENGTH(s.semnm),1) IN (SELECT DISTINCT tt.semid FROM Timetable tt WHERE tt.removeat='No' AND tt.stugroid=:studentgroupID)")
    List<Semester> searchSemesterByStudentGroup(@Param("studentgroupID") StudentGroup studentgroupID);

    @Query(value = "select t.attenteaid from Timetable t where t.removeat ='No' and t.attenteaid !=0 and t.stugroid = :stugroid and t.semid = :semid group by t.attenteaid")
    List<Integer> findLecturerByStudentGroupAndSemId(@Param("stugroid") StudentGroup stugroid, @Param("semid") int semid);

    @Query(value = "select count(t.attenteaid) from Timetable t where t.removeat ='No' and t.attenteaid !=0 and t.attenteaid = :attenteaid and t.stugroid = :stugroid and t.semid = :semid group by t.attenteaid")
    Integer countAttenteaid(@Param("attenteaid")Integer attenteaid, @Param("stugroid") StudentGroup stugroid, @Param("semid") int semid);

    @Query("SELECT l FROM Lecturer l WHERE l.lecturid = :lecturid AND l.removeat = 'No'")
    Lecturer findLecturer(@Param("lecturid") int lecturid);

    @Query(value = "SELECT s FROM SubjectDetails s where s.subjdetailsid != 1 and s.semid = :semid and s.courid = :courid")
    List<SubjectDetails> findSubjid(@Param("semid") Semester semid, @Param("courid") Courses courid);

    @Query(value = "SELECT rsg FROM RenderStudentgroup rsg WHERE rsg.courid=:courID")
    List<RenderStudentgroup> getAllStudentGroupByCourses(@Param("courID") int courID);

    //    @Query(value = "SELECT r FROM RenderTimetable r WHERE r.stugroid=:stugroid AND r.semid=:semid GROUP BY r.subjectname ORDER BY r.timetableid")
//    List<RenderTimetable> getGroupBySubjectName(@Param("stugroid") int stugroid, @Param("semid") int semid);
    @Query(value = "SELECT rt FROM RenderTimetable rt WHERE rt.subjnm != 'Day Off' AND rt.stugroid= :studentgroupID AND rt.semid= :semID")
    List<RenderTimetable> getGroupBySubjectName(@Param("studentgroupID") int studentgroupID, @Param("semID") int semID);
}

