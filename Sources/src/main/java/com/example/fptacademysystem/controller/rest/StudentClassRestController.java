package com.example.fptacademysystem.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.StudentClassDTO;
import com.example.fptacademysystem.dto.StudentDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.NewClass;
import com.example.fptacademysystem.model.NewStudent;
import com.example.fptacademysystem.model.RenderStudentgroup;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.model.Timetable;
import com.example.fptacademysystem.services.StudentClassService;
import com.example.fptacademysystem.services.StudentGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentClassRestController {
    @Autowired
    StudentClassService studentClassService;
    @Autowired
    StudentGroupService studentGroupService;

    @PostMapping(value = "/admin/studentclass/postCreate", produces = "application/json")
    public void create(@RequestBody StudentClass sc) throws IOException {
        sc.setRemoveat("No");
        studentClassService.save(sc);
    }

    @GetMapping(value = "/admin/studentclass/findAllCourse", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> findAllCourse(){
        List<CourseDTO> list = new ArrayList<>();
        try{
            for (Courses courses : studentClassService.getAllCourse()) {
                CourseDTO c=new CourseDTO();
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

    @GetMapping(value = "/admin/studentclass/findAllStudentGroupByCourse/{courid}", produces = "application/json")
    public ResponseEntity<List<RenderStudentgroup>> findAllStudentGroupByCourse(@PathVariable("courid") int courid){
        return new ResponseEntity<>(studentClassService.getAllStudentGroupsForIndex(courid), HttpStatus.OK);

    }

    @GetMapping(value = "/admin/studentclass/findAllStudentForIndex/{stugroid}", produces = "application/json")
    public ResponseEntity<List<StudentDTO>> findAllStudentForIndex(@PathVariable("stugroid") int stugroid){
        List<StudentClass> studentid = new ArrayList<>();
        List<StudentDTO> student = new ArrayList<>();
        studentid = studentClassService.findAllStudentByStudentGroupIdForIndex(stugroid);
        for(StudentClass studentclass : studentid){
            StudentDTO sdto = new StudentDTO();
            sdto = studentClassService.findStudentById(studentclass.getStuid().getStuid());
            sdto.setStustatus(studentclass.getCass());
            student.add(sdto);
        }
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/NewStudent/{stugroid}", produces = "application/json")
    public ResponseEntity<List<NewStudent>> findNewStudent(@PathVariable("stugroid")int stugroid){
        StudentGroup sg = new StudentGroup();
        sg = studentGroupService.getStudentGroupByID(stugroid);
        BranchCampus bc = new BranchCampus();
        bc = sg.getBranchcamid();
        Branch b = new Branch();
        b = bc.getBranchid();
        return new ResponseEntity<>(studentClassService.findNewStudent(b.getBranchnm()),HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/findNewStudentgroup/{id}", produces = "application/json")
    public ResponseEntity<List<NewClass>> findNewStudentGroupByCourid(@PathVariable("id")int id){


        return new ResponseEntity<>(studentClassService.findNewClassByCourseId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/getNumberOfStudentInClass/{id}", produces = "application/json")
    public ResponseEntity<Integer> getNumberOfStudentInClass(@PathVariable("id")int id){
        return new ResponseEntity<>(studentClassService.numberOfStudentInClass(id), HttpStatus.OK);
    }



    // New
    @PostMapping(value = "/admin/studentclass/postChangeClass", produces = "application/json")
    public void createChangeClass(@RequestBody StudentClassDTO scdt) throws IOException {
        StudentClass newsc = new StudentClass();
        StudentClass oldsc = new StudentClass();
        Student s = new Student();
        StudentGroup newsg = new StudentGroup();
        Date newSubjStartDate = new Date();

        newSubjStartDate = studentClassService.findSubjectStartDay(scdt.getNewstugroid(), scdt.getRegissubj());
        s.setStuid(scdt.getStuid());
        newsg.setStugroid(scdt.getNewstugroid());

        //change to new class
        newsc.setRegissubj(scdt.getRegissubj());
        newsc.setCass(scdt.getCass());
        newsc.setRestudytime(newSubjStartDate);
        newsc.setSemstatus(scdt.getSemstatus());
        newsc.setStuid(s);
        newsc.setStugroid(newsg);
        newsc.setRemoveat("No");

        studentClassService.save(newsc);

        //update old class
        oldsc = studentClassService.findCurrentClassByStudentIdAndStugroid(scdt.getStuid(),scdt.getOldstugroid());
        oldsc.setCass("old class");

        studentClassService.save(oldsc);

    }

    @GetMapping(value = "/admin/studentclass/getCurrentSubject/{stugroid}", produces = "application/json")
    public ResponseEntity<Integer> getCurrentSubject(@PathVariable("stugroid")int id){
        return new ResponseEntity<>(studentClassService.findCurrentSubjectDetailId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/checkSubjectEnd/{stugroid}", produces = "application/json")
    public ResponseEntity<Integer> check(@PathVariable("stugroid")int stugroid){
        int subjdetailid=studentClassService.findCurrentSubjectDetailId(stugroid);
        if(studentClassService.checkSubjectEnd(stugroid,subjdetailid)){
            return new ResponseEntity<>(0,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(subjdetailid,HttpStatus.OK);
        }
    }

    @GetMapping(value = "/admin/studentclass/getAllStudentGroup/{courseid}", produces = "application/json")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroup(@PathVariable("courseid")int id){
        return new ResponseEntity<>(studentClassService.getStudentGroups(id), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/getAvailableStudentGroup/{courseid}/{stugroid}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> getAvailableStudentGroup(@PathVariable("courseid")int courseid, @PathVariable("stugroid")int stugroid){
        return new ResponseEntity<>(studentClassService.findAvailableStudentGroups(courseid,stugroid), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/getCurrentStudent/{stugroid}", produces = "application/json")
    public ResponseEntity<List<StudentDTO>> getCurrentStudent(@PathVariable("stugroid")int stugroid){
        List<StudentClass> studentid = new ArrayList<>();
        List<StudentDTO> student = new ArrayList<>();
        studentid = studentClassService.findAllStudentByStudentGroupId(stugroid);
        for(StudentClass studentclass : studentid){
            StudentDTO sdto = new StudentDTO();
            sdto = studentClassService.findStudentById(studentclass.getStuid().getStuid());

            student.add(sdto);
        }
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/getCompleteSubject/{stuid}", produces = "application/json")
    public ResponseEntity<List<SubjectDetailsDTO>> getCompleteSubject(@PathVariable("stuid")int stuid){
        List<Integer> list = studentClassService.getCompleteSubject(stuid);

        for(Integer id : list){
            for(int idcurrent : studentClassService.getCurrentLearningSubjectDetailIdByStudentId(stuid)){
                if(id == idcurrent){
                    list.remove(id);
                }
            }
        }
        List<SubjectDetailsDTO> sdlist = studentClassService.getSubjectNameAndSubjectDetailId(list);
        List<SubjectDetailsDTO> emptyList = new ArrayList<>();
        if(sdlist != null){
            return new ResponseEntity<>(sdlist, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(emptyList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/admin/studentclass/findStudentByRollnum/{rollnum}", produces = "application/json")
    public ResponseEntity<Student> findStudentByRollnum(@PathVariable("rollnum")String rollnum){
        return new ResponseEntity<>(studentClassService.findStudentByRollnum(rollnum), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/studentclass/getCurrentLearningSubjectDetailIdByStudentId/{stuid}", produces = "application/json")
    public ResponseEntity<List<Integer>> getCurrentLearningSubjectDetailIdByStudentId(@PathVariable("stuid")int stuid){
        return new ResponseEntity<>(studentClassService.getCurrentLearningSubjectDetailIdByStudentId(stuid), HttpStatus.OK);
    }

    //new
    @GetMapping(value = "/admin/studentclass/findAvailableStudentGroupsForReLearning/{stuid}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> findAvailableStudentGroupsForReLearning(@PathVariable("stuid")int stuid){
        List<StudentGroupDTO> stugro = studentClassService.findAvailableStudentGroupsForReLearning(stuid);
        List<StudentGroupDTO> emptyList = new ArrayList<>();
        if(stugro != null){
            return new ResponseEntity<>(stugro, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(emptyList, HttpStatus.OK);
        }

    }

    // New
    @PostMapping(value = "/admin/studentclass/postLearnAgain", produces = "application/json")
    public void createLearnAgain(@RequestBody StudentClassDTO scdt) throws IOException {
        StudentClass lasc = new StudentClass();
        Student s = new Student();
        StudentGroup newsg = new StudentGroup();
        Date newSubjStartDate = new Date();

        newSubjStartDate = studentClassService.findSubjectStartDay(scdt.getNewstugroid(), scdt.getRegissubj());
        s.setStuid(scdt.getStuid());
        newsg.setStugroid(scdt.getNewstugroid());

        //change to new class
        lasc.setRegissubj(scdt.getRegissubj());
        lasc.setCass(scdt.getCass());
        lasc.setRestudytime(newSubjStartDate);
        lasc.setSemstatus(scdt.getSemstatus());
        lasc.setStuid(s);
        lasc.setStugroid(newsg);
        lasc.setRemoveat("No");

        studentClassService.save(lasc);

    }
}
