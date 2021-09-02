package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.services.BranchService;
import com.example.fptacademysystem.services.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/subject")
public class SubjectRestController {

    @Autowired
    SubjectService services;
    @Autowired
    BranchService branchService;

    @PostMapping(value = "/subjectCreate", produces = "application/json")
    public String postCreate(@RequestBody Subject s) {
        // Check unique
        JSONObject jsonObject = new JSONObject();
        List<Subject> list = services.Findall();
        String message = "";
        try {
            for (Subject Subject : list) {
                if (s.getSubjnm().equals(Subject.getSubjnm())) {
                    message = "Subject name";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
                if (s.getShortnm().equals(Subject.getShortnm())) {
                    message = "Subject name";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
            }
            services.create(s);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @GetMapping(value = "/subjectFind/{id}")
    public ResponseEntity<SubjectDTO> find(@PathVariable("id") int id) {
        Subject s = services.FindOne(id);
        SubjectDTO sd = new SubjectDTO();

        sd.setSubjid(s.getSubjid());
        sd.setShortnm(s.getShortnm());
        sd.setSubjnm(s.getSubjnm());
        Branch b = new Branch();
        b = branchService.findOne(s.getBranchid());
        sd.setBranchid(b.getBranchid());
        sd.setBrandnm(b.getBranchnm());

        return new ResponseEntity<>(sd, HttpStatus.OK);
    }

    @PutMapping(value = "/SubjectEdit", produces = "application/json")
    public String postEdit(@RequestBody Subject s) {
        // Check unique
        JSONObject jsonObject = new JSONObject();
        List<Subject> list = services.Findall();
        String message = "";
        try {
            for (Subject Subject : list) {
                if (!Subject.getSubjid().equals(s.getSubjid())) {
                    if (s.getSubjnm().equals(Subject.getSubjnm())) {
                        message = "Subject name";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    if (s.getShortnm().equals(Subject.getShortnm())) {
                        message = "Subject name";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                }
            }
            services.update(s);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @DeleteMapping(value = "/SubjectDelete/{id}")
    public ResponseEntity<Subject> postDelete(@PathVariable("id") int id) {
        services.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
