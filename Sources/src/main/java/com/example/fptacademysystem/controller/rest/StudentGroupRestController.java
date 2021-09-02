package com.example.fptacademysystem.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fptacademysystem.dto.BranchCampusDTO;
import com.example.fptacademysystem.dto.BranchDTO;
import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.FindStudentgroup;
import com.example.fptacademysystem.model.GetBranchCount;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.RenderStudentgroup;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.services.StudentGroupService;

@RestController
@RequestMapping("/api/admin/studentgroup")
public class StudentGroupRestController {

    @Autowired
    StudentGroupService studentGroupService;

    @RequestMapping(value = "/createStudentGroup")
    public boolean createStudentGroup(@RequestBody StudentGroupDTO sgDTO) throws IOException {
        Calendar cn = Calendar.getInstance();
        cn.setTime(sgDTO.getOpeningdate());
        GetBranchCount gbc = studentGroupService.getFirstCharBranAndCount(sgDTO.getCourid());
        Courses couid = new Courses();
        couid.setCourid(sgDTO.getCourid());
        BranchCampus getBranchCampuID = new BranchCampus();
        getBranchCampuID.setBranchcamid(sgDTO.getBranchcampus());

        String dayOfMonth = String.valueOf(cn.get(Calendar.DAY_OF_MONTH)).length() < 2
                ? "0".concat(String.valueOf(cn.get(Calendar.DAY_OF_MONTH)))
                : String.valueOf(cn.get(Calendar.DAY_OF_MONTH));
        String monthB = String.valueOf(cn.get(Calendar.MONTH) + 1).length() < 2
                ? "0".concat(String.valueOf(cn.get(Calendar.MONTH) + 1))
                : String.valueOf(cn.get(Calendar.MONTH) + 1);

        StudentGroup sg = new StudentGroup();
        sg.setStugroid(sgDTO.getStugroid());
        sg.setStugronm(gbc.getFirstchar() + (gbc.getBranchamount() + 1) + '.' + dayOfMonth + monthB + '.'
                + sgDTO.getSession() + sgDTO.getShift());
        sg.setOpeningdate(sgDTO.getOpeningdate());
        sg.setSession(sgDTO.getSession());
        sg.setShift(sgDTO.getShift());
        sg.setBranchcamid(getBranchCampuID);
        sg.setCourid(couid);
        sg.setRemoveat("No");
        studentGroupService.createStudentGroup(sg);
        return true;
    }

    @RequestMapping(value = "/updateStudentGroup")
    public boolean updateStudentGroup(@RequestBody StudentGroupDTO sgDTO) throws IOException {
        Calendar cn = Calendar.getInstance();
        cn.setTime(sgDTO.getOpeningdate());
        String dayOfMonth = String.valueOf(cn.get(Calendar.DAY_OF_MONTH)).length() < 2
                ? "0".concat(String.valueOf(cn.get(Calendar.DAY_OF_MONTH)))
                : String.valueOf(cn.get(Calendar.DAY_OF_MONTH));
        String monthB = String.valueOf(cn.get(Calendar.MONTH) + 1).length() < 2
                ? "0".concat(String.valueOf(cn.get(Calendar.MONTH) + 1))
                : String.valueOf(cn.get(Calendar.MONTH) + 1);
        Courses couid = new Courses();
        couid.setCourid(sgDTO.getCourid());
        BranchCampus branchcampusid = new BranchCampus();
        branchcampusid.setBranchcamid(sgDTO.getBranchcampus());

        // get 2 character from branch name


        // get index of Student Group Name
        StudentGroup getIndexOfName = studentGroupService.getStudentGroupByID(sgDTO.getStugroid());
        // No Change Courses
        if (getIndexOfName.getCourid().getCourid() == sgDTO.getCourid()) {
            GetBranchCount gbcNoChange = studentGroupService.getFirstCharBranAndCount(sgDTO.getCourid());	// Find data of old courses
            String indexName = getIndexOfName.getStugronm().substring(0, (getIndexOfName.getStugronm().indexOf("."))); // Ap20.1908.M0 => Ap20
            String resultIndexName = indexName.substring(1, indexName.length()); // Ap20 => 20
            String stugroName = gbcNoChange.getFirstchar() + resultIndexName + '.' + dayOfMonth + monthB + '.' + sgDTO.getSession() + sgDTO.getShift();
            studentGroupService.updateStudentGroup(stugroName, sgDTO.getOpeningdate(), sgDTO.getSession(),
                    sgDTO.getShift(), couid, branchcampusid, sgDTO.getStugroid());
        }else {		// Change Courses
            GetBranchCount gbcChange = studentGroupService.getFirstCharBranAndCount(sgDTO.getCourid());  // Find data of new courses
            String stugroName = gbcChange.getFirstchar() + (gbcChange.getBranchamount()+1) + '.' + dayOfMonth + monthB + '.' + sgDTO.getSession() + sgDTO.getShift();
            studentGroupService.updateStudentGroup(stugroName, sgDTO.getOpeningdate(), sgDTO.getSession(),
                    sgDTO.getShift(), couid, branchcampusid, sgDTO.getStugroid());
        }

        return true;
    }

