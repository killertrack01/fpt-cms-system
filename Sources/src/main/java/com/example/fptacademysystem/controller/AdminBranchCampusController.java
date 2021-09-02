/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.fptacademysystem.dto.BranchCampusDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Campus;
import com.example.fptacademysystem.repository.CampusRepository;
import com.example.fptacademysystem.services.BranchCampusService;
import com.example.fptacademysystem.services.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kille
 */
@Controller
public class AdminBranchCampusController {

    @Autowired
    BranchCampusService branchCampusService;
    @Autowired
    BranchService branchService;
    @Autowired
    CampusRepository campusRepository;

    @RequestMapping(value = "/admin/dashboard/branchcampus", method = RequestMethod.GET)
    public String page(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            if (session.getAttribute("role").equals(1)) {

                List<BranchCampusDTO> render = new ArrayList<>();
                for (BranchCampus bc : branchCampusService.Findall()) {
                    BranchCampusDTO bcdto = new BranchCampusDTO();

                    bcdto.setBranchcamid(bc.getBranchcamid());
                    bcdto.setBranchcamnm(bc.getBranchcamnm());
                    bcdto.setAddress(bc.getAddress());
                    Branch b = new Branch();
                    b = branchService.findOne(bc.getBranchid().getBranchid());
                    bcdto.setBranchnm(b.getBranchnm());
                    Campus c = new Campus();
                    c = campusRepository.findById(bc.getCampusid().getCampusid()).get();
                    bcdto.setCampusnm(c.getCampusnm());
                    render.add(bcdto);
                }
                model.addAttribute("list", render);
                model.addAttribute("branch", branchService.Findall());
                model.addAttribute("campus", campusRepository.findAll());
                return "admin/branchcampus/main-branchcampus";
            } else {
                return "redirect:/admin/dashboard/access-denied";
            }
        } else {
            return "login/admin-login";
        }
    }

}
