package com.example.fptacademysystem.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.BranchRepository;
import com.example.fptacademysystem.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LecturerController {

    @Autowired
    ILecturerAccount services;
    @Autowired
    LecturerService lecturerService;
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    TimetableService timetableService;
    @Autowired
    BranchService branchService;
    @Autowired
    BranchRepository branchrepo;

    // Method to Login Page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "/welcome";
    }

    // Method to Login Page
    @RequestMapping(value = "lecturer/dashboard/login", method = RequestMethod.GET)
    public String loginLecturer(Model model) {
        model.addAttribute("lectureraccount", new LecturerAccount());
        return "login/lecturer-login";
    }

    // Method Login
    @RequestMapping(value = "/dologinlecturer", method = RequestMethod.POST)
    public String doLoginLecturer(@PathParam("lecturerroll") String rollnum,
                          @PathParam("pass") String pass, Model model, HttpSession session) {

        LecturerAccount account = services.checkLogin(rollnum, pass);
        session.setMaxInactiveInterval(10*60);

        if (account != null) {

            int id = account.getLecturid().getLecturid();
            Lecturer lecturer = lecturerService.findLecturer(id);

            session.setAttribute("lecturerroll", rollnum);
            session.setAttribute("lecturerFullname", lecturer.getFullnm());
            session.setAttribute("lecturerid", id);
            session.removeAttribute("lecerror");
            return "redirect:/lecturer/dashboard/index";
        } else {
            session.setAttribute("lecerror", "Rollnum Or Password Invalid ! Please try again..");
            return "redirect:/lecturer/dashboard/login";
        }
    }

    // Method Logout
    @RequestMapping(value = "/logoutlecturer", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("lecturerroll");
        session.removeAttribute("lecturerFullname");
        session.removeAttribute("lecturerid");
        return "redirect:/lecturer/dashboard/login";
    }

    // Method to dashboard page
    @RequestMapping(value = "lecturer/dashboard/index", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            int id = (int) session.getAttribute("lecturerid");
            Lecturer lecturer = lecturerService.findLecturer(id);
            model.addAttribute("lecturerFullname", lecturer.getFullnm());
            return "/lecturer/dashboard/index";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to profile page
    @RequestMapping(value = "/lecturer/dashboard/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpSession session) {
        int id =  (int) session.getAttribute("lecturerid");
        String rollnum = String.valueOf(session.getAttribute("lecturerroll"));
        String userrollnum = "Lecturer" + id;
        if(!rollnum.isEmpty()) {
            Lecturer teacher = lecturerService.findLecturer(id);

            LecturerDTO t = new LecturerDTO();
            t.setLecturid(teacher.getLecturid().toString());
            t.setRollnum(teacher.getRollnum());
            t.setFullnm(teacher.getFullnm());
            t.setDob(teacher.getDob().toString());
            t.setIdcard(teacher.getIdcard());
            t.setGender(teacher.getGender());
            t.setEmail(teacher.getEmail());
            t.setCompanyemail(teacher.getCompanyemail());
            t.setPoi(teacher.getPoi());
            t.setDoi(teacher.getDoi().toString());
            t.setPhone(teacher.getPhone());
            t.setContract(teacher.getContract());
            t.setMajor(teacher.getMajor());
            t.setLecturertype(teacher.getLecturertype());
            t.setLecturertype(teacher.getLecturertype());
            t.setAddress(teacher.getAddress());


            List<String> genders = new ArrayList<>();
            genders.add("Male");
            genders.add("Female");
            genders.add("Other");


            List<String> types = new ArrayList<>();
            types.add("Ordinary");
            types.add("Business");
            types.add("International");

            List<Branch> majors = branchrepo.findall();


            model.addAttribute("info", t);
            model.addAttribute("avatar", teacher.getImg());

            model.addAttribute("genders", genders);
            model.addAttribute("gender", t.getGender());

            model.addAttribute("types", types);
            model.addAttribute("type", t.getLecturertype());

            model.addAttribute("major", t.getMajor());
            model.addAttribute("majors", majors);


            model.addAttribute("lecturer", lecturerService.findLecturer(id));
            Lecturer lecturer = new Lecturer();
            lecturer.setLecturid(id);
            model.addAttribute("lecturerPass", services.findByLecturerId(lecturer));
            return "/lecturer/profile/index";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to student group
    @RequestMapping(value = "/lecturer/dashboard/studentgroups", method = RequestMethod.GET)
    public String studentGroup(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/students/studentgroups";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to student page
    @RequestMapping(value = "/lecturer/dashboard/students", method = RequestMethod.GET)
    public String student(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/student/main-student";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to course page
    @RequestMapping(value = "/lecturer/dashboard/courses", method = RequestMethod.GET)
    public String courses(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/courses/main-courses";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to result page
    @RequestMapping(value = "/lecturer/dashboard/results", method = RequestMethod.GET)
    public String resultStudent(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/results/main-result";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to attendance page
    @RequestMapping(value = "/lecturer/dashboard/attendance", method = RequestMethod.GET)
    public String attendance(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/attendance/main-attendance";
        } else {
            return "login/lecturer-login";
        }
    }

    // Method to attendance-studentgroup page
//    @RequestMapping(value = "/lecturer/dashboard/attendance/studentgroup", method = RequestMethod.GET)
//    public String attendanceStudentGroup(Model model, HttpSession session) {
//        if (session.getAttribute("lecturerroll") != null){
//            model.addAttribute("renderSubject", attendanceService.renderAttendanceSubject(2, 1, 1));
//            return "/lecturer/attendance/attendance-studentgroup";
//        } else {
//            return "login/lecturer-login";
//        }
//    }


    // Method to attendance page
    @RequestMapping(value = "/lecturer/dashboard/attendance/report", method = RequestMethod.GET)
    public String report(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/attendance/report-attendance";
        } else {
            return "login/lecturer-login";
        }
    }

    @RequestMapping(value = "/lecturer/dashboard/teachinghours/report", method = RequestMethod.GET)
    public String techingHours(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null){
            return "/lecturer/teachinghours/main-teachinghours";
        } else {
            return "login/lecturer-login";
        }
    }

    @RequestMapping(value = "/lecturer/dashboard/subject", method = RequestMethod.GET)
    public String subjects(Model model, HttpSession session) {
        if (session.getAttribute("lecturerroll") != null)
        {
            String rollnum = (String) session.getAttribute("lecturerroll");
            Lecturer lecturer = lecturerService.findLecturerByRollnum(rollnum);
            List<SubjectDTO> list = new ArrayList<>();
            for (Subject s : lecturerService.findAllSubject(lecturer))
            {
                SubjectDTO sd = new SubjectDTO();

                sd.setSubjid(s.getSubjid());
                sd.setSubjnm(s.getSubjnm());
                sd.setShortnm(s.getShortnm());
                sd.setBranchid(s.getBranchid());
                Branch b = new Branch();
                b = branchService.findOne(s.getBranchid());
                sd.setBrandnm(b.getBranchnm());
                list.add(sd);
            }
            model.addAttribute("list", list);
            model.addAttribute("branch", branchService.Findall());
            return "/lecturer/subjects/main-subject";
        } else
        {
            return "login/lecturer-login";
        }
    }

}
