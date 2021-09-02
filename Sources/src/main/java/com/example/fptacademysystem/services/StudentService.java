package com.example.fptacademysystem.services;

import java.util.ArrayList;
import java.util.List;

import com.example.fptacademysystem.dto.AttendanceDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudent{

    @Autowired
    StudentRepository repository;
    @Autowired
    StudentClassRepository studentClassRepo;
    @Autowired
    StudentAttendanceRepository AttendanceRepo;
    @Autowired
    SemesterRepository semRepo;
    @Autowired
    StudentGroupRepository studentGroupRepo;
    @Autowired
    ExamRepository examRepo;

    @Override
    public void createStudent(Student st) {
        repository.save(st);
    }
    @Override
    public void editStudent(Student st) {
        repository.save(st);
    }
    @Override
    public int findNewStudent() {
        return repository.findNewStudent();
    }
    @Override
    public void deleteStudent(int id) {
        repository.deleteStudent(id);
    }
    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }
    @Override
    public Student findOne(int id) {
        return repository.findById(id).get();
    }
    @Override
    public List<StudentResult> findAllStudentResult(int id) {
        return repository.findAllStudentResult(id);
    }
    @Override
    public List<RenderTimetable> findAllStudentTimetable(int stuid) {
        List<RenderTimetable> list = new ArrayList<>();
        Student stu = new Student();
        stu.setStuid(stuid);

        for(StudentClass studentclass : studentClassRepo.findStudentGroupByStudentId(stu)){

            for(RenderTimetable renderTimetable : repository.findAllStudentTimetable(studentclass.getStugroid().getStugroid())){
                list.add(renderTimetable);
            }

        }
        return list;
    }
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
    @Override
    public List<Exam> findExamSchedule(int stuid) {
        List<Integer> list = repository.findExamSchedule(stuid);
        List<Exam> examList = new ArrayList<>();
        for (Integer i : list) {
            Exam ex = new Exam();
            ex = examRepo.findById(i).get();
            examList.add(ex);
        }
        return examList;
    }

}