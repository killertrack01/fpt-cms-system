package com.example.fptacademysystem.controller;

import com.example.fptacademysystem.dto.FeedbackDTO;
import com.example.fptacademysystem.model.StudentFeedback;
import com.example.fptacademysystem.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminFeedbackController {

    @Autowired
    FeedbackService services;

    @RequestMapping("/admin/dashboard/feedbacks")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            List<FeedbackDTO> list = services.findAll();
            for (FeedbackDTO feedbackDTO : list) {
                int feedid = Integer.parseInt(feedbackDTO.getFeedbackid());
                List<StudentFeedback> listStudentFeedbacks = services.findStudentFeedbackByFeedId(feedid);
                if (listStudentFeedbacks.isEmpty()) {
                    feedbackDTO.setCount("0");
                } else {
                    feedbackDTO.setCount("1");
                }
            }
            model.addAttribute("list", list);
            return "/admin/feedbacks/main-feedback";
        } else {
            return "login/admin-login";
        }
    }

}
