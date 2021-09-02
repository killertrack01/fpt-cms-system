package com.example.fptacademysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminStudentGroupController {

    @RequestMapping(value = "/admin/dashboard/studentgroup", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            return "admin/studentgroup/main-studentgroup";
        } else {
            return "login/admin-login";
        }
    }
}
