/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.FeedbackDTO;
import com.example.fptacademysystem.dto.StudentFeedbackDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Feedback;
import com.example.fptacademysystem.model.GpaLecturer;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.RenderStudentgroup;
import com.example.fptacademysystem.model.RenderTimetable;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentFeedback;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.repository.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class FeedbackService implements IFeedback {

    @Autowired
    FeedbackRepository feedRepo;

    @Autowired
    StudentFeedbackRepository studentfeedrepo;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    StudentGroupRepository sgrouprepo;

    @Autowired
    SemesterRepository semRepo;

    @Autowired
    SubjectDetailRepository sdetailrepo;

    @Autowired
    SubjectRepository subrepo;

    @Autowired
    LecturerRepository lecturerRepo;

    @Autowired
    StudentRepository sturepo;

    @Autowired
    StudentClassRepository stuclassrepo;

    @Autowired
    GpaLecturerService gpaLecturerService;

    @Override
    public List<FeedbackDTO> findAll() {
        List<Feedback> list = feedRepo.findAll();
        List<FeedbackDTO> listDTO = new ArrayList<>();
        for (Feedback feedback : list)
        {
            StudentGroup studentgroup = sgrouprepo.findById(feedback.getStugroid()).get();
            Lecturer lecturer = lecturerRepo.findById(feedback.getLecturid()).get();
            SubjectDetails subjectDetails = feedback.getSubjdetailsid();
            SubjectDetails objectDetails = sdetailrepo.findById(subjectDetails.getSubjdetailsid()).get();
            Subject subject = objectDetails.getSubjid();
            Subject objectSubject = subrepo.findById(subject.getSubjid()).get();

            FeedbackDTO dto = new FeedbackDTO();
            dto.setDateoffeedback(feedback.getDateoffeedback().toString());
            dto.setNamefeedback(feedback.getFeedbacknm());
            dto.setFeedbackid(feedback.getFeedbackid().toString());
            dto.setStugroupnm(studentgroup.getStugronm());
            dto.setSubjectnm(objectSubject.getSubjnm());
            dto.setLecturernm(lecturer.getFullnm());
            dto.setStugroid(String.valueOf(feedback.getStugroid()));
            dto.setEnddatefeedback(feedback.getEnddatefeedback().toString());
            dto.setFeedbackstatus(feedback.getFeedbackstatus());
            listDTO.add(dto);

        }
        return listDTO;
    }

    @Override
    public Subject findSubjectById(int id) {
        return subrepo.findById(id).get();
    }

    @Override
    public List<Courses> findAllCourse() {
        List<Courses> list = new ArrayList<>();
        for (Courses courses : courseRepo.findAll())
        {
            if(courses.getCourid() != 1){
                list.add(courses);
            }
        }
        return list;
    }

    @Override
    public List<RenderStudentgroup> findStudentGroupByCourid(int courseid) {
        return feedRepo.getAllStudentGroupByCourses(courseid);
    }

    @Override
    public List<Semester> findAllSemester(int stugroupid) {
        StudentGroup studentGroupid = new StudentGroup(stugroupid);
        return feedRepo.searchSemesterByStudentGroup(studentGroupid);
    }

    @Override
    public List<SubjectDetailsDTO> findSubjectDetails(int semid, int courid) {
        List<SubjectDetailsDTO> sdlist = new ArrayList<>();
        Semester semester = new Semester(semid);
        Courses courses = new Courses(courid);
        List<SubjectDetails> list = feedRepo.findSubjid(semester, courses);
        for (SubjectDetails subjectDetails : list)
        {
            SubjectDetailsDTO sd = new SubjectDetailsDTO();
            Subject s = new Subject();
            s = subrepo.findById(subjectDetails.getSubjid().getSubjid()).get();
            sd.setSubjdetailsid(subjectDetails.getSubjdetailsid());
            sd.setSubjnm(s.getSubjnm());

            sdlist.add(sd);
        }
        return sdlist;
    }

    @Override
    public void saveFeedback(FeedbackDTO dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = format.parse(dto.getDateoffeedback());
            Date enddate = format.parse(dto.getEnddatefeedback());

            Feedback feedback = new Feedback();
            feedback.setFeedbacknm(dto.getNamefeedback());
            feedback.setDateoffeedback(date);
            feedback.setEnddatefeedback(enddate);
            feedback.setStugroid(Integer.parseInt(dto.getStugroid()));
            feedback.setLecturid(Integer.parseInt(dto.getLecturid()));
            feedback.setFeedbackstatus("true");
            SubjectDetails subDetails = new SubjectDetails();
            subDetails.setSubjdetailsid(Integer.parseInt(dto.getSubjdetailsid()));
            feedback.setSubjdetailsid(subDetails);
            feedback.setRemoveat("No");

            feedRepo.save(feedback);
        } catch (ParseException ex)
        {
            Logger.getLogger(FeedbackService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Lecturer> findLecturer() {
        return lecturerRepo.findAllLecturers(Sort.by("lecturid").descending());
    }


    @Override
    public Feedback findFeedback(int feedbackid) {
        return feedRepo.findFeedback(feedbackid);
    }

    @Override
    public String findSubjectName(int subjectdetailsid) {
        SubjectDetails subDetails = sdetailrepo.findById(subjectdetailsid).get();
        Subject sub = subDetails.getSubjid();
        Subject subject = subrepo.findById(sub.getSubjid()).get();
        return subject.getSubjnm();
    }

    @Override
    public void editFeedback(FeedbackDTO dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = format.parse(dto.getDateoffeedback());
            Date enddate = format.parse(dto.getEnddatefeedback());

            Feedback feedback = new Feedback();
            feedback.setFeedbackid(Integer.parseInt(dto.getFeedbackid()));
            feedback.setFeedbacknm(dto.getNamefeedback());
            feedback.setDateoffeedback(date);
            feedback.setEnddatefeedback(enddate);
            feedback.setStugroid(Integer.parseInt(dto.getStugroid()));
            feedback.setLecturid(Integer.parseInt(dto.getLecturid()));
            feedback.setFeedbackstatus("true");
            SubjectDetails subDetails = new SubjectDetails();
            subDetails.setSubjdetailsid(Integer.parseInt(dto.getSubjdetailsid()));
            feedback.setSubjdetailsid(subDetails);
            feedback.setRemoveat("No");

            feedRepo.save(feedback);
        } catch (ParseException ex)
        {
            Logger.getLogger(FeedbackService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Feedback findFeedbackBySucjectID(int subjdetailsid, int stugroid) {
        return feedRepo.findFeedbackBySucjectID(subjdetailsid, stugroid);
    }

    @Override
    public void saveStudentFeedback(StudentFeedbackDTO studentFeedbackDTO) {
        StudentFeedback object = new StudentFeedback();
        object.setAns1(studentFeedbackDTO.getAns1());
        object.setAns2(studentFeedbackDTO.getAns2());
        object.setAns3(studentFeedbackDTO.getAns3());
        object.setAns4(studentFeedbackDTO.getAns4());
        object.setAns5(studentFeedbackDTO.getAns5());
        if (!studentFeedbackDTO.getNote().isEmpty())
        {
            object.setNote(studentFeedbackDTO.getNote());
        } else
        {

        }
        int ans1 = Integer.parseInt(studentFeedbackDTO.getAns1());
        int ans2 = Integer.parseInt(studentFeedbackDTO.getAns2());
        int ans3 = Integer.parseInt(studentFeedbackDTO.getAns3());
        int ans4 = Integer.parseInt(studentFeedbackDTO.getAns4());
        int ans5 = Integer.parseInt(studentFeedbackDTO.getAns5());
        double gpafeedback = ((double)(ans1 + ans2 + ans3 + ans4 + ans5)) / 5;
        object.setGpafeedback(gpafeedback);

        Student student = sturepo.findStudentByRollnum(studentFeedbackDTO.getStudentroll());
        Student s = new Student();
        s.setStuid(student.getStuid());
        object.setStuid(s);
        Feedback feedbackOject = new Feedback();
        feedbackOject.setFeedbackid(Integer.parseInt(studentFeedbackDTO.getFeedbackid()));
        object.setFeedbackid(feedbackOject);

        object.setRemoveat("No");
        studentfeedrepo.save(object);

        //create GpaLecturer
        Feedback feedback = feedRepo.findFeedback(Integer.parseInt(studentFeedbackDTO.getFeedbackid()));
        StudentGroup group = new StudentGroup(feedback.getStugroid());
        List<StudentClass> listStudentClass = stuclassrepo.findStudentByStudentGroupId(group);
        if (!listStudentClass.isEmpty())
        {
            int numberStudentFeedback = studentfeedrepo.countStudentFeedbackByFeedId(feedback.getFeedbackid());
            if (listStudentClass.size() == numberStudentFeedback)
            {
                GpaLecturer gpa = new GpaLecturer();
                gpa.setGpamonth(feedback.getEnddatefeedback());
                gpa.setGpasubject(feedback.getSubjdetailsid().getSubjdetailsid());
                gpa.setGpastudentgroup(feedback.getStugroid());

                Feedback f = new Feedback(feedback.getFeedbackid());
                List<StudentFeedback> studentFeedbacks = studentfeedrepo.findStudentFeedbackByFeedId(f);
                double score = 0;
                for (StudentFeedback studentFeedback : studentFeedbacks)
                {
                    score += studentFeedback.getGpafeedback();
                }
                gpa.setGpascore(score / numberStudentFeedback);
                Lecturer l = new Lecturer(feedback.getLecturid());
                gpa.setLecturid(l);
                gpa.setRemoveat("No");
                gpaLecturerService.create(gpa);
            }
        }
    }

    @Override
    public List<StudentFeedback> findAllStudentFeedback() {
        return studentfeedrepo.findAll();
    }

    @Override
    public List<StudentClass> findCurrentClassByStudentId(String rollnum) {
        Student student = sturepo.findStudentByRollnum(rollnum);
        Student s = new Student(student.getStuid());
        List<StudentClass> list = studentfeedrepo.findCurrentClassByStudentId(s);
        return list;
    }

    @Override
    public void updateNote(StudentFeedbackDTO dto) {
        int feedbackid = Integer.parseInt(dto.getFeedbackid());
        Student student = sturepo.findStudentByRollnum(dto.getStudentrollnum());
        StudentFeedback studentFeedback = studentfeedrepo.findStudentFeedbackByFeedandStuId(feedbackid, student.getStuid());
        studentfeedrepo.updateNote(dto.getNote(), studentFeedback.getStufeedbackid());
    }

    @Override
    public void deleteFeedback(int feedbackid) {
        feedRepo.deleteFeedback(feedbackid);
        studentfeedrepo.deleteStudentFeedback(feedbackid);
    }

    @Override
    public List<StudentFeedback> findStudentFeedbackByFeedId(int feedbackid) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackid(feedbackid);
        return studentfeedrepo.findStudentFeedbackByFeedId(feedback);
    }

    @Override
    public void updateStatusFeedback(String feedbackstatus, int feedbackid) {
        feedRepo.updateStatusFeedback(feedbackstatus, feedbackid);
    }

    @Override
    public Date getMaxDateFeedback(int stugroid, int subjdetailsid) {
        Date maxDate = feedRepo.getMaxDate(stugroid, subjdetailsid);
        return maxDate;
    }

    @Override
    public Lecturer findLecturerByStudentGroupAndSemId(int stugroid, int semid) {
        StudentGroup studentGroupId = new StudentGroup(stugroid);
        List<Integer> list = feedRepo.findLecturerByStudentGroupAndSemId(studentGroupId, semid-1);
        if(!list.isEmpty()){
            int max = feedRepo.countAttenteaid(list.get(0), studentGroupId, semid - 1);
            int id = list.get(0);
            for (Integer integer : list)
            {
                Integer countattenteadid = feedRepo.countAttenteaid(integer, studentGroupId, semid - 1);
                if(max < countattenteadid){
                    max = countattenteadid;
                    id = integer;
                }
            }
            System.out.println(max);
            System.out.println(id);
            Lecturer lecturer = feedRepo.findLecturer(id);
            return lecturer;
        }
        return null;
    }

}
