package com.example.fptacademysystem.controller.rest;

import com.example.fptacademysystem.dto.BranchCampusDTO;
import com.example.fptacademysystem.dto.DateAttendanceDTO;
import com.example.fptacademysystem.dto.GetDataToCreateAttendanceDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.dto.SubjectDateInTimetableDTO;
import com.example.fptacademysystem.dto.TimetableDTO;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/admin/attendance")
public class AttendanceRestController {

    @Autowired
    AttendanceService attendanceService;

    @RequestMapping(value = "/createAttendance")
    public boolean createAttendance(@RequestBody List<GetDataToCreateAttendanceDTO> getDTCSDTO) throws IOException {
        Attendance att = null;
        Timetable tt = null;
        Student st = null;
        for (int i = 0; i < getDTCSDTO.size(); i++) {
            // Get Student ID
            st = new Student();
            st.setStuid(attendanceService.findStudent(getDTCSDTO.get(i).getRollnum())); // Student ID

            // Get Info Of Subject In Timetable
            StudentGroup getSGID = new StudentGroup();
            getSGID.setStugroid(getDTCSDTO.get(i).getStudentgroupid());
            SubjectDetails getSDID = new SubjectDetails();
            getSDID.setSubjdetailsid(getDTCSDTO.get(i).getSubjectdetailsid());
            List<Timetable> listTT = attendanceService.findTimetable(getSGID, getDTCSDTO.get(i).getSemid(), getSDID,
                    getDTCSDTO.get(i).getSubjdate());

            // Create By Case
            switch (getDTCSDTO.get(i).getSession()) {
                case "S1":
                    att = new Attendance();
                    if (listTT.size() > 0) {
                        for (int j = 0; j < listTT.size(); j++) {
                            String das = (listTT.get(j).getSlotofsubjdate()).substring(
                                    (listTT.get(j).getSlotofsubjdate()).indexOf('?') + 2,
                                    (listTT.get(j).getSlotofsubjdate()).indexOf('?') + 3);
                            if ((listTT.get(j).getSlotofsubjdate())
                                    .substring((listTT.get(j).getSlotofsubjdate()).indexOf('?') + 2,
                                            (listTT.get(j).getSlotofsubjdate()).indexOf('?') + 3)
                                    .equals("1")) {
                                tt = new Timetable();
                                tt.setTimetableid(listTT.get(j).getTimetableid()); // Timetable ID Of Session 1
                                att.setPresent(getDTCSDTO.get(i).getPresent());
                                att.setNote(getDTCSDTO.get(i).getNote());
                                att.setTimetableid(tt);
                                att.setStuid(st);
                                att.setRemoveat("No");
                                attendanceService.createAttendance(att);
                            }
                        }
                    }
                    break;
                case "S2":
                    att = new Attendance();
                    if (listTT.size() > 0) {
                        for (int j = 0; j < listTT.size(); j++) {
                            String das1 = (listTT.get(j).getSlotofsubjdate()).substring(
                                    (listTT.get(j).getSlotofsubjdate()).indexOf('?') + 2,
                                    (listTT.get(j).getSlotofsubjdate()).indexOf('?') + 3);
                            if ((listTT.get(j).getSlotofsubjdate())
                                    .substring((listTT.get(j).getSlotofsubjdate()).indexOf('?') + 2,
                                            (listTT.get(j).getSlotofsubjdate()).indexOf('?') + 3)
                                    .equals("2")) {
                                tt = new Timetable();
                                tt.setTimetableid(listTT.get(j).getTimetableid()); // Timetable ID Of Session 2
                                att.setPresent(getDTCSDTO.get(i).getPresent());
                                att.setNote(getDTCSDTO.get(i).getNote());
                                att.setTimetableid(tt);
                                att.setStuid(st);
                                att.setRemoveat("No");
                                attendanceService.createAttendance(att);
                            }
                        }
                    }
                    break;
            }
        }
        return true;
    }

    @RequestMapping(value = "/updateAttendance")
    public boolean updateAttendance(@RequestBody List<GetDataToCreateAttendanceDTO> getDTCSDTO) throws IOException {
        for (int i = 0; i < getDTCSDTO.size(); i++) {
            attendanceService.updateAttendance(getDTCSDTO.get(i).getPresent(), getDTCSDTO.get(i).getNote(), getDTCSDTO.get(i).getAttenid());
        }
        return true;
    }

    //	// Check Session Before Call Create Function
    //	@GetMapping(value = "/checkSessionCreate/{studengroupID}/{semID}/{subjectID}", produces = "application/json")
    //	public ResponseEntity<List<GetDataToCreateAttendanceDTO>> checkSessionCreate(
    //			@PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
    //			@PathVariable("subjectID") int subjectId) {
    //		List<GetDataToCreateAttendanceDTO> renderData = null;
    //		try {
    //			renderData = attendanceService.getStudentInStudentGroupToCreate(studengroupID, semID, subjectId);
    //			return new ResponseEntity<>(renderData, HttpStatus.OK);
    //		} catch (Exception e) {
    //			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //		}
    //	}

