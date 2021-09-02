/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.controller.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.fptacademysystem.dto.BranchCampusDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Campus;
import com.example.fptacademysystem.repository.CampusRepository;
import com.example.fptacademysystem.services.BranchCampusService;
import com.example.fptacademysystem.services.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kille
 */
@RestController
@RequestMapping("/api/admin/branchcampus")
public class BranchCampusRestController {
    @Autowired
    BranchCampusService branchCampusService;
    @Autowired
    BranchService branchService;
    @Autowired
    CampusRepository campusRepository;
    // Create new branch campus
    @PostMapping(value = "/Create")
    public ResponseEntity<BranchCampus> postCreate(@RequestBody BranchCampus branchCampus) {
        branchCampusService.create(branchCampus);
        return new ResponseEntity<>(branchCampus, HttpStatus.OK);
    }

    @GetMapping(value = "/Find/{id}")
    public ResponseEntity<BranchCampusDTO> find(@PathVariable("id")int id) {
        BranchCampus s= branchCampusService.findOne(id);
        BranchCampusDTO bcdto= new BranchCampusDTO();

        bcdto.setBranchcamid(s.getBranchcamid());
        bcdto.setBranchcamnm(s.getBranchcamnm());
        bcdto.setBranchid(s.getBranchid().getBranchid());
        bcdto.setAddress(s.getAddress());
        Branch b = new Branch();
        b= branchService.findOne(s.getBranchid().getBranchid());
        bcdto.setBranchnm(b.getBranchnm());
        bcdto.setCampusid(s.getCampusid().getCampusid());
        Campus ca=new Campus();
        ca=campusRepository.findById(s.getCampusid().getCampusid()).get();
        bcdto.setCampusnm(ca.getCampusnm());

        return new ResponseEntity<>(bcdto, HttpStatus.OK);
    }
    @PutMapping(value = "/edit")
    public ResponseEntity<BranchCampus> postEdit(@RequestBody BranchCampus s){
        branchCampusService.update(s);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PutMapping(value = "/Delete/{id}")
    public ResponseEntity<BranchCampus> postDelete(@PathVariable("id")int id){
        branchCampusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
