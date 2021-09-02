package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.CourseDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.dto.StudentClassDTO;
import org.springframework.data.repository.query.Param;


public interface ILecturer {
    List<Lecturer> findAll();

    Lecturer findLecturer(int lecturid);
    Lecturer findLecturerByRollnum(String rollnum);
    List<Branch> findAllBranch();
    int findNewLecturer();
    void postCreate(LecturerDTO teacher);
    Optional<Lecturer> findById(int lecturid);
    void postUpdate(LecturerDTO teacher);
    boolean deleteLecturer(int lecturid);


    List<StudentClassDTO> findStudentByStudentGroupId(int stugroid);
    List<Subject> findAllSubject(Lecturer lecturer);
    List<CourseDTO> findAllCoursesByBranch(Lecturer lecturer);
    List<RenderStudentgroup> getAllStudentGroupByCourses(@Param("courID")int courID);
    List<RenderStudentgroup> getTimetableByLecturerCourseGroupId(Lecturer lecturer);
    List<StudentGroup> getStudentGroupByBranchCampusId(BranchCampus branchcampusID, int teacherID);
//    boolean checkTheRightToEditStudentAttendance(int stugroID, int semID, int subjID, Date subjDate, String sessionVal);
    List<Timetable> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(StudentGroup stugroID,int semID,int subjID,int teacherID);

    List<Lecturer> getAlllecture();
    List<RenderTeachingHours> renderteachinghours(int teaID,int yearVal,int monthVal);
    List<String> groupByRenderteachinghours(int teaID,int yearVal,int monthVal);


    // Attendance Report
    List<ReportAttendance> getAttendanceReport(int stugroid, int semid, int subjid, int teachid);



    //Test All Teaching Hours
    List<RenderTeachingHours> renderAllTeachingHours(int yearVal,int monthVal);
    List<String> groupByAllRenderTeachingHours(int yearVal,int monthVal);
}

