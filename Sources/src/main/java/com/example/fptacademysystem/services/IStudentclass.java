package com.example.fptacademysystem.services;

import java.util.Date;
import java.util.List;

import com.example.fptacademysystem.dto.StudentDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.*;


public interface IStudentclass {
    void save(StudentClass stcl);
    List<Courses> getAllCourse();
    List<NewStudent> findNewStudent(String major);
    List<NewClass> findNewClassByCourseId(int courseid);
    int numberOfStudentInClass(int StudentGroupId);

    // New
    Date findSubjectStartDay(int stugroid, int subjectid);
    StudentClass findCurrentClassByStudentIdAndStugroid(int stuid,int stugroid);
    StudentDTO findStudentById(int studentid);
    List<StudentClass> findAllStudentByStudentGroupId(int StudentGroupId);
    int findCurrentSubjectDetailId(int stugroid);
    boolean checkSubjectEnd(int subjdetailid,int stugroid);
    boolean checkSubjectStart(int subjdetailid,int stugroid);
    List<RenderStudentgroup> getStudentGroups(int courseid);
    List<RenderStudentgroup> getAllStudentGroupsForIndex(int courseid);
    List<StudentGroup> getAllStudentGroupsByCourse(int courseid);
    List<StudentGroupDTO> findAvailableStudentGroups(int courseid, int stugroid);
    List<Integer> getCompleteSubject( int stuid);
    Student findStudentByRollnum(String rollnum);
    List<Integer> getCurrentLearningSubjectDetailIdByStudentId(int stuid);
    List<StudentGroupDTO> findAvailableStudentGroupsForReLearning(int subjdetailid);
    List<SubjectDetailsDTO> getSubjectNameAndSubjectDetailId(List<Integer> list);
    List<StudentClass> findAllStudentByStudentGroupIdForIndex(int StudentGroupId);
}
