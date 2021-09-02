package com.example.fptacademysystem.services;
import java.util.List;

import com.example.fptacademysystem.dto.AttendanceDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.RenderTimetable;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentResult;

public interface IStudent {
    List<Student> findAll();
    List<StudentResult> findAllStudentResult(int id);
    List<RenderTimetable> findAllStudentTimetable(int stuid);
    List<StudentGroupDTO> findStudentGroupByStudentId(int stuid);
    List<SemesterDTO> findAllSemester();
    List<Exam> findExamSchedule(int stuid);
    AttendanceDTO findAllStudentAttendances(int stuid, int timetableid);
    void createStudent(Student st);
    void editStudent(Student st);
    void deleteStudent(int id);
    int findNewStudent();
    Student findOne(int id);
}