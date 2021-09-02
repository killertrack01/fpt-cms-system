/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.FeedbackDTO;
import com.example.fptacademysystem.dto.StudentFeedbackDTO;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentFeedback;
import com.example.fptacademysystem.repository.LecturerRepository;
import com.example.fptacademysystem.repository.SemesterRepository;
import com.example.fptacademysystem.repository.StudentFeedbackRepository;
import com.example.fptacademysystem.repository.StudentGroupRepository;
import com.example.fptacademysystem.repository.SubjectDetailRepository;
import com.example.fptacademysystem.repository.SubjectRepository;
import com.example.fptacademysystem.services.FeedbackService;
import com.example.fptacademysystem.services.LecturerService;
import com.example.fptacademysystem.services.StudentGroupService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
public class StudentFeedbackRestController {

    @Autowired
    FeedbackService services;

    @Autowired
    LecturerService lecserService;

    @Autowired
    StudentGroupService stugroService;

    @Autowired
    StudentGroupRepository sgrouprepo;

    @Autowired
    SemesterRepository semRepo;

    @Autowired
    SubjectDetailRepository sdetailrepo;

    @Autowired
    SubjectRepository subrepo;

    @Autowired
    LecturerRepository lecturerRepo;

    @Autowired
    StudentFeedbackRepository studentfeedrepo;

    @GetMapping(value = "/api/students/feedback", produces = "application/json")
    public ResponseEntity<List<FeedbackDTO>> findAll(HttpSession session) {
        String rollnum = (String) session.getAttribute("studentroll");
        List<StudentClass> listStuClass = services.findCurrentClassByStudentId(rollnum);

        //get all feedback
        List<FeedbackDTO> list = services.findAll();
        List<StudentFeedback> listStuFeed = services.findAllStudentFeedback();
        try
        {
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            List<FeedbackDTO> listRemove = new ArrayList<>();
            for (FeedbackDTO feedbackDTO : list)
            {
                LocalDate ldatetoday = Instant.ofEpochMilli(currentDate.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDate ldatedto = Instant.ofEpochMilli(format.parse(feedbackDTO.getEnddatefeedback()).getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                long difference = Period.between(ldatetoday, ldatedto).getDays();
                if (!format.parse(feedbackDTO.getDateoffeedback()).before(currentDate) || difference < 0 || feedbackDTO.getFeedbackstatus().equals("false"))
                {
                    listRemove.add(feedbackDTO);
                }
            }
            for (FeedbackDTO feedbackDTO : list)
            {
                for (StudentClass stuclass : listStuClass)
                {
                    String stugroid = stuclass.getStugroid().getStugroid().toString();
                    if (!feedbackDTO.getStugroid().equals(stugroid))
                    {
                        listRemove.add(feedbackDTO);
                    }
                }
            }
            list.removeAll(listRemove);


            list.forEach(feedbackDTO ->
            {
                listStuFeed.stream().filter(student -> (Integer.parseInt(feedbackDTO.getFeedbackid()) == student.getFeedbackid().getFeedbackid())).forEachOrdered(student ->
                {
                    listStuClass.stream().filter(studentClass -> (Objects.equals(student.getStuid().getStuid(), studentClass.getStuid().getStuid()))).forEachOrdered(_item ->
                    {
                        feedbackDTO.setCheck(true);
                    });
                });
            });
        } catch (ParseException ex)
        {
            Logger.getLogger(StudentFeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/api/students/feedback/post", produces = "application/json")
    public String postCreate(@RequestBody StudentFeedbackDTO dto) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            if (dto.getAns1() == null || dto.getAns2() == null || dto.getAns3() == null || dto.getAns4() == null || dto.getAns5() == null)
            {
                jsonObject.put("title", "error");
                jsonObject.put("message", "Choose All Selection");
                return jsonObject.toString();
            }
            services.saveStudentFeedback(dto);
            jsonObject.put("title", "success");
        } catch (JSONException ex)
        {
            Logger.getLogger(StudentFeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @PostMapping(value = "/api/students/feedback/updatenote", produces = "application/json")
    public String updateNote(@RequestBody StudentFeedbackDTO dto) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            services.updateNote(dto);
            jsonObject.put("title", "success");
        } catch (JSONException ex)
        {
            Logger.getLogger(StudentFeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }
}
