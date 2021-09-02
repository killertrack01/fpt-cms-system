package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.AttendanceDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentService implements IParent{

    //Parent repo
    @Autowired
    ParentRepository repository;
    // Student class Repo
    @Autowired
    StudentClassRepository studentClassRepo;
    // Student group repo
    @Autowired
    StudentGroupRepository studentGroupRepo;
    // Semester repo
    @Autowired
    SemesterRepository semRepo;
    //Attendance repo
    @Autowired
    StudentAttendanceRepository AttendanceRepo;

    @Override
    public List<Parent> findAll() {
        return repository.findAll();
    }

    @Override
    public void createParent(Parent pa) {
        repository.save(pa);
    }
    @Override
    public void editParent(Parent pa) {
        repository.save(pa);
    }
    @Override
    public void deleteParent(Student st) {
        repository.deleteParentByStudentid(st);
    }
    @Override
    public Parent findParentByStudentId(Student stuid) {
        return repository.findParentByStudentId(stuid);
    }

    @Override
    public int findNewParent() {
        return repository.findNewParent();
    }

    //Parent view Student result
    @Override
    public Result findStuResult(int studentid) {
        return repository.findStuResult(studentid);
    }

    @Override
    public List<ParentResults> parentResult(int id) {
        List<ParentResults> render= repository.parentResult(id);
        return render;
    }

    @Override
    public Parent findParent(int id) {
        return repository.findParent(id);
    }

    // Render timetable
    @Override
    public List<RenderTimetable> renderTimetable(int stuid) {

        List<RenderTimetable> list = new ArrayList<>();
        Student stu= new Student();
        stu.setStuid(stuid);
        for(StudentClass studentClass:repository.findStudentclass(stu)){
            for(RenderTimetable renderTimetable:repository.renderTimetable(studentClass.getStugroid().getStugroid())){
                list.add(renderTimetable);
            }
        }
        return list;
    }

    // fInd student group
    @Override
    public List<StudentGroupDTO> findStudentGroupByStudentId(int stuid) {
        Student student = new Student();
        student.setStuid(stuid);
        List<StudentGroupDTO> list = new ArrayList<>();
        List<StudentClass> stuClassList = new ArrayList<>();
        stuClassList=studentClassRepo.findStudentGroupByStudentId(student);
        for(StudentClass studentclass : stuClassList){

            StudentGroup studentgroup = new StudentGroup();
            studentgroup = studentGroupRepo.findById(studentclass.getStugroid().getStugroid()).get();
            StudentGroupDTO s = new StudentGroupDTO();

            s.setStugroid(studentgroup.getStugroid());
            s.setStugronm(studentgroup.getStugronm());
            s.setOpeningdate(studentgroup.getOpeningdate());
            s.setSession(studentgroup.getSession());
            s.setShift(studentgroup.getShift());
            s.setCourid(studentgroup.getCourid().getCourid());

            list.add(s);
        }
        return list;
    }
    // find semester
    @Override
    public List<SemesterDTO> findAllSemester() {
        List<SemesterDTO> list = new ArrayList<>();
        for (Semester semester : semRepo.findAll()) {
            SemesterDTO s=new SemesterDTO();
            s.setSemid(semester.getSemid());
            s.setSemnm(semester.getSemnm());

            list.add(s);
        }
        return list;
    }

    // Get student attendance
    @Override
    public AttendanceDTO findAllStudentAttendances(int stuid, int timetableid) {

        List<Attendance> attendancelist = new ArrayList<>();
        attendancelist = AttendanceRepo.findAll();

        AttendanceDTO dto = new AttendanceDTO();

        for (Attendance attendance :attendancelist){
            if(attendance.getStuid().getStuid() == stuid && attendance.getTimetableid().getTimetableid() == timetableid){

                dto.setAttenid(attendance.getAttenid());
                dto.setPresent(attendance.getPresent()+"");
                dto.setTimetableid(attendance.getTimetableid().getTimetableid());
                dto.setStuid(attendance.getStuid().getStuid());
            }
        }

        return dto;
    }

}
