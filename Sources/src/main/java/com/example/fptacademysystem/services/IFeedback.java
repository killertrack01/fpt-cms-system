package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.FeedbackDTO;
import com.example.fptacademysystem.dto.StudentFeedbackDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.*;

import java.util.Date;
import java.util.List;

public interface IFeedback {

    List<FeedbackDTO> findAll();
    Feedback findFeedback(int feedbackid);
    Feedback findFeedbackBySucjectID(int subjdetailsid, int stugroid);
    void saveFeedback(FeedbackDTO dto);
    void editFeedback(FeedbackDTO dto);
    void deleteFeedback(int feedbackid);
    void updateStatusFeedback(String feedbackstatus, int feedbackid);
    Subject findSubjectById(int id);
    Date getMaxDateFeedback(int stugroid, int subjdetailsid);
    String findSubjectName(int subjectdetailsid);
    void saveStudentFeedback(StudentFeedbackDTO studentFeedbackDTO);
    List<StudentClass> findCurrentClassByStudentId(String rollnum);
    List<StudentFeedback> findAllStudentFeedback();
    List<StudentFeedback> findStudentFeedbackByFeedId(int feedbackid);
    void updateNote(StudentFeedbackDTO dto);
    //dropdow box
    List<Courses> findAllCourse();
    List<RenderStudentgroup> findStudentGroupByCourid(int courseid);
    List<Semester> findAllSemester(int stugroupid);
    List<SubjectDetailsDTO> findSubjectDetails(int semid, int courid);
    List<Lecturer> findLecturer();
    Lecturer findLecturerByStudentGroupAndSemId(int stugroid, int semid);
    // end dropdown box
}

