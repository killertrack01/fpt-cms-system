package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.services.BranchService;
import com.example.fptacademysystem.services.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminSubjectController {
    
    @Autowired
    SubjectService service;
    @Autowired
    BranchService branchService;

    @RequestMapping(value = "/admin/dashboard/subject", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            List<SubjectDTO> list = new ArrayList<>();
            for(Subject s: service.Findall())
            {
                SubjectDTO sd=new SubjectDTO();

                sd.setSubjid(s.getSubjid());
                sd.setSubjnm(s.getSubjnm());
                sd.setShortnm(s.getShortnm());
                sd.setBranchid(s.getBranchid());
                Branch b= new Branch();
                b= branchService.findOne(s.getBranchid());
                sd.setBrandnm(b.getBranchnm());
                list.add(sd);
            }
            model.addAttribute("list",list);
            model.addAttribute("branch", branchService.Findall());
            return "admin/subjects/main-subject";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/subject/importSubject", method = RequestMethod.GET)
    public String doImportStudent(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)){
                return "/admin/subjects/importsubject";
            }else {
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }
    }
}
