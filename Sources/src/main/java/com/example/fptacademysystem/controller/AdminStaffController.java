package com.example.fptacademysystem.controller;


import com.example.fptacademysystem.services.DepartmentService;
import com.example.fptacademysystem.services.RoleService;
import com.example.fptacademysystem.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminStaffController {

    @Autowired
    StaffService staffService;

    @Autowired
    DepartmentService depService;

    @Autowired
    RoleService roleService;


    @RequestMapping(value = "/admin/dashboard/staffs", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)){
                model.addAttribute("list", staffService.findAll());
                model.addAttribute("listDept", depService.Findall());
                model.addAttribute("listRole", roleService.findAll());
                return "/admin/staffs/main-staff";
            } else {
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }

    }

    @RequestMapping(value = "/admin/dashboard/staffs/profile/{staffid}", method = RequestMethod.GET)
    public String profile(@PathVariable("staffid")int staffid, Model model, HttpSession session){
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)){
                model.addAttribute("listDept", depService.Findall());
                model.addAttribute("listRole", roleService.findAll());
                model.addAttribute("staff", staffService.findStaffByStaffid(staffid));
                return "/admin/staffs/profile";
            } else {
                return "redirect:/admin/dashboard/access-denied";
            }  
        } else {
            return "login/admin-login";
        }
    }
}
