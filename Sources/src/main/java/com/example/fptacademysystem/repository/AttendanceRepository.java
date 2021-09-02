package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Search In Attendance
    public static String GET_ALL_BRANCH_CAMPUS = "select bc from BranchCampus bc where bc.removeat='No'";
    public static String GET_STUDENT_GROUP_BY_BRANCH_CAMPUS_ID = "select sg from StudentGroup sg where sg.removeat='No' and sg.branchcamid=:branchcampusID";
    public static String GET_SEMESTER_BY_STUDENT_GROUP_ID = "select tt.semid from StudentGroup sg join Timetable tt on sg.stugroid=tt.stugroid where sg.removeat='No' and tt.removeat='No' and sg.stugroid=:stugroID group by tt.semid";
    public static String GET_SUBJECT_BY_SEMESTER_ID_AND_STUDENT_GROUP_ID = "SELECT ssa FROM SearchSubjectAttendance ssa WHERE ssa.stugroid=:stugroID and ssa.semid=:semID";
    public static String GET_SUBJECT_DATE_BY_SEMESTER_ID_AND_STUDENT_GROUP_ID_AND_SUBJECT_ID = "select tt from Timetable tt, SubjectDetails sd, Subject s where tt.subjdetailsid=sd.subjdetailsid and sd.subjid=s.subjid and tt.removeat='No' and tt.stugroid=:stugroID and tt.semid=:semID and s.subjid=:subjID group by tt.subjdate order by tt.subjdate";
    // Get Student With 2 Option: Current Class, Learn Again
    public static String GET_STUDENT_CURRENT_CLASS = "SELECT scc FROM StudentCurrentClass scc where scc.stugroid=:stugroID and scc.regissubj<=:subjectID";
    public static String GET_STUDENT_LEARN_AGAIN = "SELECT sla FROM StudentLearnAgain sla where sla.stugroid=:stugroID and sla.semstatus=:semID and sla.regissubj=:subjectID";

    // Update Function
    public static String GET_DATA_TO_UPDATE_FUNCTION = "SELECT fsia FROM FindStudentInAttendance fsia where fsia.stugroid=:stugroID and fsia.semid=:semID and fsia.subjid=:subjID and fsia.subjdate=Date(:subjDate) and fsia.session=:sessionVal";

    // Check Exist
    public static String CHECK_EXIST = "SELECT count(fsia.rollnum) FROM FindStudentInAttendance fsia where fsia.stugroid=:stugroID and fsia.semid=:semID and fsia.subjid=:subjID and fsia.subjdate=Date(:subjDate) and fsia.session=:sessionVal";

    // Create Attendance
    public static String FIND_TIMETABLE = "SELECT tt FROM Timetable tt where tt.removeat='No' and tt.stugroid=:stugroID and tt.semid=:semID and tt.subjdetailsid=:subjdetailsID and tt.subjdate=Date(:subjDate)";
    public static String FIND_STUDENT = "select s.stuid from Student s where s.removeat='No' and s.rollnum=:rollNum";

    //Execute Update Attendance
    public static final String UPDATE_ATTENDANCE = "UPDATE Attendance SET present=:present, note=:note WHERE attenid=:attenID";

    // Search In Attendance
    @Query(value = GET_ALL_BRANCH_CAMPUS)
    List<BranchCampus> getAllBranchCampus();

    @Query(value = GET_STUDENT_GROUP_BY_BRANCH_CAMPUS_ID)
    List<StudentGroup> getStudentGroupByBranchCampusId(@Param("branchcampusID") BranchCampus branchcampusID);

    @Query(value = GET_SEMESTER_BY_STUDENT_GROUP_ID)
    List<Integer> getSemsterByStudentGroupId(@Param("stugroID") int stugroID);

    @Query(value = GET_SUBJECT_BY_SEMESTER_ID_AND_STUDENT_GROUP_ID)
    List<SearchSubjectAttendance> getSubjectBySemsterIdAndStudentGroupId(@Param("stugroID") int stugroID,
                                                                         @Param("semID") int semID);

    @Query(value = GET_SUBJECT_DATE_BY_SEMESTER_ID_AND_STUDENT_GROUP_ID_AND_SUBJECT_ID)
    List<Timetable> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(@Param("stugroID") StudentGroup stugroID,
                                                                           @Param("semID") int semID, @Param("subjID") int subjID);

    // Get Student With 2 Option: Current Class, Learn Again
    @Query(value = GET_STUDENT_CURRENT_CLASS)
    List<StudentCurrentClass> getStudentCurrentClass(@Param("stugroID") int stugroID,
                                                     @Param("subjectID") int subjectID);

    @Query(value = GET_STUDENT_LEARN_AGAIN)
    List<StudentLearnAgain> getStudentLearnAgain(@Param("stugroID") int stugroID, @Param("semID") int semID,
                                                 @Param("subjectID") int subjectID);

    //---------------------------------------------------------------- Create Attendance
    @Query(value = FIND_TIMETABLE)
    List<Timetable> findTimetable(@Param("stugroID") StudentGroup stugroID, @Param("semID") int semID,
                                  @Param("subjdetailsID") SubjectDetails subjdetailsID, @Param("subjDate") Date subjDate);

    @Query(value = FIND_STUDENT)
    int findStudent(@Param("rollNum") String rollNum);

    //---------------------------------------------------------------- Update Attendance
    @Query(value = GET_DATA_TO_UPDATE_FUNCTION)
    List<FindStudentInAttendance> findStundentToUpdate(@Param("stugroID") int stugroID, @Param("semID") int semID,
                                                       @Param("subjID") int subjID, @Param("subjDate") Date subjDate, @Param("sessionVal") String sessionVal);

    // Check Exist
    @Query(value = CHECK_EXIST)
    int checkExist(@Param("stugroID") int stugroID, @Param("semID") int semID, @Param("subjID") int subjID,
                   @Param("subjDate") Date subjDate, @Param("sessionVal") String sessionVal);

    //Execute Update Attendance
    @Transactional
    @Modifying
    @Query(value = UPDATE_ATTENDANCE)
    void updateAttendance(@Param("present") Boolean present,@Param("note") String note,@Param("attenID") int attenID);

    // Attendance Report
    public static String GET_ATTENDANCE_REPORT = "SELECT ra FROM ReportAttendance ra WHERE ra.stugroid = :stugroid AND ra.semid = :semid " +
            "AND ra.subjid = :subjid ORDER BY ra.stuid";
    @Query(value = GET_ATTENDANCE_REPORT)
    List<ReportAttendance> getAttendanceReport(@Param("stugroid") int stugroid, @Param("semid") int semid, @Param("subjid") int subjid);

    public static String GET_DATE_ATTENDANCE_REPORT = "SELECT DISTINCT tt.subjdate FROM Timetable tt WHERE tt.semid = 1 AND tt.stugroid =1 AND tt.subjdetailsid = ";
}
