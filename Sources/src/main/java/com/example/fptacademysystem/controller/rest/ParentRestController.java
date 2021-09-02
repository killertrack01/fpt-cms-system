package com.example.fptacademysystem.controller.rest;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.fptacademysystem.dto.AttendanceDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.model.Parent;
import com.example.fptacademysystem.model.RenderTimetable;
import com.example.fptacademysystem.services.ParentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/user/parent")
public class ParentRestController {
    @Autowired
    ParentService parentService;

    //Parent view student's timetable
    @GetMapping(value = "/view/timetable")
    public ResponseEntity<List<RenderTimetable>> getTimetable(int stugroid, int semid,HttpSession session){
        int id=(int) session.getAttribute("parentid");
        Parent pa = parentService.findParent(id);

        //-- List all timetable
        List<RenderTimetable> list= new ArrayList<>();

        //-- List timetable with studentgroup id and semid
        List<RenderTimetable> render= new ArrayList<>();

        list = parentService.renderTimetable(pa.getStuid().getStuid());
        for(RenderTimetable rt:list){
            if(rt.getStugroid()==stugroid&&rt.getSemid()== (semid - 1 )){
                render.add(rt);
            }
        }
        return new ResponseEntity<>(render,HttpStatus.OK);
    }

    // get Student group by student id
    @GetMapping(value = "/view/timetable/findStudentgroup")
    public ResponseEntity<List<StudentGroupDTO>> getallStudentGroup(HttpSession session) {
        int id=(int) session.getAttribute("parentid");
        Parent pa = parentService.findParent(id);
        int studid= pa.getStuid().getStuid();
        return new ResponseEntity<>(parentService.findStudentGroupByStudentId(studid), HttpStatus.OK);
    }
    // Get list semester
    @GetMapping(value = "/view/timetable/findAllSemester")
    public ResponseEntity<List<SemesterDTO>> getallSemester() {
        return new ResponseEntity<>(parentService.findAllSemester(), HttpStatus.OK);
    }
    // Get student's attendance
    @GetMapping(value = "/view/timetable/getattendance")
    public ResponseEntity<AttendanceDTO> getallAttendance(int timetableid, HttpSession session) {
        int id=(int) session.getAttribute("parentid");
        Parent pa = parentService.findParent(id);
        int studid=pa.getStuid().getStuid();
        return new ResponseEntity<>(parentService.findAllStudentAttendances(studid, timetableid), HttpStatus.OK);
    }
}
