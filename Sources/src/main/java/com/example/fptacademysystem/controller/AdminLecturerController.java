package com.example.fptacademysystem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.repository.BranchRepository;
import com.example.fptacademysystem.services.LecturerService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLecturerController {

    @Autowired
    LecturerService services;
    @Autowired
    BranchRepository branchrepo;

    @RequestMapping(value = "/admin/dashboard/lecturers", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            ModelAndView mv = new ModelAndView("/admin/lecturer/main-lecturer");
            List<String> genders = new ArrayList<>();
            genders.add("Male");
            genders.add("Female");
            genders.add("Other");

            List<Branch> majors = branchrepo.findall();

            List<String> types = new ArrayList<>();
            types.add("Ordinary");
            types.add("Business");
            types.add("International");

            mv.addObject("list", services.findAll());
            mv.addObject("genders", genders);
            mv.addObject("types", types);
            mv.addObject("majors", majors);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("login/admin-login");
            return mv;
        }

    }



    @RequestMapping(value = "/admin/dashboard/lecturers/profile/{id}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable("id")int id, HttpSession session){
        if (session.getAttribute("staffroll") != null) {
            ModelAndView mv = new ModelAndView("admin/lecturer/profile");
            Lecturer teacher = services.findLecturer(id);

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

            mv.addObject("info", t);
            mv.addObject("avatar", teacher.getImg());

            mv.addObject("genders", genders);
            mv.addObject("gender", t.getGender());

            mv.addObject("types", types);
            mv.addObject("type", t.getLecturertype());

            mv.addObject("major", t.getMajor());
            mv.addObject("majors", majors);

            return mv;
        } else {
            ModelAndView mv = new ModelAndView("login/admin-login");
            return mv;
        }
    }

    @RequestMapping(value = "/admin/dashboard/lecturers/gpa", method = RequestMethod.GET)
    public String gpaofmonth(Model model, HttpSession session){
        if (session.getAttribute("staffroll") != null) {
            LocalDate start = LocalDate.parse("2015-01-01");
            LocalDate current = LocalDate.now();
            List<Integer> totalYears = new ArrayList<>();
            while(!start.isAfter(current)){
                totalYears.add(start.getYear());
                start = start.plusYears(1);
            }
            // sort list DESC
            Collections.sort(totalYears, (Integer o1, Integer o2) -> o1 > o2 ? -1 : 1);
            model.addAttribute("list", services.findAll());
            model.addAttribute("years", totalYears);
            return "/admin/lecturer/gpalecturer";
        } else {
            return "login/admin-login";
        }
       
    }

    @RequestMapping(value = "/admin/dashboard/lecturers/teachinghours", method = RequestMethod.GET)
    public String teachinghours(Model model, HttpSession session){
        if (session.getAttribute("staffroll") != null) {
            return "/admin/lecturer/teachinghours";
        } else {
            return "login/admin-login";
        }

    }
}
