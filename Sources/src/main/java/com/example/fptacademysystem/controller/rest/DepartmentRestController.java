package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.DepartmentDTO;
import com.example.fptacademysystem.model.Department;
import com.example.fptacademysystem.services.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/department")
public class DepartmentRestController {

    @Autowired
    DepartmentService services;

    @PostMapping(value = "/departmentCreate")
    public ResponseEntity<Department> postCreate(@RequestBody Department depart) {
        services.create(depart);
        return new ResponseEntity<>(depart, HttpStatus.OK);
    }

    @GetMapping(value = "/DepartmentFind/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> find(@PathVariable("id") int id) {
        Department d = services.FindOne(id);
        DepartmentDTO ddto = new DepartmentDTO();
        ddto.setDepid(d.getDepid());
        ddto.setDepnm(d.getDepnm());
        ddto.setDescriptions(d.getDescriptions());
        return new ResponseEntity<>(ddto, HttpStatus.OK);
    }

    @PutMapping(value = "/DepartmentEdit")
    public ResponseEntity<Department> postEdit(@RequestBody Department depart) {
        services.update(depart);
        return new ResponseEntity<>(depart, HttpStatus.OK);
    }

    
    @DeleteMapping(value = "/DepartmentDelete/{id}")
    public ResponseEntity<Department> postDelete(@PathVariable("id") int id) {
        services.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
