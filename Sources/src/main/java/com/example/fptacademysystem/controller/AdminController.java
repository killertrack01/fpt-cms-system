package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.services.*;
import org.apache.commons.math3.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IStaffAccount services;
    @Autowired
    StaffService staffService;
    @Autowired
    StaffAccountService staffAccountService;
    @Autowired
    DepartmentService depService;
    @Autowired
    RoleService roleService;
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectService subjectServiceservice;
    @Autowired
    BranchService branchService;
    @Autowired
    CoursesService coursesService;
    @Autowired
    StudentGroupService studenGroupService;
    @Autowired
    LecturerService lecturerService;
    @Autowired
    ExamService examService;
    @Autowired
    TimetableService timetableService;
    @Autowired
    BranchCampusService branchCampusService;


    // Method to Login Page
    @RequestMapping(value = "/admin/dashboard/login", method = RequestMethod.GET)
    public String loginStaff(Model model) {
        model.addAttribute("staffaccount", new StaffAccount());
        return "login/admin-login";
    }

    @RequestMapping(value = "/admin/dashboard/index", method = RequestMethod.GET)
    public String student(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null){
            int id =  (int) session.getAttribute("staffId");
            Staff staff = staffService.findStaffByStaffid(id);
            model.addAttribute("studentFullname", staff.getFullnm());
            // List Subject
            List<SubjectDTO> listSubject = new ArrayList<>();
            int sumSubject = 0;
            for(Subject s: subjectServiceservice.Findall())
            {
                SubjectDTO sd=new SubjectDTO();

                sd.setSubjid(s.getSubjid());
                sd.setSubjnm(s.getSubjnm());
                sd.setShortnm(s.getShortnm());
                sd.setBranchid(s.getBranchid());
                Branch b= new Branch();
                b= branchService.findOne(s.getBranchid());
                sd.setBrandnm(b.getBranchnm());
                listSubject.add(sd);
                sumSubject = listSubject.size();
            }
            model.addAttribute("sumsubject", sumSubject);

            // List Course
            List<Courses> listCourse = new ArrayList<>();
            int sumCourse = 0;
            for (Courses courses : coursesService.Findall()) {
                if(courses.getRemoveat().equals("No") && courses.getCourid() != 1){
                    listCourse.add(courses);
                    sumCourse = listCourse.size();
                }
            }
            model.addAttribute("sumcourse", sumCourse);

            // List Student Group
            List<StudentGroup> listStudentGroup = new ArrayList<>();
            int sumStudentGroup = 0;
            for (StudentGroup studentGroup : studenGroupService.getAll()) {
                if(studentGroup.getRemoveat().equals("No")){
                    listStudentGroup.add(studentGroup);
                    sumStudentGroup = listStudentGroup.size();
                }
            }
            model.addAttribute("sumstugroup", sumStudentGroup);

            // List Student Group
            List<BranchCampus> listBranchCampus = new ArrayList<>();
            int sumBranchCampus = 0;
            for (BranchCampus branchCampus : branchCampusService.Findall()) {
                if(branchCampus.getRemoveat().equals("No")){
                    listBranchCampus.add(branchCampus);
                    sumBranchCampus = listBranchCampus.size();
                }
            }
            model.addAttribute("sumbranchcampus", sumBranchCampus);

            // List Lecturer
            List<Lecturer> listLecturer = new ArrayList<>();
            int sumLecturer = 0;
            for (Lecturer lecturer : lecturerService.findAll()) {
                if(lecturer.getRemoveat().equals("No")){
                    listLecturer.add(lecturer);
                    sumLecturer = listLecturer.size();
                }
            }
            model.addAttribute("sumlecturer", sumLecturer);

            // List Exam
            List<Exam> listExam = new ArrayList<>();
            int sumExam = 0;
            for (Exam exam : examService.findAll()) {
                if(exam.getRemoveat().equals("No")){
                    listExam.add(exam);
                    sumExam = listExam.size();
                }
            }
            model.addAttribute("sumexam", sumExam);

            // List Exam
            List<Timetable> listTT = new ArrayList<>();
            int sumTimetable = 0;
            for (Timetable tt : timetableService.findAll()) {
                if(tt.getRemoveat().equals("No")){
                    listTT.add(tt);
                    sumTimetable = listTT.size();
                }
            }
            model.addAttribute("sumtt", sumTimetable);

            // List Staff
            List<Staff> listStaff = new ArrayList<>();
            int sumStaff = 0;
            for (Staff staffs : staffService.findAll()) {
                if(staffs.getRemoveat().equals("No")){
                    listStaff.add(staffs);
                    sumStaff = listStaff.size();
                }
            }
            model.addAttribute("sumstaff", sumStaff);

            // List Student
            List<Student> list = new ArrayList<>();
            int sumStudent = 0;
            for (Student student : studentService.findAll()) {
                if(student.getRemoveat().equals("No")){
                    list.add(student);
                    sumStudent = list.size();
                }
            }
            model.addAttribute("sum", sumStudent);
            model.addAttribute("list", list);
            return "/admin/dashboard/index";
        } else {
            return "login/admin-login";
        }
    }

    // Method Login
    @RequestMapping(value = "/dologinstaff", method = RequestMethod.POST)
    public String doLoginStaff(@PathParam("rollnum") String rollnum,
                                 @PathParam("pass") String pass,
                                 Model model, HttpSession session) {

        StaffAccount account = services.checkStaffLogin(rollnum, pass);

        session.setMaxInactiveInterval(10*60);
        if (account != null) {

            int id = account.getStaffid().getStaffid();
            Staff staff = staffService.findStaffByStaffid(id);

            session.setAttribute("staffroll", rollnum);
            session.setAttribute("staffFullname",staff.getFullnm());
            session.setAttribute("role", staff.getRoleid().getRoleid());
            session.setAttribute("staffId",id);
            session.removeAttribute("stafferror");
            return "redirect:/admin/dashboard/index";
        } else {
            session.setAttribute("stafferror", "Rollnum Or Password Invalid ! Please try again..");
            return "redirect:/admin/dashboard/login";
        }
    }

    // Method Logout
    @RequestMapping(value = "/logoutstaff", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("staffroll");
        session.removeAttribute("staffFullname");
        session.removeAttribute("staffId");
        return "redirect:/admin/dashboard/login";
    }

    // Method To Access Denied
    @RequestMapping(value = "/admin/dashboard/access-denied", method = RequestMethod.GET)
    public String accessdenied(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null){
            return "admin/dashboard/access-denied";
        } else {
            return "login/admin-login";
        }
    }
    
    @RequestMapping(value = "/admin/dashboard/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null){
            int id = (int) session.getAttribute("staffId");
            model.addAttribute("listDept", depService.Findall());
            model.addAttribute("listRole", roleService.findAll());
            model.addAttribute("staff", staffService.findStaffByStaffid(id));
            return "admin/dashboard/main-profile";
        } else {
            return "login/admin-login";
        }
    }
}
