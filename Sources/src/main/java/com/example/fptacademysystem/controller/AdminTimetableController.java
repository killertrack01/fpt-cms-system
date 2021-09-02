package com.example.fptacademysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fptacademysystem.services.StudentGroupService;
import com.example.fptacademysystem.services.TimetableService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminTimetableController {

    @Autowired
    TimetableService timetableService;

    @Autowired
    StudentGroupService studentGroupService;

    @RequestMapping(value = "/admin/dashboard/timetable", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            model.addAttribute("studengroup", studentGroupService.getAll());
            return "admin/timetable/main-timetable";
        } else {
            return "login/admin-login";
        }
    }

    @RequestMapping(value = "/admin/dashboard/timetable/report", method = RequestMethod.GET)
    public String report(Model model, HttpSession session) {
        if (session.getAttribute("staffroll") != null) {
            return "admin/timetable/report-timetable";
        } else {
            return "login/admin-login";
        }
    }

//	@RequestMapping(value = "/admin/dashboard/timetable/class/{id}/{sid}", method = RequestMethod.GET)
//	public String searchByClass(@PathVariable("id") int id, @PathVariable("sid") int sid, Model model) {
//		model.addAttribute("listTTClass", timetableService.searchTimetableByClass(id, sid));
//		return "admin/timetable/main-timetable";
//	}

}
