package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.BranchDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/admin/branch")
public class BranchRestController {

    @Autowired
    BranchService branchService;

    @PostMapping(value = "/branchCreate")
    public ResponseEntity<Branch> postCreate(@RequestBody Branch branch) {
        branchService.create(branch);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    @GetMapping(value = "/BranchFind/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<BranchDTO> find(@PathVariable("id") int id) {
        Branch b= branchService.findOne(id);
        BranchDTO bd=new BranchDTO();
        bd.setBranchid(b.getBranchid());
        bd.setBranchnm(b.getBranchnm());
        return new ResponseEntity<>(bd, HttpStatus.OK);
    }

    @PutMapping(value = "/BranchEdit")
    public ResponseEntity<Branch> postEdit(@RequestBody Branch branch) {
        branchService.update(branch);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }


    @PutMapping(value = "/BranchDelete/{id}")
    public ResponseEntity<Branch> postDelete(@PathVariable("id") int id) {
        branchService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
