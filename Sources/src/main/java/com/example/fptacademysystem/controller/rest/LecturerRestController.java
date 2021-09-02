package com.example.fptacademysystem.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.fptacademysystem.configuration.Mail;
import com.example.fptacademysystem.dto.*;
import com.example.fptacademysystem.model.*;

import static com.example.fptacademysystem.model.StudentGroup_.session;
import com.example.fptacademysystem.services.AttendanceService;
import com.example.fptacademysystem.services.GpaLecturerService;
import com.example.fptacademysystem.services.LecturerAccountService;
import com.example.fptacademysystem.services.LecturerService;

import com.example.fptacademysystem.services.MailService;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
//@RequestMapping("/api/admin/teacher")
public class LecturerRestController {

    ModelAndView tIndex = new ModelAndView("admin/lecturer/main-lecturer");

    @Autowired
    LecturerService services;

    @Autowired
    GpaLecturerService gpaservices;

    @Autowired
    LecturerAccountService accountservices;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    MailService mailService;

    @PostMapping(value = "/api/admin/teacher/postcreate", produces = "application/json")
    public String postCreate(LecturerDTO teacher) throws IOException {
        JSONObject jsonObject = new JSONObject();
        List<Lecturer> list = services.findAll();
        String message = "";
        try
        {
            // kiem tra rollnum da ton tai chua
            for (Lecturer lecturer : list)
            {
                if (lecturer.getRollnum().equals(teacher.getRollnum()))
                {
                    message = "RollNumber";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
                if (lecturer.getCompanyemail().equals(teacher.getCompanyemail()))
                {
                    message = "CompanyEmail";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + "Has Been Existed");
                    return jsonObject.toString();
                }
                if (lecturer.getEmail().equals(teacher.getEmail()))
                {
                    message = "Email";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + "Has Been Existed");
                    return jsonObject.toString();
                }
                if (lecturer.getIdcard().equals(teacher.getIdcard()))
                {
                    message = "IDCard";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + "Has Been Existed");
                    return jsonObject.toString();
                }
                if (lecturer.getPhone().equals(teacher.getPhone()))
                {
                    message = "Phone";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + "Has Been Existed");
                    return jsonObject.toString();
                }
            }
            // tao lecturer
            services.postCreate(teacher);

            // tao lectureraccount
            LecturerAccount account = new LecturerAccount();
            account.setRollnum(teacher.getRollnum());
            account.setPass("123");
            //lay lecturer mo nhat
            Lecturer l = new Lecturer();
            l.setLecturid(services.findNewLecturer());
            account.setLecturid(l);
            account.setRemoveat("No");
            accountservices.postCreate(account);

            tIndex.addObject("list", services.findAll());

            jsonObject.put("title", "success");
            jsonObject.put("message", "Save Successfully");
        } catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @PostMapping(value = "/api/admin/teacher/postupdate", produces = "application/json")
    public String postUpdate(LecturerDTO teacher) throws IOException {
        JSONObject jsonObject = new JSONObject();
        List<Lecturer> list = services.findAll();
        String message = "";
        try
        {
            // kiem tra rollnum da ton tai chua
            for (Lecturer lecturer : list)
            {
                if (lecturer.getLecturid() != Integer.parseInt(teacher.getLecturid()))
                {
                    if (lecturer.getRollnum().equals(teacher.getRollnum()))
                    {
                        message = "RollNumber";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    if (lecturer.getCompanyemail().equals(teacher.getCompanyemail()))
                    {
                        message = "CompanyEmail";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    if (lecturer.getEmail().equals(teacher.getEmail()))
                    {
                        message = "Email";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    if (lecturer.getIdcard().equals(teacher.getIdcard()))
                    {
                        message = "IDCard";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    if (lecturer.getPhone().equals(teacher.getPhone()))
                    {
                        message = "Phone";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                } else
                {
                    break;
                }
            }

            // cap nhat lecturer
            services.postUpdate(teacher);

            // cap nhat lecturer account
            int lecturid = Integer.parseInt(teacher.getLecturid());

            LecturerAccount eacc = accountservices.findAccount(lecturid);

            LecturerAccount account = new LecturerAccount();
            account.setTeaaccid(eacc.getTeaaccid());
            account.setRollnum(teacher.getRollnum());
            if (teacher.getLecturerPass().isEmpty())
            {
                account.setPass("123");
            } else
            {
                account.setPass(teacher.getLecturerPass());
            }
            account.setLecturid(eacc.getLecturid());
            account.setRemoveat("No");

            accountservices.postCreate(account);
            tIndex.addObject("list", services.findAll());

            jsonObject.put("title", "success");
            jsonObject.put("message", "Update Successfully");
        } catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @PostMapping(value = "/api/admin/teacher/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        JSONObject jSONObject = new JSONObject();
        try
        {
            if (services.deleteLecturer(id) == false)
            {
                jSONObject.put("title", "error");
            } else
            {
                tIndex.addObject("list", services.findAll());
                jSONObject.put("title", "success");
            }
        } catch (Exception e)
        {
            return e.getMessage();
        }
        return jSONObject.toString();
    }

    @GetMapping(value = "/api/admin/teacher/findlecturer/{lecturid}", produces = "application/json")
    public String findLecturer(@PathVariable("lecturid") int lecturid) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            Lecturer lecturer = services.findLecturer(lecturid);
            jsonObject.put("id", lecturer.getLecturid());
        } catch (JSONException ex)
        {
            Logger.getLogger(LecturerRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/api/admin/teacher/sendmailLecturer", produces = "application/json")
    public String sendmailLecturer(LecturerMailDTO dto) {
        JSONObject jSONObject = new JSONObject();
        try
        {
            if (dto.getMailsubject().isEmpty())
            {

                jSONObject.put("title", "error");
                jSONObject.put("message", "Mail Subject Null !");
                return jSONObject.toString();
            }
            if (dto.getMailmessage().isEmpty())
            {

                jSONObject.put("title", "error");
                jSONObject.put("message", "Mail Message Null !");
                return jSONObject.toString();
            }
            Mail mail = new Mail();
            Lecturer lecturer = services.findLecturer(Integer.parseInt(dto.getIdsendmail()));
            mail.setMailTo(lecturer.getCompanyemail());
            mail.setMailSubject(dto.getMailsubject());
            mail.setMailContent(dto.getMailmessage());
            String fileName = dto.getAttachments().getOriginalFilename();
            if (fileName == null)
            {
                //
            } else
            {
                mail.setAttachments(dto.getAttachments());
            }
            mailService.sendEmail(mail);
            jSONObject.put("title", "success");
            jSONObject.put("message", "Successfully!");
        } catch (JSONException ex)
        {
            Logger.getLogger(LecturerRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @GetMapping(value = "/api/admin/teacher/gpalecturer", produces = "application/json")
    public ResponseEntity<List<GpaLecturerDTO>> gpalecturer(String gpamonth, String gpayear, String lecturid) {
        try
        {
            GpaLecturerDTO dto = new GpaLecturerDTO(gpamonth, gpayear, lecturid);
            List<GpaLecturerDTO> list = gpaservices.findGpaForOneLecturer(dto);
            if (!list.isEmpty())
            {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else
            {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
            }
        } catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/admin/teacher/gpalecturerforyear", method = RequestMethod.GET)
    public ResponseEntity<List<RenderGpalecturer>> gpalecturerforyear(int gpayear, int lecturid) {
        List<RenderGpalecturer> list = gpaservices.findGpaYearForOneLecturer(gpayear, lecturid);
        if(list.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.OK);
        }
        return new ResponseEntity<List<RenderGpalecturer>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/teacher/allgpalecturerforyear", method = RequestMethod.GET)
    public ResponseEntity<List<RenderGpalecturer>> allgpalecturerforyear(int gpayear) {
        List<RenderGpalecturer> list = gpaservices.findAllGpaYearLecturer(gpayear);
        if(list.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.OK);
        }
        return new ResponseEntity<List<RenderGpalecturer>>(list, HttpStatus.OK);
    }

    //User Lecturer
    @GetMapping(value = "/api/lecturer/subject/findstudentingroup/{id}")
    public ResponseEntity<List<StudentClassDTO>> findStudentByStudentGroupId(@PathVariable("id") String id) {
        List<StudentClassDTO> list = services.findStudentByStudentGroupId(Integer.parseInt(id));
        if (list.isEmpty())
        {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Get All Courses By Branch
    @GetMapping(value = "/api/lecturer/courses/findAll")
    public ResponseEntity<List<CourseDTO>> findAllCoursesByBranch(HttpSession session) {
        String rollnum = (String) session.getAttribute("lecturerroll");
        Lecturer lecturer = services.findLecturerByRollnum(rollnum);
        List<CourseDTO> list = services.findAllCoursesByBranch(lecturer);
        if (list.isEmpty())
        {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/api/lecturer/studentgroups/findAll/{courseid}")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroupByCourses(@PathVariable("courseid") int courseid) {
        List<RenderStudentgroup> list = services.getAllStudentGroupByCourses(courseid);
        if (list.isEmpty())
        {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/api/lecturer/studentgroups/findbylecturerandcourse")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroupByLecturerId(HttpSession session) {
        String rollnum = (String) session.getAttribute("lecturerroll");
        Lecturer lecturer = services.findLecturerByRollnum(rollnum);
        List<RenderStudentgroup> list = services.getTimetableByLecturerCourseGroupId(lecturer);
        if (list.isEmpty())
        {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/api/lecturer/studentgroups/findlecturerstudentgroupbycourse/{courseid}")
    public ResponseEntity<List<RenderStudentgroup>> getAllStudentGroupByLecturerCoursesId(HttpSession session, @PathVariable("courseid")int courseid) {
        String rollnum = (String) session.getAttribute("lecturerroll");
        Lecturer lecturer = services.findLecturerByRollnum(rollnum);
        List<RenderStudentgroup> list = services.getTimetableByLecturerCourseGroupId(lecturer);
        List<RenderStudentgroup> checkList = new ArrayList<>();
        if (!list.isEmpty())
        {
            for (RenderStudentgroup rs : list)
            {
                if(rs.getCourid() != courseid){
                    checkList.add(rs);
                }
            }
            list.removeAll(checkList);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    // Get Student Group By Branch Campus Id
    @GetMapping(value = "/api/admin/teacher/getStudentGroupByBranchCampusId/{branchcampusID}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> getStudentGroupByBranchCampusId(
            @PathVariable("branchcampusID") int branchcampusID, HttpSession session) {
        List<StudentGroupDTO> list = null;
        try {
            if (session.getAttribute("lecturerid") != null) {
                int id = (int) session.getAttribute("lecturerid");
                list = new ArrayList<>();
                BranchCampus getID = new BranchCampus();
                getID.setBranchcamid(branchcampusID);
                for (StudentGroup stuGroup : services.getStudentGroupByBranchCampusId(getID, id)) {
                    StudentGroupDTO sgDTO = new StudentGroupDTO();
                    sgDTO.setStugroid(stuGroup.getStugroid());
                    sgDTO.setStugronm(stuGroup.getStugronm());
                    sgDTO.setOpeningdate(stuGroup.getOpeningdate());
                    sgDTO.setSession(stuGroup.getSession());
                    sgDTO.setShift(stuGroup.getShift());
                    sgDTO.setBranchcampus(stuGroup.getBranchcamid().getBranchcamid());
                    sgDTO.setCourid(stuGroup.getCourid().getCourid());
                    list.add(sgDTO);
                }
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get Subject Date By Semester Id And Student Group Id And Subject Id
    @GetMapping(value = "/api/admin/teacher/getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId/{studengroupID}/{semID}/{subjID}", produces = "application/json")
    public ResponseEntity<List<SubjectDateInTimetableDTO>> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
            @PathVariable("subjID") int subjID, HttpSession session) {
        List<SubjectDateInTimetableDTO> list = new ArrayList<>();
        StudentGroup getID = new StudentGroup();
        getID.setStugroid(studengroupID);
        try {
            if (session.getAttribute("lecturerid") != null) {
                int id = (int) session.getAttribute("lecturerid");
                for (Timetable timetableDTO : services.getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(getID, semID, subjID,id)) {
                    SubjectDateInTimetableDTO dateInTimetableDTO = new SubjectDateInTimetableDTO();
                    dateInTimetableDTO.setTimetableid(timetableDTO.getTimetableid());
                    dateInTimetableDTO.setSubjnm(timetableDTO.getSubjnm());
                    dateInTimetableDTO.setSubjdate(timetableDTO.getSubjdate());
                    dateInTimetableDTO.setSlotofsubjdate(timetableDTO.getSlotofsubjdate());
                    dateInTimetableDTO.setAttenteaid(timetableDTO.getAttenteaid());
                    dateInTimetableDTO.setSemid(timetableDTO.getSemid());
                    dateInTimetableDTO.setAttenedit(timetableDTO.getAttenedit());
                    dateInTimetableDTO.setNote(timetableDTO.getNote());
                    dateInTimetableDTO.setSubjdetailsid(timetableDTO.getSubjdetailsid().getSubjdetailsid());
                    dateInTimetableDTO.setStugroid(timetableDTO.getStugroid().getStugroid());
                    dateInTimetableDTO.setRoomid(timetableDTO.getRoomid().getRoomid());
                    dateInTimetableDTO.setRemoveat(timetableDTO.getRemoveat());
                    list.add(dateInTimetableDTO);
                }
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Render Attendance Report
    @GetMapping(value = "/api/admin/teacher/renderAttendanceReport/{studengroupID}/{semID}/{subjectID}", produces = "application/json")
    public ResponseEntity<List<ReportAttendance>> renderAttendanceReport(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
            @PathVariable("subjectID") int subjectId,  HttpSession session) {
        List<ReportAttendance> renderData = null;
        try {
            if (session.getAttribute("lecturerid") != null) {
                int id = (int) session.getAttribute("lecturerid");
                renderData = services.getAttendanceReport(studengroupID, semID, subjectId, id);
            }
            return new ResponseEntity<>(renderData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Na: Get All Lecturer
    @GetMapping(value = "/api/admin/teacher/getAllLecture", produces = "application/json")
    public ResponseEntity<List<LecturerDTO>> getAllLecture() {
        List<LecturerDTO> list = new ArrayList<LecturerDTO>();
        try {
            for (Lecturer item : services.getAlllecture()) {
                LecturerDTO ldto = new LecturerDTO();
                ldto.setFullnm(item.getFullnm());
                ldto.setLecturid(String.valueOf(item.getLecturid()));
                ldto.setRollnum(item.getRollnum());
                list.add(ldto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Na: Render Teaching Hours
    @GetMapping(value = "/api/admin/teacher/renderTeachingHours/{teaid}/{yearVal}/{monthVal}", produces = "application/json")
    public ResponseEntity<List<RenderTeachingHours>> renderTeachingHours(@PathVariable("teaid") int teaid,
                                                                         @PathVariable("yearVal") int yearVal, @PathVariable("monthVal") int monthVal) {
        List<RenderTeachingHours> list = null;
        try {
            list = services.renderteachinghours(teaid, yearVal, monthVal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Na: Group By Render Teaching Hours
    @GetMapping(value = "/api/admin/teacher/countRenderTeachingHours/{teaid}/{yearVal}/{monthVal}", produces = "application/json")
    public ResponseEntity<List<String>> countRenderTeachingHours(@PathVariable("teaid") int teaid,
                                                                 @PathVariable("yearVal") int yearVal, @PathVariable("monthVal") int monthVal) {
        List<String> list = null;
        try {
            list = services.groupByRenderteachinghours(teaid, yearVal, monthVal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //------------------------------------------------- Render All Teaching Hours
    // Na: Render Teaching Hours
    @GetMapping(value = "/api/admin/teacher/renderAllTeachingHours/{yearVal}/{monthVal}", produces = "application/json")
    public ResponseEntity<List<RenderTeachingHours>> renderAllTeachingHours(@PathVariable("yearVal") int yearVal, @PathVariable("monthVal") int monthVal) {
        List<RenderTeachingHours> list = null;
        try {
            list = services.renderAllTeachingHours(yearVal, monthVal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Na: Group By Render Teaching Hours
    @GetMapping(value = "/api/admin/teacher/countAllRenderTeachingHours/{yearVal}/{monthVal}", produces = "application/json")
    public ResponseEntity<List<String>> countAllRenderTeachingHours(@PathVariable("yearVal") int yearVal, @PathVariable("monthVal") int monthVal) {
        List<String> list = null;
        try {
            list = services.groupByAllRenderTeachingHours(yearVal, monthVal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


//    // Check The Right To Edit Student Attendance
//    @GetMapping(value = "/checkTheRightToEditStudentAttendance/{studentgroupID}/{semesterID}/{subjectID}/{subjectDate}/{sessionVal}", produces = "application/json")
//    public ResponseEntity<Boolean> checkTheRightToEditStudentAttendance(
//            @PathVariable("studentgroupID") int studentgroupID,
//            @PathVariable("semesterID") int semesterID, @PathVariable("subjectID") int subjectID,
//            @PathVariable("subjectDate") String subjectDate, @PathVariable("sessionVal") String sessionVal) {
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//            Date date = formatter.parse(subjectDate);
//            boolean checkEditRole = services.checkTheRightToEditStudentAttendance(studentgroupID, semesterID, subjectID, date, sessionVal);
//            return new ResponseEntity<>(checkEditRole, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    // Quang:  Render Teaching Hours
    @GetMapping(value = "/api/admin/teacher/renderTeachingHoursQuang/{yearVal}/{monthVal}", produces = "application/json")
    public ResponseEntity<List<RenderTeachingHours>> renderTeachingHoursQuang(@PathVariable("yearVal") int yearVal, @PathVariable(
            "monthVal") int monthVal, HttpSession session) {
        List<RenderTeachingHours> list = null;
        try {
            if (session.getAttribute("lecturerid") != null) {
                int id = (int) session.getAttribute("lecturerid");
                list = services.renderteachinghours(id, yearVal, monthVal);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Quang: Group By Render Teaching Hours
    @GetMapping(value = "/api/admin/teacher/countRenderTeachingHoursQuang/{yearVal}/{monthVal}", produces = "application/json")
    public ResponseEntity<List<String>> countRenderTeachingHoursQuang(@PathVariable("yearVal") int yearVal,
                                                                      @PathVariable("monthVal") int monthVal, HttpSession session) {
        List<String> list = null;
        try {
            if (session.getAttribute("lecturerid") != null) {
                int id = (int) session.getAttribute("lecturerid");
                list = services.groupByRenderteachinghours(id, yearVal, monthVal);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
