/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.FeedbackDTO;
import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentFeedbackDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Feedback;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.RenderStudentgroup;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentFeedback;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.repository.SemesterRepository;
import com.example.fptacademysystem.repository.StudentClassRepository;
import com.example.fptacademysystem.repository.SubjectDetailRepository;
import com.example.fptacademysystem.services.CoursesService;
import com.example.fptacademysystem.services.FeedbackService;
import com.example.fptacademysystem.services.LecturerService;
import com.example.fptacademysystem.services.StudentGroupService;
import com.example.fptacademysystem.services.StudentService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api/admin/feedback")
public class FeedbackRestController {

    @Autowired
    FeedbackService services;

    @Autowired
    LecturerService lecserService;

    @Autowired
    StudentGroupService stugroService;

    @Autowired
    CoursesService courseService;

    @Autowired
    StudentService studentservice;

    @Autowired
    SemesterRepository semrepo;

    @Autowired
    SubjectDetailRepository detailRepo;

    @Autowired
    StudentClassRepository stuclassrepo;

    @PostMapping(value = "/saveFeedback", produces = "application/json")
    public String postCreate(@RequestBody FeedbackDTO dto) {
        JSONObject jSONObject = new JSONObject();
        try
        {
            int subjectdetailsid = Integer.parseInt(dto.getSubjdetailsid());
            int stugroid = Integer.parseInt(dto.getStugroid());
            Feedback feedback = services.findFeedbackBySucjectID(subjectdetailsid, stugroid);
            if (feedback != null)
            {
                jSONObject.put("title", "error");
                jSONObject.put("message", "Subject or StudentGroup Duplicate");
                return jSONObject.toString();
            }
            services.saveFeedback(dto);
            jSONObject.put("title", "success");
        } catch (JSONException ex)
        {
            Logger.getLogger(FeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @PostMapping(value = "/editFeedback", produces = "application/json")
    public String postEdit(@RequestBody FeedbackDTO dto) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            if (dto.getDateoffeedback().isEmpty())
            {
                jsonObject.put("title", "error");
                return jsonObject.toString();
            }
            services.editFeedback(dto);
            jsonObject.put("title", "success");
        } catch (JSONException ex)
        {
            Logger.getLogger(FeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @PostMapping(value = "/deleteFeedback/{id}", produces = "application/json")
    public String deleteFeedback(@PathVariable("id") String id) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            int feedbackid = Integer.parseInt(id);
            services.deleteFeedback(feedbackid);
            jsonObject.put("title", "success");
        } catch (JSONException ex)
        {
            Logger.getLogger(FeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @PostMapping(value = "/updateStatus", produces = "application/json")
    public String updateStatusFeedback(@RequestBody FeedbackDTO dto) {
        JSONObject jsonObject = new JSONObject();
        try
        {
            int feedbackid = Integer.parseInt(dto.getFeedbackid());
            services.updateStatusFeedback(dto.getFeedbackstatus(), feedbackid);
            jsonObject.put("title", "success");
            if (dto.getFeedbackstatus().equals("true"))
            {
                jsonObject.put("message", "Feedback Actived");
            } else
            {
                jsonObject.put("message", "Feedback Deactived");
            }
        } catch (JSONException ex)
        {
            Logger.getLogger(FeedbackRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    @GetMapping(value = "/findAllFeedback")
    public ResponseEntity<List<FeedbackDTO>> findAllFeedback() {
        List<FeedbackDTO> list = services.findAll();
        try
        {
            for (FeedbackDTO feedbackDTO : list)
            {
                int feedid = Integer.parseInt(feedbackDTO.getFeedbackid());
                List<StudentFeedback> listStudentFeedbacks = services.findStudentFeedbackByFeedId(feedid);
                if (listStudentFeedbacks.isEmpty())
                {
                    feedbackDTO.setCount("0");
                } else
                {
                    feedbackDTO.setCount("1");
                }
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //    @GetMapping(value = "/findAllFeedback")
//    public ResponseEntity<FeedbackDTO> getDateOfSubject(@RequestBody FeedbackDTO dto) {
//        try
//        {
//
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        } catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping(value = "/findFeedbackMaxDate", produces = "application/json")
    public ResponseEntity<FeedbackDTO> findMaxDateFeedback(int stugroid, int subjdetailsid) {
        try
        {
            Date maxDate = services.getMaxDateFeedback(stugroid, subjdetailsid);
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            feedbackDTO.setEnddatefeedback(maxDate.toString());
            Calendar calendar = new GregorianCalendar(maxDate.getYear() + 1900, maxDate.getMonth(), maxDate.getDate());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar.add(Calendar.DATE, -2);
            feedbackDTO.setDateoffeedback(sdf.format(calendar.getTime()));
            return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
        } catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findStudentFeedback/{feedbackid}", produces = "application/json")
    public ResponseEntity<List<StudentFeedbackDTO>> findStudentFeedback(@PathVariable("feedbackid") String feedbackid) {
        int feedid = Integer.parseInt(feedbackid);
        List<StudentFeedback> listStudentFeedback = services.findStudentFeedbackByFeedId(feedid);
        Feedback feedback = services.findFeedback(feedid);
        List<StudentFeedbackDTO> listDTO = new ArrayList<>();
        //set object index 0
        StudentFeedbackDTO studentFeed = new StudentFeedbackDTO();
        studentFeed.setNamefeedback(feedback.getFeedbacknm());
        listDTO.add(studentFeed);

        //get list dto and return
        try
        {
            for (StudentFeedback studentFeedback : listStudentFeedback)
            {
                Student student = studentservice.findOne(studentFeedback.getStuid().getStuid());
                StudentClass studentClass = stuclassrepo.findCurrentClassByStudentIdAndStugroid(student.getStuid(), feedback.getStugroid());
                StudentGroup studentGrou = stugroService.getStudentGroupByID(studentClass.getStugroid().getStugroid());
                //set object reutrn
                StudentFeedbackDTO dto = new StudentFeedbackDTO();
                dto.setStufeedbackid(studentFeedback.getStufeedbackid().toString());
                dto.setAns1(studentFeedback.getAns1());
                dto.setAns2(studentFeedback.getAns2());
                dto.setAns3(studentFeedback.getAns3());
                dto.setAns4(studentFeedback.getAns4());
                dto.setAns5(studentFeedback.getAns5());
                dto.setFeedbackid(studentFeedback.getFeedbackid().getFeedbackid().toString());
                dto.setGpa(String.valueOf(studentFeedback.getGpafeedback()));
                dto.setNote(studentFeedback.getNote());
                dto.setStuid(student.getStuid().toString());
                dto.setStudentfullnm(student.getFullnm());
                dto.setStudentrollnum(student.getRollnum());
                dto.setStugroid(String.valueOf(studentGrou.getStugroid()));
                dto.setStugronm(studentGrou.getStugronm());
                dto.setDatefeedback(studentFeedback.getCreatat().toString());
                listDTO.add(dto);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findAllCourse", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> findAllCourse() {
        List<CourseDTO> list = new ArrayList<>();
        try
        {
            for (Courses courses : services.findAllCourse())
            {
                CourseDTO c = new CourseDTO();
                c.setCourid(courses.getCourid());
                c.setCournm(courses.getCournm());
                c.setBranchid(courses.getBranchid().getBranchid());

                list.add(c);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findStudentgroup/{id}", produces = "application/json")
    public ResponseEntity<List<RenderStudentgroup>> findStudentGroupByCourid(@PathVariable("id") int id) {
        try
        {
            return new ResponseEntity<>(services.findStudentGroupByCourid(id), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findAllSemester/{id}", produces = "application/json")
    public ResponseEntity<List<SemesterDTO>> findAllSemester(@PathVariable("id") String id) {
        try
        {
            int stugroid = Integer.parseInt(id);
            List<Semester> list = services.findAllSemester(stugroid);
            List<SemesterDTO> listDTO = new ArrayList<>();
            for (Semester semester : list)
            {
                SemesterDTO dto = new SemesterDTO();
                dto.setSemid(semester.getSemid());
                dto.setSemnm(semester.getSemnm());

                listDTO.add(dto);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (NumberFormatException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findSubjectDetails/{semid}/{courseid}", produces = "application/json")
    public ResponseEntity<List<SubjectDetailsDTO>> findSubjectDetails(@PathVariable("semid") String semid, @PathVariable("courseid") String courseid) {
        try
        {
            int sem = Integer.parseInt(semid);
            int course = Integer.parseInt(courseid);
            List<SubjectDetailsDTO> list = services.findSubjectDetails(sem, course);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NumberFormatException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findAllLecturer", produces = "application/json")
    public ResponseEntity<LecturerDTO> findAllLecturer(String stugroid, String semid) {
        int studentgroupid = Integer.parseInt(stugroid);
        int sem = Integer.parseInt(semid);
        Lecturer lecturer = services.findLecturerByStudentGroupAndSemId(studentgroupid, sem);
        if (lecturer != null)
        {
            LecturerDTO t = new LecturerDTO();
            t.setLecturid(lecturer.getLecturid().toString());
            t.setRollnum(lecturer.getRollnum());
            t.setFullnm(lecturer.getFullnm());
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{feedbackid}", produces = "application/json")
    public ResponseEntity<FeedbackDTO> find(@PathVariable("feedbackid") int feedbackid) {
        try
        {
            Feedback feedback = services.findFeedback(feedbackid);
            FeedbackDTO dto = new FeedbackDTO();
            SubjectDetails details = detailRepo.findById(feedback.getSubjdetailsid().getSubjdetailsid()).get();
            Courses courses = courseService.FindOne(details.getCourid().getCourid());
            Semester semester = semrepo.findById(details.getSemid().getSemid()).get();

            dto.setNamefeedback(feedback.getFeedbacknm());
            dto.setDateoffeedback(feedback.getDateoffeedback().toString());
            dto.setEnddatefeedback(feedback.getEnddatefeedback().toString());
            dto.setStugroid(String.valueOf(feedback.getStugroid()));
            dto.setLecturid(String.valueOf(feedback.getLecturid()));
            dto.setSubjdetailsid(String.valueOf(details.getSubjdetailsid()));

            //get lectuernm
            Lecturer lecturer = lecserService.findLecturer(feedback.getLecturid());
            dto.setLecturernm(lecturer.getFullnm());

            //get subjectname
            int subjectid = feedback.getSubjdetailsid().getSubjdetailsid();
            dto.setSubjectnm(services.findSubjectName(subjectid));

            //get feedbackid
            dto.setFeedbackid(feedback.getFeedbackid().toString());

            dto.setCourseid(courses.getCourid().toString());

            dto.setSemesterid(semester.getSemid().toString());

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
