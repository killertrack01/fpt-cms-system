package com.example.fptacademysystem.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.fptacademysystem.repository.TimetableRepository;

@Service
public class TimetableService implements ITimetable {

    @Autowired
    TimetableRepository dao;

    @Override
    public List<Timetable> findAll() {
        return dao.findAll();
    }

    public ArrayList<Date> checkSubjectDate(Date dateToCheck, int amount) {
        ArrayList<Date> listDate = new ArrayList<Date>();
        listDate.add(dateToCheck);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToCheck);
        for (int i = 0; i < amount-1; i++) {
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case 2:
                case 4:
                case 3:
                case 5:
                    calendar.add(Calendar.DATE, 2);
                    break;
                case 6:
                case 7:
                    calendar.add(Calendar.DATE, 3);
                    break;
            }
            listDate.add(calendar.getTime());
            calendar.setTime(calendar.getTime());
        }

        return listDate;
    }


    @Override
    public void createTimetable(Timetable tt) {
        dao.save(tt);
    }

    @Override
    public List<Courses> getAllCourses() {
        return dao.getAllCourses();
    }

    @Override
    public List<StudentGroup> getStudentGroupByCourses(Courses courID) {
        return dao.getStudentGroupByCourses(courID);
    }

    @Override
    public List<Semester> getSemesterByStudentGroup(StudentGroup studentGroupID) {
        return dao.getSemesterByStudentGroup(studentGroupID);
    }


    @Override
    public List<Timetable> searchTimetableByClass(int stugroID, int semID) {
        return dao.searchTimetableByClass(stugroID, semID);
    }


    @Override
    public List<RenderTimetable> renderSubInSemOfClass(int studentgroupID, int semID) {

        return dao.renderSubInSemOfClass(studentgroupID, semID);
    }

    @Override
    public List<Semester> searchSemesterByStudentGroup(StudentGroup studentgroupID) {
        return dao.searchSemesterByStudentGroup(studentgroupID);
    }


    @Override
    public List<Subject> getSubjectByBranch(int branchID) {
        return dao.getSubjectByBranch(branchID);
    }


    @Override
    public List<SearchSubject> renderSubOfFuncSearchSubByBranch(int subjID) {
        return dao.renderSubOfFuncSearchSubByBranch(subjID);
    }


    @Override
    public List<SearchSubjectByDate> renderSubByDate(Date startDate, Date endDate) {
        return dao.renderSubByDate(startDate, endDate);
    }

    @Override
    public List<Timetable> findTTByClassAndSem(StudentGroup classID, int semID) {
        return dao.findTTByClassAndSem(classID, semID);
    }



    @Override
    public void deleteTimetable(int timetableID) {
        dao.deleteTimetable(timetableID);
    }

    @Override
    public List<MindateMaxdate> getMinMaxDateOfSem(int studentgroupID) {
        return dao.getMinMaxDateOfSem(studentgroupID);
    }



    @Override
    public void updateTeacherInTimetable(int teaID, int timetableID) {
        dao.updateTeacherInTimetable(teaID, timetableID);
    }

    @Override
    public Lecturer getLecturerById(int lecturID) {
        return dao.getLecturerById(lecturID);
    }

    @Override
    public int getMinMaxDateToCheck(int studentgroupID, int semID) {
        return dao.getMinMaxDateToCheck(studentgroupID, semID);
    }

    @Override
    public String getStudentGroupByStudentGroupID(int stugroID) {
        return dao.getStudentGroupByStudentGroupID(stugroID);
    }

    @Override
    public List<RenderTimetable> getGroupBySubjectName(int stugroid, int semid) {
        return dao.getGroupBySubjectName(stugroid, semid);
    }

    @Override
    public List<Campus> getALlCampus() {
        return dao.getALlCampus();
    }

    @Override
    public List<Branch> getAllBranch() {
        return dao.getAllBranch();
    }

    @Override
    public List<BranchCampus> getBranchCampusByBranchIdAndCampusId(Branch branchID, Campus campusID) {
        return dao.getBranchCampusByBranchIdAndCampusId(branchID, campusID);
    }

    @Override
    public List<StudentGroup> getStudentGroupByBranch(Branch branchID) {
        return dao.getStudentGroupByBranch(branchID);
    }

    @Override
    public List<RenderLocation> renderDataByBranchCampusId(int branchcampusID) {
        return dao.renderDataByBranchCampusId(branchcampusID);
    }


    @Override
    public List<Lecturer> getAllLecturer() {
        // TODO Auto-generated method stub
        return dao.getAllLecturer();
    }

    @Override
    public void updateTimetable(List<Timetable> tt) {
        // TODO Auto-generated method stub
        for (int i = 0; i <tt.size(); i++) {
            dao.updateTimetable(tt.get(i).getSubjdate(), tt.get(i).getAttenteaid(), tt.get(i).getTimetableid());
        }

    }


}
