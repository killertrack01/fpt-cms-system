
package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.*;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    @Query(value = "SELECT MAX(lecturid) FROM lecturer", nativeQuery = true)
    int findNewLecturer();

    @Query("SELECT l FROM Lecturer l WHERE l.removeat = 'No'")
    List<Lecturer> findAllLecturers(Sort sort);

    @Query(value = "select * from lecturer where rollnum = ?1 and removeat = 'No'", nativeQuery = true)
    Lecturer findLecturerByRollnum(String rollnum);

    @Query(value = "SELECT l FROM Lecturer l WHERE l.lecturid = :lecturid AND l.removeat = 'No'")
    Lecturer findLecturer(@Param("lecturid")int lecturid);

    @Query(value = "SELECT b FROM Branch b WHERE b.branchid != 1")
    List<Branch> findAllBranch();

//    @Query(value = "SELECT s FROM Subject s WHERE s.courid = :courid AND s.removeat='No' AND s.subjid != 1 ORDER BY s.branchid")
//    List<Subject> findAllSubject(@Param("courid")int courid);

    @Query(value = "SELECT s FROM Subject s WHERE s.subjid = :subjid AND s.removeat='No' AND s.subjid != 1 ORDER BY s.branchid")
    Subject findOneSubject(@Param("subjid")int subjid);

    @Query(value = "SELECT b FROM Branch b WHERE b.branchnm = :branchnm")
    Branch findBranchByMajor(@Param("branchnm")String branchnm);

    @Query(value = "SELECT b FROM Branch b WHERE b.branchid = :branchid AND b.branchid != 1")
    Branch findBranchByBranchId(@Param("branchid")int branchid);

    @Query(value = "SELECT c FROM Courses c WHERE c.courid  != 1 AND c.branchid = :branchid")
    List<Courses> findCoursesByBranch(@Param("branchid")Branch branchid);

    @Query("SELECT c FROM Courses c WHERE c.removeat='No' and c.courid!=1 ")
    List<Courses> findAllCourses();

    @Query(value = "SELECT s FROM SubjectSubjectdetails s WHERE s.courid = :courid")
    List<SubjectSubjectdetails> findSubjectDetails(@Param("courid")int courid);

    @Query(value = "SELECT s FROM StudentClass s where s.stugroid = :stugroid AND s.cass !='old class' AND s.removeat='No'")
    List<StudentClass> findStudentByStudentGroupId(@Param("stugroid")StudentGroup stugroid);

    @Query(value = "SELECT rsg FROM RenderStudentgroup rsg WHERE rsg.courid=:courID")
    List<RenderStudentgroup> getAllStudentGroupByCourses(@Param("courID")int courID);

    @Query(value = "SELECT rsg FROM RenderStudentgroup rsg")
    List<RenderStudentgroup> getAllStudentGroup();

    @Query(value = "SELECT rsg FROM RenderTimetable rsg WHERE rsg.courseSD=:courseSD AND rsg.attenteaid = :attenteaid AND rsg.stugroid = :stugroid")
    List<RenderTimetable> getTimetableByLecturerCourseGroupId(@Param("courseSD")int courseSD, @Param("attenteaid")int attenteaid, @Param("stugroid")int stugroid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Lecturer l SET l.removeat = 'Yes' WHERE l.lecturid = :lecturid")
    void deleteLecturer(@Param("lecturid") int lecturid);

    public static String GET_STUDENT_GROUP_BY_BRANCH_CAMPUS_ID = "select sg from  StudentGroup sg, Timetable t where sg.removeat ='No' and t.removeat ='No' and sg.stugroid =t.stugroid and sg.branchcamid =:branchcampusID and t.attenteaid=:teacherID group by sg.stugroid";

    @Query(value = GET_STUDENT_GROUP_BY_BRANCH_CAMPUS_ID)
    List<StudentGroup> getStudentGroupByBranchCampusId(@Param("branchcampusID") BranchCampus branchcampusID,@Param("teacherID") int teacherID);

    public static String GET_SUBJECT_DATE_BY_SEMESTER_ID_AND_STUDENT_GROUP_ID_AND_SUBJECT_ID = "select tt from Timetable tt, SubjectDetails sd, Subject s where tt.subjdetailsid=sd.subjdetailsid and sd.subjid=s.subjid and tt.removeat='No' and tt.stugroid=:stugroID and tt.semid=:semID and s.subjid=:subjID and tt.attenteaid =:teacherID group by tt.subjdate order by tt.subjdate";
    @Query(value = GET_SUBJECT_DATE_BY_SEMESTER_ID_AND_STUDENT_GROUP_ID_AND_SUBJECT_ID)
    List<Timetable> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(@Param("stugroID") StudentGroup stugroID,
                                                                           @Param("semID") int semID, @Param("subjID") int subjID, @Param("teacherID") int teacherID);

    public static String GET_ALL_LECTURER = "select l from Lecturer l where l.removeat ='No'";
    @Query(value = GET_ALL_LECTURER)
    List<Lecturer> getAlllecture();

    public static String RENDER_TEACHING_HOURS = "select a from RenderTeachingHours a where a.attenteaid=:teaID and a.monthtea=:monthVal and a.yeartea=:yearVal group by a.stugronm";
    @Query(value = RENDER_TEACHING_HOURS)
    List<RenderTeachingHours> renderteachinghours(@Param("teaID") int teaID,@Param("yearVal") int yearVal,@Param("monthVal") int monthVal);

    public static String GROUP_BY_RENDER_TEACHING_HOURS = "select concat(count(a.stugronm),'=',a.stugronm) from RenderTeachingHours a where a.attenteaid=:teaID and a.monthtea=:monthVal and a.yeartea=:yearVal group by a.stugronm";
    @Query(value = GROUP_BY_RENDER_TEACHING_HOURS)
    List<String> groupByRenderteachinghours(@Param("teaID") int teaID,@Param("yearVal") int yearVal,@Param("monthVal") int monthVal);

//    public static String CHECK_THE_RIGHT_TO_EDIT_STUDENT_ATTENDANCE = "select fsia.attenedit from FindStudentInAttendance fsia where fsia.stugroid=:stugroID and fsia.semid=:semID and fsia.subjid=:subjID and fsia.subjdate=Date(':subjDate') and fsia.session=:sessionVal group by fsia.timetableid, fsia.session";
//
//    @Query(value = CHECK_THE_RIGHT_TO_EDIT_STUDENT_ATTENDANCE)
//    boolean checkTheRightToEditSA(@Param("stugroID") int stugroID, @Param("semID") int semID, @Param("subjID") int subjID,
//                                  @Param("subjDate") Date subjDate, @Param("sessionVal") String sessionVal);



    // Attendance Report
    public static String GET_ATTENDANCE_REPORT = "SELECT ra FROM ReportAttendance ra WHERE ra.stugroid = :stugroid AND ra.semid = :semid " +
            "AND ra.subjid = :subjid AND ra.attenteaid = :teachid ORDER BY ra.stuid";
    @Query(value = GET_ATTENDANCE_REPORT)
    List<ReportAttendance> getAttendanceReport(@Param("stugroid") int stugroid, @Param("semid") int semid, @Param("subjid") int subjid,
                                               @Param("teachid") int teachid);




    // Test Render All Teaching Hours

    public static String RENDER_ALL_TEACHING_HOURS = "select a from RenderTeachingHours a where a.monthtea=:monthVal and a.yeartea=:yearVal group by a.stugronm";
    @Query(value = RENDER_ALL_TEACHING_HOURS)
    List<RenderTeachingHours> renderAllTeachingHours(@Param("yearVal") int yearVal,@Param("monthVal") int monthVal);

    public static String GROUP_BY_ALL_RENDER_TEACHING_HOURS = "select concat(count(a.stugronm),'=',a.stugronm) from RenderTeachingHours a where a.monthtea=:monthVal and a.yeartea=:yearVal group by a.stugronm";
    @Query(value = GROUP_BY_ALL_RENDER_TEACHING_HOURS)
    List<String> groupByAllRenderTeachingHours(@Param("yearVal") int yearVal,@Param("monthVal") int monthVal);


}