    // Get All Branch Campus In Function Search
    @GetMapping(value = "/getAllBranchCampus", produces = "application/json")
    public ResponseEntity<List<BranchCampusDTO>> getAllBranchCampus() {
        List<BranchCampusDTO> list = new ArrayList<>();
        try {
            for (BranchCampus branchCampus : attendanceService.getAllBranchCampus()) {
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

    // Get Student Group By Branch Campus Id
    @GetMapping(value = "/getStudentGroupByBranchCampusId/{branchcampusID}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> getStudentGroupByBranchCampusId(
            @PathVariable("branchcampusID") int branchcampusID) {
        List<StudentGroupDTO> list = new ArrayList<>();
        BranchCampus getID = new BranchCampus();
        getID.setBranchcamid(branchcampusID);
        try {
            for (StudentGroup stuGroup : attendanceService.getStudentGroupByBranchCampusId(getID)) {
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
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get Semester By Student Group Id
    @GetMapping(value = "/getSemsterByStudentGroupId/{studengroupID}", produces = "application/json")
    public ResponseEntity<List<SemesterDTO>> getSemsterByStudentGroupId(
            @PathVariable("studengroupID") int studengroupID) {
        List<SemesterDTO> list = new ArrayList<>();
        try {
            for (Integer sem : attendanceService.getSemsterByStudentGroupId(studengroupID)) {
                SemesterDTO semDTO = new SemesterDTO();
                semDTO.setSemid(sem);
                semDTO.setSemnm("Semester " + sem);
                list.add(semDTO);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get Subject By Semester Id And Student Group Id
    @GetMapping(value = "/getSubjectBySemsterIdAndStudentGroupId/{studengroupID}/{semID}", produces = "application/json")
    public ResponseEntity<List<SearchSubjectAttendance>> getSubjectBySemsterIdAndStudentGroupId(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID) {
        List<SearchSubjectAttendance> list = null;
        try {
            list = attendanceService.getSubjectBySemsterIdAndStudentGroupId(studengroupID, semID);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get Subject Date By Semester Id And Student Group Id And Subject Id
    @GetMapping(value = "/getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId/{studengroupID}/{semID}/{subjID}", produces = "application/json")
    public ResponseEntity<List<SubjectDateInTimetableDTO>> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
            @PathVariable("subjID") int subjID) {
        List<SubjectDateInTimetableDTO> list = new ArrayList<>();
        StudentGroup getID = new StudentGroup();
        getID.setStugroid(studengroupID);
        try {
            for (Timetable timetableDTO : attendanceService
                    .getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(getID, semID, subjID)) {
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
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Render List Student In Student Group To Create Function By Subject Id,
    // Semester Id, Student Group Id
    @GetMapping(value = "/renderListStudentToCreate/{studengroupID}/{semID}/{subjectID}", produces = "application/json")
    public ResponseEntity<List<GetDataToCreateAttendanceDTO>> renderListStudentToCreate(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
            @PathVariable("subjectID") int subjectId) {
        List<GetDataToCreateAttendanceDTO> renderDataCreate = null;
        try {
            renderDataCreate = attendanceService.getStudentInStudentGroupToCreate(studengroupID, semID, subjectId);
            return new ResponseEntity<>(renderDataCreate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Render List Student In Student Group To Update Function By Subject Id,
    // Semester Id, Student Group Id
    @GetMapping(value = "/renderListStudentToUpdate/{studengroupID}/{semID}/{subjectID}/{subjectDate}/{sessionVal}", produces = "application/json")
    public ResponseEntity<List<GetDataToCreateAttendanceDTO>> renderListStudentToUpdate(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
            @PathVariable("subjectID") int subjectId, @PathVariable("subjectDate") String subjectDate,
            @PathVariable("sessionVal") String sessionVal) {
        List<GetDataToCreateAttendanceDTO> renderDataUpdate = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(subjectDate);
            renderDataUpdate = attendanceService.getStudentInStudentGroupToUpdate(studengroupID, semID, subjectId, date,
                    sessionVal);
            return new ResponseEntity<>(renderDataUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Find Exists To Select Function
    @GetMapping(value = "/findCheckExist/{studentgroupID}/{semesterID}/{subjectID}/{subjectDate}/{sessionVal}", produces = "application/json")
    public ResponseEntity<Integer> findCheckExist(@PathVariable("studentgroupID") int studentgroupID,
                                                  @PathVariable("semesterID") int semesterID, @PathVariable("subjectID") int subjectID,
                                                  @PathVariable("subjectDate") String subjectDate, @PathVariable("sessionVal") String sessionVal) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(subjectDate);
            int checkExist = attendanceService.checkExist(studentgroupID, semesterID, subjectID, date, sessionVal);
            return new ResponseEntity<>(checkExist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Render Attendance Report
    @GetMapping(value = "/renderAttendanceReport/{studengroupID}/{semID}/{subjectID}", produces = "application/json")
    public ResponseEntity<List<ReportAttendance>> renderAttendanceReport(
            @PathVariable("studengroupID") int studengroupID, @PathVariable("semID") int semID,
            @PathVariable("subjectID") int subjectId) {
        List<ReportAttendance> renderData = null;
        try {
            renderData = attendanceService.getAttendanceReport(studengroupID, semID, subjectId);
            return new ResponseEntity<>(renderData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
