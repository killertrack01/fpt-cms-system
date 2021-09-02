package com.example.fptacademysystem.controller;


import javax.servlet.http.HttpSession;

import com.example.fptacademysystem.services.StudentClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminStudentClassController {

    @Autowired
    StudentClassService studentClassService;

    @RequestMapping(value = "/admin/dashboard/studentclass", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession session) {
        if(session.getAttribute("staffroll") != null) {
            return "/admin/studentclass/main-studentclass";
        } else {
            return "login/admin-login";
        }
       
    }
}
