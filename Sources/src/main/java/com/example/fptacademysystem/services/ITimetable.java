package com.example.fptacademysystem.services;

import java.util.Date;
import java.util.List;

import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Campus;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.MindateMaxdate;
import com.example.fptacademysystem.model.RenderLocation;
import com.example.fptacademysystem.model.RenderTimetable;
import com.example.fptacademysystem.model.SearchSubject;
import com.example.fptacademysystem.model.SearchSubjectByDate;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.Timetable;

public interface ITimetable {
    List<Timetable> findAll();
    void createTimetable(Timetable tt);
    List<Courses> getAllCourses();
    List<StudentGroup> getStudentGroupByBranch(Branch branchID);
    List<StudentGroup> getStudentGroupByCourses(Courses courID);
    List<Semester> getSemesterByStudentGroup(StudentGroup studentGroupID);
    List<Timetable> searchTimetableByClass(int stugroID,int semID);
    List<RenderTimetable> renderSubInSemOfClass(int studentgroupID,int semID);
    List<Semester> searchSemesterByStudentGroup(StudentGroup studentgroupID);
    List<Subject> getSubjectByBranch(int branchID);
    List<SearchSubject> renderSubOfFuncSearchSubByBranch(int subjID);
    List<SearchSubjectByDate> renderSubByDate(Date startDate,Date endDate);
    List<Timetable> findTTByClassAndSem(StudentGroup classID,int semID);
    void updateTimetable(List<Timetable> tt);
    void deleteTimetable(int timetableID);
    List<MindateMaxdate> getMinMaxDateOfSem(int studentgroupID);
    List<Lecturer> getAllLecturer();
    void updateTeacherInTimetable(int teaID,int timetableID);
    Lecturer getLecturerById(int lecturID);
    int getMinMaxDateToCheck(int studentgroupID, int semID);
    String getStudentGroupByStudentGroupID(int stugroID);
    List<RenderTimetable> getGroupBySubjectName(int stugroid,int semid);
    List<Campus> getALlCampus();
    List<Branch> getAllBranch();
    List<BranchCampus> getBranchCampusByBranchIdAndCampusId(Branch branchID,Campus campusID);
    List<RenderLocation> renderDataByBranchCampusId(int branchcampusID);
}
