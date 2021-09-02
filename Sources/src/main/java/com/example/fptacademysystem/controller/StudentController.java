package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.dto.ExamDTO;
import com.example.fptacademysystem.dto.FeedbackDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.LecturerRepository;
import com.example.fptacademysystem.repository.StudentGroupRepository;
import com.example.fptacademysystem.repository.SubjectDetailRepository;
import com.example.fptacademysystem.repository.SubjectRepository;
import com.example.fptacademysystem.services.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.time.Period;
import java.util.Locale;
import java.util.List;
import java.util.Objects;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {

    @Autowired
    IStudentAccount services;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentAccountService studentAccountService;
    @Autowired
    FeedbackService feedservices;
    @Autowired
    StudentGroupRepository sgrouprepo;
    @Autowired
    SubjectDetailRepository sdetailrepo;
    @Autowired
    SubjectRepository subrepo;
    @Autowired
    LecturerRepository lecturerRepo;
    @Autowired
    BranchService branchrepo;
    @Autowired
    ExamService examService;
    @Autowired
    SubjectService subjectService;

    // Method to Login Page
    @RequestMapping(value = "/students/dashboard/login", method = RequestMethod.GET)
    public String loginStudent(Model model) {
        model.addAttribute("studentaccount", new StudentAccount());
        return "login/student-login";
    }

    // Method Login
    @RequestMapping(value = "/dologinstudent", method = RequestMethod.POST)
    public String doLoginStudent(@PathParam("rollnum") String rollnum, @PathParam("pass") String pass, Model model,
                                 HttpSession session) {

        StudentAccount account = services.checkStudentLogin(rollnum, pass);

        session.setMaxInactiveInterval(10 * 60);
        if (account != null) {

            int id = account.getStuid().getStuid();
            Student student = studentService.findOne(id);

            session.setAttribute("studentroll", rollnum);
            session.setAttribute("studentFullname", student.getFullnm());
            session.setAttribute("studentId", id);
            session.removeAttribute("stuerror");
            return "redirect:/students/dashboard/index";
        } else {
            session.setAttribute("stuerror", "Rollnum Or Password Invalid ! Please try again..");
            return "redirect:/students/dashboard/login";
        }
    }

    // Method Logout
    @RequestMapping(value = "/logoutstudent", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("studentroll");
        session.removeAttribute("studentFullname");
        session.removeAttribute("studentId");
        return "redirect:/students/dashboard/login";
    }

    @RequestMapping(value = "/students/dashboard/index", method = RequestMethod.GET)
    public String student(Model model, HttpSession session) {
        if (session.getAttribute("studentroll") != null) {
            int id = (int) session.getAttribute("studentId");
            Student student = studentService.findOne(id);
            model.addAttribute("studentFullname", student.getFullnm());
            return "/students/dashboard/index";
        } else {
            return "login/student-login";
        }
    }

    @RequestMapping(value = "/students/dashboard/profile", method = RequestMethod.GET)
    public String userProfile(Model model, HttpSession session) {
        if (session.getAttribute("studentroll") != null) {
            int id = (int) session.getAttribute("studentId");
            String rollnum = String.valueOf(session.getAttribute("studentroll"));
            String userrollnum = "Student" + id;
            if (rollnum.equals(userrollnum)) {
                model.addAttribute("student", studentService.findOne(id));
                Student student = new Student();
                student.setStuid(id);
                model.addAttribute("studentPass", studentAccountService.findByStudentId(student));
                model.addAttribute("major", branchrepo.Findall());
                return "students/profile/main-profile";
            } else {
                return "login/student-login";
            }
        } else {
            return "login/student-login";
        }
    }

    @RequestMapping(value = "/students/dashboard/result", method = RequestMethod.GET)
    public String studentResult(Model model, HttpSession session) {
        if (session.getAttribute("studentroll") != null) {
            int id = (int) session.getAttribute("studentId");
            List<StudentResult> mainResult = new ArrayList<>();
            List<StudentResult> reResult = new ArrayList<>();
            List<StudentResult> list = studentService.findAllStudentResult(id);
            for (StudentResult studentResult : list) {
                if (studentResult.getCass().equals("current class") || studentResult.getCass().equals("old class")) {
                    mainResult.add(studentResult);
                } else if (studentResult.getCass().equals("learn again")) {
                    reResult.add(studentResult);
                }
            }
            model.addAttribute("mainResult", mainResult);
            model.addAttribute("reResult", reResult);
            return "/students/results/main-result";
        } else {
            return "login/student-login";
        }
    }

    @RequestMapping(value = "/students/dashboard/timetable", method = RequestMethod.GET)
    public String studentTimetable(Model model, HttpSession session) {
        if (session.getAttribute("studentroll") != null) {
            return "/students/timetable/main-timetable";
        } else {
            return "login/student-login";
        }
    }

    @RequestMapping(value = "/students/dashboard/feedback", method = RequestMethod.GET)
    public String feedback(Model model, HttpSession session) {
        if (session.getAttribute("studentroll") != null) {
            String rollnum = (String) session.getAttribute("studentroll");
            List<StudentClass> listStuClass = feedservices.findCurrentClassByStudentId(rollnum);

            // get all feedback
            List<FeedbackDTO> list = feedservices.findAll();
            List<StudentFeedback> listStuFeed = feedservices.findAllStudentFeedback();
            try {
                long millis = System.currentTimeMillis();
                Date currentDate = new Date(millis);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                List<FeedbackDTO> listRemove = new ArrayList<>();
                for (FeedbackDTO feedbackDTO : list) {
                    LocalDate ldatetoday = Instant.ofEpochMilli(currentDate.getTime()).atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    LocalDate ldatedto = Instant.ofEpochMilli(format.parse(feedbackDTO.getEnddatefeedback()).getTime())
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    long difference = Period.between(ldatetoday, ldatedto).getDays();
                    if (format.parse(feedbackDTO.getDateoffeedback()).before(currentDate) == false || difference < 0
                            || feedbackDTO.getFeedbackstatus().equals("false")) {
                        listRemove.add(feedbackDTO);
                    }
                }
                list.forEach(feedbackDTO -> {
                    listStuClass.stream().map(stuclass -> stuclass.getStugroid().getStugroid().toString())
                            .filter(stugroid -> (!feedbackDTO.getStugroid().equals(stugroid))).forEachOrdered(_item -> {
                        listRemove.add(feedbackDTO);
                    });
                });
                list.removeAll(listRemove);

                list.forEach(feedbackDTO -> {
                    listStuFeed.stream().filter(student -> (Integer.parseInt(feedbackDTO.getFeedbackid()) == student
                            .getFeedbackid().getFeedbackid())).forEachOrdered(student -> {
                        listStuClass.stream().filter(studentClass -> (Objects
                                .equals(student.getStuid().getStuid(), studentClass.getStuid().getStuid())))
                                .forEachOrdered(_item -> {
                                    feedbackDTO.setCheck(true);
                                });
                    });
                });
            } catch (ParseException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.addAttribute("studentroll", session.getAttribute("studentroll"));
            model.addAttribute("list", list);
            return "/students/feedback/index";
        } else {
            return "login/student-login";
        }
    }

    @RequestMapping(value = "/students/dashboard/feedback/{id}", method = RequestMethod.GET)
    public String studentfeedback(Model model, HttpSession session, @PathVariable("id") String id) {
        if (session.getAttribute("studentroll") != null) {
            Feedback feedback = feedservices.findFeedback(Integer.parseInt(id));
            StudentGroup studentgroup = sgrouprepo.findById(feedback.getStugroid()).get();
            Lecturer lecturer = lecturerRepo.findById(feedback.getLecturid()).get();
            SubjectDetails subjectDetails = feedback.getSubjdetailsid();
            SubjectDetails objectDetails = sdetailrepo.findById(subjectDetails.getSubjdetailsid()).get();
            Subject subject = objectDetails.getSubjid();
            Subject objectSubject = subrepo.findById(subject.getSubjid()).get();

            LocalDate date = Instant.ofEpochMilli(feedback.getDateoffeedback().getTime()).atZone(ZoneId.systemDefault())
                    .toLocalDate();
            FeedbackDTO dto = new FeedbackDTO();

            dto.setDateoffeedback(date.getMonth().toString());
            dto.setNamefeedback(feedback.getFeedbacknm());
            dto.setFeedbackid(feedback.getFeedbackid().toString());
            dto.setStugroupnm(studentgroup.getStugronm());
            dto.setSubjectnm(objectSubject.getShortnm());
            dto.setLecturernm(lecturer.getFullnm());
            dto.setStugroid(String.valueOf(feedback.getStugroid()));
            dto.setEnddatefeedback(feedback.getEnddatefeedback().toString());
            dto.setFeedbackstatus(feedback.getFeedbackstatus());
            model.addAttribute("feedbackdto", dto);
            model.addAttribute("feedbackid", id);
            model.addAttribute("studentroll", session.getAttribute("studentroll"));
            return "/students/feedback/studentfeedback";
        } else {
            return "login/student-login";
        }
    }

    @RequestMapping(value = "/students/dashboard/exam", method = RequestMethod.GET)
    public String getAllExamSchedule(Model model, HttpSession session) {
        if (session.getAttribute("studentroll") != null)
        {
            List<ExamDTO> list=new ArrayList<>();
            int id = (int) session.getAttribute("studentId");
            for (Exam e : studentService.findExamSchedule(id)) {
                if(e.getRemoveat().equals("No")){
                    ExamDTO ed = new ExamDTO();

                    ed.setExamid(e.getExamid());
                    ed.setExamcodelogin(e.getExamcodelogin());
                    ed.setExamday(e.getExamday().toString());
                    ed.setExamtype(e.getExamtype());
                    ed.setExamtime(e.getExamtime());
                    ed.setBout(e.getBout()+"");
                    ed.setRoomid(e.getRoomid().getRoomid().toString());
                    ed.setStugroid(e.getStugroid().getStugroid().toString());
                    ed.setSubjdetailsid(e.getSubjdetailsid()+"");

                    //get room object
                    Room room = new Room();
                    room      = examService.findRoomById(e.getRoomid().getRoomid());
                    ed.setRoomnm(room.getRoomnm());
                    //----------

                    //get student room object
                    StudentGroup stugro = new StudentGroup();
                    stugro = examService.findStudentGroupById(e.getStugroid().getStugroid());
                    ed.setStugronm(stugro.getStugronm());
                    //---------

                    //get subject detail and subject object
                    SubjectDetails subdetail=new SubjectDetails();
                    Subject s = new Subject();

                    subdetail = examService.findSubjectDetailsById(e.getSubjdetailsid());
                    s = examService.findSubjectById(subdetail.getSubjid().getSubjid());
                    ed.setSubjnm(s.getSubjnm());
                    //---------

                    list.add(ed);
                }
            }
            model.addAttribute("list", list);
            return "/students/exam/main-exam";
        }else{
            return "login/student-login";
        }
    }

}