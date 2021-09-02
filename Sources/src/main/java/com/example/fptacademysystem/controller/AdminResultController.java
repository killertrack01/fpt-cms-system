package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.services.ResultService;
import com.example.fptacademysystem.services.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminResultController {
    @Autowired
    ResultService resultService;
    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/admin/dashboard/results", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        if (session.getAttribute("staffroll") != null) {
            return "/admin/results/main-result";
        } else {
            return "login/admin-login";
        }
    }
}