    @RequestMapping(value = "/removeStudentGroup/{id}")
    public boolean removeStudentGroup(@PathVariable("id") int id) throws IOException {
        studentGroupService.deleteStudentGroup(id);
        return true;
    }

    //  Check Date: 10-07-2021
    @GetMapping(value = "/getAllBranchCampus", produces = "application/json")
    public ResponseEntity<List<BranchCampusDTO>> getAllBranchCampus() {
        List<BranchCampusDTO> list = new ArrayList<>();
        try {
            for (BranchCampus branchCampus : studentGroupService.getAllBranchCampus()) {
                BranchCampusDTO bcDTO = new BranchCampusDTO();
                bcDTO.setBranchcamid(branchCampus.getBranchcamid());
                bcDTO.setBranchcamnm(branchCampus.getBranchcamnm());
                bcDTO.setAddress(branchCampus.getAddress());
                bcDTO.setBranchid(branchCampus.getBranchid().getBranchid());
                bcDTO.setCampusid(branchCampus.getCampusid().getCampusid());
                list.add(bcDTO);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

////  Check Date: 10-07-2021
//    @GetMapping(value = "/getCourseByBranchID", produces = "application/json")
//    public ResponseEntity<List<CourseDTO>> getCourseByBranchID() {
//        List<CourseDTO> list = new ArrayList<>();
//        try {
//            for (Courses courses : studentGroupService.getAllCourses()) {
//                CourseDTO c = new CourseDTO();
//                c.setCourid(courses.getCourid());
//                c.setCournm(courses.getCournm());
//                c.setBranchid(courses.getBranchid().getBranchid());
//
//                list.add(c);
//            }
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(value = "/getCourseByBranch/{branchid}", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> getCourseByBranch(@PathVariable("branchid") int branchid) {
        List<CourseDTO> list = new ArrayList<>();
        Branch getID = new Branch();
        getID.setBranchid(branchid);
        try {
            for (Courses courses : studentGroupService.getCoursesByBranch(getID)) {
                CourseDTO c = new CourseDTO();
                c.setCourid(courses.getCourid());
                c.setCournm(courses.getCournm());
                c.setBranchid(courses.getBranchid().getBranchid());

                list.add(c);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getSubjectByBranch/{branchid}", produces = "application/json")
    public ResponseEntity<List<SubjectDTO>> getSubjectByBranch(@PathVariable("branchid") int branchid) {
        List<SubjectDTO> list = new ArrayList<>();
        try {
            for (Subject sub : studentGroupService.getSubjectByBranch(branchid)) {
                SubjectDTO c = new SubjectDTO();
                c.setSubjid(sub.getSubjid());
                c.setShortnm(sub.getShortnm());
                c.setSubjnm(sub.getSubjnm());
                c.setBranchid(sub.getBranchid());
                c.setRemoveat(sub.getRemoveat());
                list.add(c);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(value = "/getAllRoom", produces = "application/json")
//    public ResponseEntity<List<RoomDTO>> getAllRoom() {
//        List<RoomDTO> list = new ArrayList<>();
//        try {
//            for (Room room : studentGroupService.getAllRoom()) {
//                RoomDTO c = new RoomDTO();
//                c.setRoomid(room.getRoomid());
//                c.setRoomnm(room.getRoomnm());
//                c.setRemoveat(room.getRemoveat());
//                list.add(c);
//            }
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    //  Check Date: 10-07-2021
    @GetMapping(value = "/getAllBranch", produces = "application/json")
    public ResponseEntity<List<BranchDTO>> getAllBranch() {
        List<BranchDTO> list = new ArrayList<>();
        try {
            for (Branch branch : studentGroupService.getAllBranch()) {
                BranchDTO b = new BranchDTO();
                b.setBranchid(branch.getBranchid());
                b.setBranchnm(branch.getBranchnm());
                list.add(b);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllLecturer", produces = "application/json")
    public ResponseEntity<List<LecturerDTO>> getAllLecturer() {
        List<LecturerDTO> list = new ArrayList<>();
        try {
            for (Lecturer lec : studentGroupService.getAllLecturer()) {
                LecturerDTO c = new LecturerDTO();
                c.setLecturid(String.valueOf(lec.getLecturid()));
                c.setFullnm(lec.getFullnm());
                c.setRollnum(lec.getRollnum());
                list.add(c);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllSubject", produces = "application/json")
    public ResponseEntity<List<SubjectDTO>> getAllSubject() {
        List<SubjectDTO> list = new ArrayList<>();
        try {
            for (Subject sub : studentGroupService.getAllSubject()) {
                SubjectDTO c = new SubjectDTO();
                c.setSubjid(sub.getSubjid());
                c.setShortnm(sub.getShortnm());
                c.setSubjnm(sub.getSubjnm());
                c.setBranchid(sub.getBranchid());
                c.setRemoveat(sub.getRemoveat());
                list.add(c);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllStudentGroup", produces = "application/json")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroup() {
        List<RenderStudentgroup> list = new ArrayList<>();
        try {
            for (RenderStudentgroup stugrop : studentGroupService.getAllStudentGroup()) {
                list.add(stugrop);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllStudentGroupBySubject/{id}", produces = "application/json")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroupBySubject(@PathVariable("id") int id) {
        List<RenderStudentgroup> list = new ArrayList<>();
        try {
            for (RenderStudentgroup stugrop : studentGroupService.getAllStudentGroupBySubject(id)) {
                list.add(stugrop);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllStudentGroupByCourses/{id}", produces = "application/json")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroupByCourses(@PathVariable("id") int id) {
        List<RenderStudentgroup> list = new ArrayList<>();
        try {
            for (RenderStudentgroup stugrop : studentGroupService.getAllStudentGroupByCourses(id)) {
                list.add(stugrop);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getStudentGroupById/{id}", produces = "application/json")
    public ResponseEntity<StudentGroupDTO> getStudentGroupById(@PathVariable("id") int id) {
        try {
            StudentGroup oneRow = studentGroupService.getStudentGroupByID(id);
            StudentGroupDTO stuDto = new StudentGroupDTO();
            stuDto.setStugroid(oneRow.getStugroid());
            stuDto.setStugronm(oneRow.getStugronm());
            stuDto.setOpeningdate(oneRow.getOpeningdate());
            stuDto.setSession(oneRow.getSession());
            stuDto.setShift(oneRow.getShift());
            stuDto.setCourid(oneRow.getCourid().getCourid());
            stuDto.setBranchcampus(oneRow.getBranchcamid().getBranchcamid());
            return new ResponseEntity<>(stuDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Use In Update Student Group
    @GetMapping(value = "/getDataByStudentGroupID/{id}", produces = "application/json")
    public ResponseEntity<FindStudentgroup> getDataByStudentGroupID(@PathVariable("id") int id) {
        try {
            FindStudentgroup stuDto = studentGroupService.getDataByStudentGroupId(id);
            return new ResponseEntity<>(stuDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getStudentGroupByIdInTimetable/{id}", produces = "application/json")
    public ResponseEntity<Integer> getStudentGroupByIdInTimetable(@PathVariable("id") int id) {
        StudentGroup getID = new StudentGroup();
        getID.setStugroid(id);
        try {
            int amount = studentGroupService.getStudentGroupByIdinTimetable(getID);
            return new ResponseEntity<>(amount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/amountStudentInStuGrou/{stuid}", produces = "application/json")
    public ResponseEntity<Integer> amountStudentInStuGrou(@PathVariable("stuid") int stuid) {
        StudentGroup getID = new StudentGroup();
        getID.setStugroid(stuid);
        try {
            int amount = studentGroupService.getAmountStudentInStudentGroup(getID);
            return new ResponseEntity<>(amount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
