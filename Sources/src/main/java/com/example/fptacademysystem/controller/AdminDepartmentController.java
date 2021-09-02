package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.services.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminDepartmentController {
    
    @Autowired
    DepartmentService departmentServices;

    @RequestMapping(value = "/admin/dashboard/department", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)){
                model.addAttribute("list", departmentServices.Findall());
                return "admin/departments/main-department";
            }else {
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }

    }
}
