package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.AttendanceDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.model.*;

import java.util.List;

public interface IParent {
    List<Parent> findAll();
    void createParent(Parent pa);
    void editParent(Parent pa);
    void deleteParent(Student st);
    int findNewParent();
    Parent findParentByStudentId(Student stuid);

    Result findStuResult(int studentid);

    // Find parent by parent id
    Parent findParent(int id);

    // Show student Result
    List<ParentResults> parentResult(int id);

    // Show student Timetable
    List<RenderTimetable> renderTimetable(int stuid);

    // Find student's group
    List<StudentGroupDTO> findStudentGroupByStudentId(int stuid);

    // List all semester
    List<SemesterDTO> findAllSemester();

    // Get student attendance
    AttendanceDTO findAllStudentAttendances(int stuid, int timetableid);
}
