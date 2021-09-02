package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.ExamDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.services.IExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/admin/exam")
public class ExamRestController {

    @Autowired
    IExam services;

    @GetMapping(value = "/findAllCourse", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> findAllCourse(){
        List<CourseDTO> list = new ArrayList<>();
        try{
            for (Courses courses : services.findAllCourse()) {
                CourseDTO c = new CourseDTO();
                c.setCourid(courses.getCourid());
                c.setCournm(courses.getCournm());
                c.setBranchid(courses.getBranchid().getBranchid());

                list.add(c);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findStudentgroup/{id}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> findStudentGroupByCourid(@PathVariable("id")int id){

        List<StudentGroupDTO> list = new ArrayList<>();
        try{
            Courses courses =new Courses();
            courses.setCourid(id);
            for(StudentGroup student : services.findStudentGroupByCourid(courses)){
                StudentGroupDTO s = new StudentGroupDTO();

                s.setStugroid(student.getStugroid());
                s.setStugronm(student.getStugronm());
                s.setOpeningdate(student.getOpeningdate());
                s.setSession(student.getSession());
                s.setShift(student.getShift());
                s.setCourid(student.getCourid().getCourid());


                list.add(s);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findAllSemester", produces = "application/json")
    public ResponseEntity<List<SemesterDTO>> findAllSemester(){
        List<SemesterDTO> list = new ArrayList<>();
        try{
            for (Semester semester : services.findAllSemester()) {
                SemesterDTO s=new SemesterDTO();
                s.setSemid(semester.getSemid());
                s.setSemnm(semester.getSemnm());

                list.add(s);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findSubjectDetails", produces = "application/json")
    public ResponseEntity<List<SubjectDetailsDTO>> findSubjectDetails(int stugroid,int semid){
        try{
            return new ResponseEntity<>(services.findAvailableSubjectDetailsById(stugroid, semid), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create", produces = "application/json")
    public boolean createExam(@RequestBody ExamDTO examDTO) throws IOException{
        services.save(examDTO);
        return true;
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public boolean editExam(@RequestBody ExamDTO examDTO) throws IOException{
        services.save(examDTO);
        return true;
    }

    @GetMapping(value = "/findExamById/{id}", produces = "application/json")
    public ResponseEntity<ExamDTO> findExam(@PathVariable("id")int id) throws IOException{
        return new ResponseEntity<>(services.findExamById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/deleteExam/{id}", produces = "application/json")
    public void deleteExam(@PathVariable("id")int id) throws IOException{
        services.delete(id);
    }

    @GetMapping(value = "/checkExamComplete/{examid}", produces = "application/json")
    public ResponseEntity<Boolean> checkExamComplete(@PathVariable("examid")int examid) throws IOException{
        return new ResponseEntity<>(services.checkIfExamComplete(examid),HttpStatus.OK);
    }

    @GetMapping(value = "/checkExamType/{stugroid}/{subjdetailid}", produces = "application/json")
    public ResponseEntity<List<Integer>> checkExamType(@PathVariable("stugroid")int stugroid,@PathVariable("subjdetailid")int subjdetailid) throws IOException{
        return new ResponseEntity<>(services.checkExamType(stugroid,subjdetailid),HttpStatus.OK);
    }

    @GetMapping(value = "/checkExamBout/{stugroid}/{subjdetailid}/{examType}", produces = "application/json")
    public ResponseEntity<Integer> checkExamBout(@PathVariable("stugroid")int stugroid,@PathVariable("subjdetailid")int subjdetailid,@PathVariable("examType")String examType) throws IOException{
        return new ResponseEntity<>(services.checkExamBout(stugroid,subjdetailid,examType),HttpStatus.OK);
    }

    @GetMapping(value = "/findSubjectEndDay/{stugroid}/{subjdetailid}", produces = "application/json")
    public ResponseEntity<Date> findSubjectEndDay(@PathVariable("stugroid")int stugroid,@PathVariable("subjdetailid")int subjdetailid) throws IOException{
        return new ResponseEntity<>(services.findSubjectEndDay(stugroid,subjdetailid),HttpStatus.OK);
    }

}
