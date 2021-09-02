package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.services.BranchService;
import com.example.fptacademysystem.services.CoursesService;
import com.example.fptacademysystem.services.ImportSubjectExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminCourseController {

    @Autowired
    CoursesService coursesService;
    @Autowired
    BranchService branchService;

    @Autowired
    ImportSubjectExcel services;

    @RequestMapping(value = "/admin/dashboard/courses", method = RequestMethod.GET)
    public String page(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
             return "admin/courses/main-course";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/courses/importCourse", method = RequestMethod.GET)
    public String importCourse(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)) {
                return "admin/courses/importcourse";
            }else{
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }
    }

    @PostMapping(value = "/api/importdataexcelsubject")
    public String uploadFileSubjectExcel(@RequestParam("file") MultipartFile file,  RedirectAttributes redirectAttributes) throws IOException {

        try {
            File f = new File(file.getOriginalFilename());
            File absolute = f.getAbsoluteFile();

            System.out.println(absolute.getPath());
            services.importData(file);

        } catch(Exception ex){
            redirectAttributes.addFlashAttribute("error", "Error");
            return "redirect:/admin/dashboard/courses/importCourse";
        }
        redirectAttributes.addFlashAttribute("success", "Import Success");
        return "redirect:/admin/dashboard/courses/importCourse";
    }
}
