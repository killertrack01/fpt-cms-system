package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminBranchController {

    @Autowired
    BranchService branchService;

    @RequestMapping(value = "/admin/dashboard/branch", method = RequestMethod.GET)
    public String page(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)){
                model.addAttribute("list", branchService.Findall());
                return "admin/branchs/main-branch";
            } else{
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }
    }
}
