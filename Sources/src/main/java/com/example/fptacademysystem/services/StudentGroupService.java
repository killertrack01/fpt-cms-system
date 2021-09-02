package com.example.fptacademysystem.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.fptacademysystem.repository.StudentGroupRepository;

@Service
public class StudentGroupService implements IStudentGroup {

    @Autowired
    StudentGroupRepository dao;


    @Override
    public List<StudentGroup> getAll() {

        return dao.getAll();
    }

    @Override
    public void createStudentGroup(StudentGroup studentGroup) {

        dao.save(studentGroup);
    }

    @Override
    public GetBranchCount getFirstCharBranAndCount(int courID) {

        return dao.getFirstCharBranAndCount(courID);
    }


    @Override
    public List<Room> getAllRoom() {

        return dao.getAllRoom();
    }

    @Override
    public List<Lecturer> getAllLecturer() {

        return dao.getAllLecturer();
    }

    @Override
    public List<RenderStudentgroup> getAllStudentGroup() {

        return dao.getAllStudentGroup();
    }

    @Override
    public List<Subject> getAllSubject() {

        return dao.getAllSubject();
    }

    @Override
    public List<RenderStudentgroup> getAllStudentGroupByCourses(int courID) {

        return dao.getAllStudentGroupByCourses(courID);
    }

    @Override
    public void updateStudentGroup(String stugronm, Date openingDate, String session, int shift, Courses courID, BranchCampus branchcamID, int stugroID) {

        dao.updateStudentGroup(stugronm, openingDate, session, shift, courID, branchcamID, stugroID);
    }

    @Override
    public void deleteStudentGroup(int stugroID) {

        dao.deleteStudentGroup(stugroID);
    }

    @Override
    public int getStudentGroupByIdinTimetable(StudentGroup studentGroupId) {

        return dao.getStudentGroupByIdinTimetable(studentGroupId);
    }

    @Override
    public List<RenderStudentgroup> getAllStudentGroupBySubject(int subjID) {

        return dao.getAllStudentGroupBySubject(subjID);
    }

    @Override
    public List<BranchCampus> getAllBranchCampus() {

        return dao.getAllBranchCampus();
    }

    @Override
    public List<Branch> getAllBranch() {

        return dao.getAllBranch();
    }

    @Override
    public List<Courses> getCoursesByBranch(Branch branchid) {

        return dao.getCoursesByBranch(branchid);
    }

    @Override
    public List<Subject> getSubjectByBranch(int branchid) {

        return dao.getSubjectByBranch(branchid);
    }

    @Override
    public StudentGroup getStudentGroupByID(int studentGroupId) {

        return dao.getStudentGroupByID(studentGroupId);
    }

    @Override
    public FindStudentgroup getDataByStudentGroupId(int studentGroupId) {

        return dao.getDataByStudentGroupId(studentGroupId);
    }

    @Override
    public int getAmountStudentInStudentGroup(StudentGroup studentGroupId) {
        // TODO Auto-generated method stub
        return dao.getAmountStudentInStudentGroup(studentGroupId);
    }


}
