package com.example.fptacademysystem.controller.rest;

import java.util.ArrayList;
import java.util.List;

import com.example.fptacademysystem.dto.BranchDTO;
import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.repository.CourseRepository;
import com.example.fptacademysystem.repository.SemesterRepository;
import com.example.fptacademysystem.services.BranchService;

import com.example.fptacademysystem.services.CoursesService;
import com.example.fptacademysystem.services.SubjectService;
import com.example.fptacademysystem.services.SubjectdetailServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/admin/subjectdetails")
public class CourseRestController {

    // Branch services
    @Autowired
    BranchService branchService;
    // subject services
    @Autowired
    SubjectService subjectService;
    // Semester repository
    @Autowired
    SemesterRepository semesterRepository;
    // Subject detail services
    @Autowired
    SubjectdetailServices subjectDetailsService;
    // Course services
    @Autowired
    CoursesService coursesService;
    // Course Repo
    @Autowired
    CourseRepository courseRepository;

    // get all branch
    @GetMapping(value = "/findAllBranch", produces = "application/json")
    public ResponseEntity<List<BranchDTO>> findAllBranch() {
        List<BranchDTO> list = new ArrayList<>();
        try {
            for (Branch branch : branchService.Findall()) {
                BranchDTO bdto = new BranchDTO();
                bdto.setBranchid(branch.getBranchid());
                bdto.setBranchnm(branch.getBranchnm());
                bdto.setRemoveat(branch.getRemoveat());
                list.add(bdto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // get all semester
    @GetMapping(value = "/findAllSemester", produces = "application/json")
    public ResponseEntity<List<SemesterDTO>> findAllSemester() {
        List<SemesterDTO> list = new ArrayList<>();
        try {
            for (Semester semester : semesterRepository.findAll()) {
                SemesterDTO sedto = new SemesterDTO();
                sedto.setSemid(semester.getSemid());
                sedto.setSemnm(semester.getSemnm());
                list.add(sedto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // get all subject
    @GetMapping(value = "/findAllSubject/{id}", produces = "application/json")
    public ResponseEntity<List<SubjectDTO>> findAllSubject(@PathVariable("id") int id) {
        List<SubjectDTO> list = new ArrayList<>();
        try {
            for (Subject subject : subjectDetailsService.findSubjectBranchid(id)) {
                SubjectDTO sdto = new SubjectDTO();
                sdto.setSubjid(subject.getSubjid());
                sdto.setSubjnm(subject.getSubjnm());
                sdto.setShortnm(subject.getShortnm());
                sdto.setBranchid(subject.getBranchid());
                Branch b = branchService.findOne(subject.getBranchid());
                sdto.setBrandnm(b.getBranchnm());
                sdto.setRemoveat(subject.getRemoveat());
                list.add(sdto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/createnewcourse", produces = "application/json")
    public String newcourse(@RequestBody Courses c) {

        // Check unique
        JSONObject jsonObject = new JSONObject();
        List<Courses> cList = courseRepository.listAll();
        String message = "";
        try {
            for (Courses courses : cList) {
                if (c.getCournm().equals(courses.getCournm())) {
                    message = "Courses name";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
            }
            coursesService.create(c);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonObject.toString();
    }

    // Create subject details
    @PostMapping(value = "/create", produces = "application/json")
    public boolean create(@RequestBody List<SubjectDetailsDTO> details) {
        subjectDetailsService.create(details);
        return true;
    }

    // Find all courses
    @GetMapping(value = "/findCourses/{id}", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> findAllCourses(@PathVariable("id") int id) {
        List<CourseDTO> list = new ArrayList<>();
        Branch b = branchService.findOne(id);
        try {
            for (Courses courses : courseRepository.listCoursesByBranchId(b)) {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setCourid(courses.getCourid());
                courseDTO.setCournm(courses.getCournm());
                list.add(courseDTO);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Find all courses by courses id
    @GetMapping(value = "findCoursesByCoursesId/{id}",produces = "application/json")
    public ResponseEntity<CourseDTO> findCoursesById(@PathVariable("id")int id) {
        try {
            Courses courses =coursesService.FindOne(id);
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourid(courses.getCourid());
            courseDTO.setCournm(courses.getCournm());

            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // find all subject_details for each semester
    @GetMapping(value = "/findSubjectDetails/{semid}/{courid}", produces = "application/json")
    public ResponseEntity<List<SubjectDetailsDTO>> findAllSubjectDetails(@PathVariable("semid") int semid,
                                                                         @PathVariable("courid") int courid) {
        try {
            List<SubjectDetailsDTO> list = subjectDetailsService.findSubjid(semid, courid);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
