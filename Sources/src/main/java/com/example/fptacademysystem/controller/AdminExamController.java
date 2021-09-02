package com.example.fptacademysystem.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.fptacademysystem.dto.ExamDTO;
import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.Room;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.repository.RoomRepository;
import com.example.fptacademysystem.services.ExamService;
import com.example.fptacademysystem.services.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminExamController {

    @Autowired
    ExamService examService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    RoomRepository roomrepo;
    @RequestMapping(value = "/admin/dashboard/exams", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            List<ExamDTO> list=new ArrayList<>();
            for (Exam e : examService.findAll()) {
                if(e.getRemoveat().equals("No")){
                    ExamDTO ed = new ExamDTO();

                    ed.setExamid(e.getExamid());
                    ed.setExamcodelogin(e.getExamcodelogin());
                    ed.setExamday(e.getExamday().toString());
                    ed.setExamtype(e.getExamtype());
                    ed.setExamtime(e.getExamtime());
                    ed.setBout(e.getBout()+"");
                    ed.setRoomid(e.getRoomid().getRoomid().toString());
                    ed.setStugroid(e.getStugroid().getStugroid().toString());
                    ed.setSubjdetailsid(e.getSubjdetailsid()+"");

                    //get room object
                    Room room = new Room();
                    room      = examService.findRoomById(e.getRoomid().getRoomid());
                    ed.setRoomnm(room.getRoomnm());
                    //----------

                    //get student room object
                    StudentGroup stugro = new StudentGroup();
                    stugro = examService.findStudentGroupById(e.getStugroid().getStugroid());
                    ed.setStugronm(stugro.getStugronm());
                    //---------

                    //get subject detail and subject object
                    SubjectDetails subdetail=new SubjectDetails();
                    Subject s = new Subject();

                    subdetail = examService.findSubjectDetailsById(e.getSubjdetailsid());
                    s = examService.findSubjectById(subdetail.getSubjid().getSubjid());
                    ed.setSubjnm(s.getSubjnm());
                    //---------

                    list.add(ed);
                }
            }
            model.addAttribute("list", list);
            model.addAttribute("roomlist", roomrepo.findAll());
            return "/admin/exams/main-exam";
        } else {
            return "login/admin-login";
        }

    }

}
