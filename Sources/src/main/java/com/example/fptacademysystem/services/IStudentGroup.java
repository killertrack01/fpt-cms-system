package com.example.fptacademysystem.services;

import java.util.Date;
import java.util.List;


import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.FindStudentgroup;
import com.example.fptacademysystem.model.GetBranchCount;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.RenderStudentgroup;
import com.example.fptacademysystem.model.Room;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;

public interface IStudentGroup {

    StudentGroup getStudentGroupByID(int studentGroupId);
    FindStudentgroup getDataByStudentGroupId(int studentGroupId);

    List<StudentGroup> getAll();

    void createStudentGroup(StudentGroup studentGroup);

    GetBranchCount getFirstCharBranAndCount(int courID);

    List<BranchCampus> getAllBranchCampus();

//    List<Courses> getCoursesByBranchID(Branch branchID);

    List<Room> getAllRoom();

    List<Branch> getAllBranch();

    List<Lecturer> getAllLecturer();

    List<RenderStudentgroup> getAllStudentGroup();

    List<RenderStudentgroup> getAllStudentGroupByCourses(int courID);

    List<RenderStudentgroup> getAllStudentGroupBySubject(int subjID);

    List<Subject> getAllSubject();

    void updateStudentGroup(String stugronm, Date openingDate, String session, int shift, Courses courID, BranchCampus branchcamID, int stugroID);

    void deleteStudentGroup(int stugroID);

    int getStudentGroupByIdinTimetable(StudentGroup studentGroupId);

    // new
    List<Courses> getCoursesByBranch(Branch branchid);
    List<Subject> getSubjectByBranch(int branchid);

    int getAmountStudentInStudentGroup(StudentGroup studentGroupId);

}





















