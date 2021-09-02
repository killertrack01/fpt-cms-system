package com.example.fptacademysystem.controller;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminStudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    ParentService parentService;
    @Autowired
    StudentAccountService studentAccountService;
    @Autowired
    BranchService branchService;
    @Autowired
    ImportStudentExcel importstudentservice;

    @RequestMapping(value = "/admin/dashboard/students", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            List<Student> list = new ArrayList<>();
            for (Student student : studentService.findAll()) {
                if(student.getRemoveat().equals("No")){
                    list.add(student);
                }
            }
            model.addAttribute("list", list);
            model.addAttribute("major", branchService.Findall());
            return "/admin/students/main-student";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/students/profile/{id}")
    public String profile(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            model.addAttribute("student", studentService.findOne(id));
            Student student = new Student();
            student.setStuid(id);
            model.addAttribute("studentPass", studentAccountService.findByStudentId(student));
            model.addAttribute("parent", parentService.findParentByStudentId(student));
            model.addAttribute("major", branchService.Findall());
            return "admin/students/profile";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/students/importStudent", method = RequestMethod.GET)
    public String doImportStudent(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            return "/admin/students/importstudent";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/students/studentgroup", method = RequestMethod.GET)
    public String studentGroup(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            return "/admin/students/studentgroup";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/courses/importStudent", method = RequestMethod.GET)
    public String importStudentExcel(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            return "/admin/students/importstudent";
        } else {
            return "login/admin-login";
        }

    }

    @PostMapping(value = "/api/importdatastudentexcel")
    public String uploadFileStudentExcel(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        try
        {
            File f = new File(file.getOriginalFilename());
            File absolute = f.getAbsoluteFile();

            System.out.println(absolute.getPath());
            importstudentservice.importData(file);
        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("error", "Error");
            return "redirect:/admin/dashboard/students/importStudent";
        }
        redirectAttributes.addFlashAttribute("success", "Import Success");
        return "redirect:/admin/dashboard/students/importStudent";
    }
}
