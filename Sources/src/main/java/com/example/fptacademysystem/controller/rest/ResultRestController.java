package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.ExamDTO;
import com.example.fptacademysystem.dto.ResultDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.Result;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.services.ResultService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ngoho
 */
@RestController
@RequestMapping("/api/admin/result")
public class ResultRestController {
    @Autowired
    ResultService services;

    @PostMapping(value = "/saveResult", produces = "application/json")
    public boolean postCreate(@RequestBody ResultDTO  resultdt){
        services.create(resultdt);
        return true;
    }
    @GetMapping(value = "/findAllCourse", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> findAllCourse(){
        List<CourseDTO> list = new ArrayList<>();
        try{
            for (Courses courses : services.findAllCourse()) {
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

//    @GetMapping(value = "/findStudentByStudentGroup/{id}", produces = "application/json")
//    public ResponseEntity<List<StudentDTO>> findStudentByStudentGroup(@PathVariable("id")int id,int examasmid,int examobjid,int subjdetailsid){
//        List<StudentDTO> list = new ArrayList<>();
//        int attsum = services.getAllSubjectCount(id, subjdetailsid);
//        try{
//            StudentGroup stdgroup = new StudentGroup();
//            stdgroup.setStugroid(id);
//
//            for (StudentClass stClass : services.findStudentByStudentGroupAndSubjectDetail(stdgroup,subjdetailsid)) {
//                int attcount = services.getStudentAttendance(stClass.getStuid().getStuid(), subjdetailsid);
//                // Declare New Student Object
//                Student stud = new Student();
//                stud = services.findStudentByStudentId(stClass.getStuid().getStuid());
//                if((attcount/attsum)*100 < 0){
//                    stud.setStustatus("unavailable");
//                }
//                StudentDTO stdto = new StudentDTO();
//                Result asmResult = new Result();
//                Result objResult = new Result();
//
//                asmResult = services.findResultByStudentIdAndExamid(stud.getStuid(), examasmid);
//                objResult = services.findResultByStudentIdAndExamid(stud.getStuid(), examobjid);
//
//                if(asmResult != null){
//                    stdto.setAsmResultId(asmResult.getResultid());
//                    stdto.setAsmResult(asmResult.getExamresults());
//                }
//                if(objResult != null){
//                    stdto.setObjResultId(objResult.getResultid());
//                    stdto.setObjResult(objResult.getExamresults());
//                }
//
//                stdto.setStuid(stud.getStuid());
//                stdto.setFullnm(stud.getFullnm());
//                stdto.setRollnum(stud.getRollnum());
//                stdto.setStustatus(stud.getStustatus());
//                list.add(stdto);
//            }
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(value = "/findStudentByStudentGroup/{id}", produces = "application/json")
    public ResponseEntity<List<StudentDTO>> findStudentByStudentGroup(@PathVariable("id")int id,int examasmid,int examobjid,int subjdetailsid){
        List<StudentDTO> list = new ArrayList<>();

        try{
            StudentGroup stdgroup = new StudentGroup();
            stdgroup.setStugroid(id)
            ;

            for (StudentClass stClass : services.findStudentByStudentGroupAndSubjectDetail(stdgroup,subjdetailsid)) {
                float attcount = services.getStudentAttendance(stClass.getStuid().getStuid(), subjdetailsid);
                float attsum = services.getAllSubjectCount(id, subjdetailsid);
                int attend=(int) ((attcount/attsum)*100);
                // Declare New Student Object
                Student stud = new Student();
                stud = services.findStudentByStudentId(stClass.getStuid().getStuid());
                if(attend < 70){
                    stud.setStustatus("unavailable");
                }
                StudentDTO stdto = new StudentDTO();
                Result asmResult = new Result();
                Result objResult = new Result();

                asmResult = services.findResultByStudentIdAndExamid(stud.getStuid(), examasmid);
                objResult = services.findResultByStudentIdAndExamid(stud.getStuid(), examobjid);

                if(asmResult != null){
                    stdto.setAsmResultId(asmResult.getResultid());
                    stdto.setAsmResult(asmResult.getExamresults());
                }
                if(objResult != null){
                    stdto.setObjResultId(objResult.getResultid());
                    stdto.setObjResult(objResult.getExamresults());
                }

                stdto.setStuid(stud.getStuid());
                stdto.setFullnm(stud.getFullnm());
                stdto.setRollnum(stud.getRollnum());
                stdto.setStustatus(stud.getStustatus());
                list.add(stdto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findExamByStudentGroupAndSubjectDetail", produces = "application/json")
    public ResponseEntity<List<ExamDTO>> findExamByStudentGroupAndSubjectDetail(int stugroid,int subjdetailsid,int bout){
        List<ExamDTO> list = new ArrayList<>();
        StudentGroup stugro=new StudentGroup();
        stugro.setStugroid(stugroid);

        try{

            for (Exam exam : services.findExamBySubjectDetailAndStudentGroup(stugro,subjdetailsid)) {
                if(exam.getBout() == bout){
                    ExamDTO examdto=new ExamDTO();
                    examdto.setExamid(exam.getExamid());
                    examdto.setExamcodelogin(exam.getExamcodelogin());
                    examdto.setExamday(exam.getExamday().toString());
                    examdto.setExamtype(exam.getExamtype());
                    examdto.setBout(exam.getBout()+"");
                    examdto.setRoomid(exam.getRoomid().getRoomid().toString());
                    examdto.setStugroid(exam.getStugroid().getStugroid().toString());
                    examdto.setSubjdetailsid(exam.getSubjdetailsid()+"");
                    list.add(examdto);
                }
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
                StudentGroupDTO s =new StudentGroupDTO();

                s.setStugroid(student.getStugroid());
                s.setStugronm(student.getStugronm());
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
    @GetMapping(value = "/findSubjectDetails/{semid}/{stugroid}", produces = "application/json")
    public ResponseEntity<List<SubjectDetailsDTO>> findSubjectDetails(@PathVariable("semid") int semid,@PathVariable("stugroid") int stugroid){
        return new ResponseEntity<>(services.findSubjectDetails(semid,stugroid), HttpStatus.OK);
    }

}