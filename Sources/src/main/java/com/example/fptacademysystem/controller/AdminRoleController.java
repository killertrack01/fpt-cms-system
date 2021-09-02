package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminRoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/admin/dashboard/role")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)){
                model.addAttribute("list", roleService.findAll());
                return "admin/role/main-role";
            } else {
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }
    }
}
