package com.example.fptacademysystem.controller.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.fptacademysystem.dto.CampusDTO;
import com.example.fptacademysystem.dto.CourseDTO;
import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.dto.SemesterDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDTO;
import com.example.fptacademysystem.dto.TeacherTimetableDTO;
import com.example.fptacademysystem.dto.TimetableDTO;
import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Campus;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.MindateMaxdate;
import com.example.fptacademysystem.model.RenderLocation;
import com.example.fptacademysystem.model.RenderTimetable;
import com.example.fptacademysystem.model.Room;
import com.example.fptacademysystem.model.SearchSubject;
import com.example.fptacademysystem.model.SearchSubjectByDate;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.model.Timetable;
import com.example.fptacademysystem.services.StudentGroupService;
import com.example.fptacademysystem.services.SubjectSubjectDetailsService;
import com.example.fptacademysystem.services.TimetableService;
import com.example.fptacademysystem.model.SubjectSubjectdetails;

@RestController
@RequestMapping("/api/admin/timetable")
public class TimetableRestController {

    @Autowired
    SubjectSubjectDetailsService subjectSubjectDetailtsService;

    @Autowired
    StudentGroupService studentGroupService;

    @Autowired
    TimetableService timetableService;

    @RequestMapping(value = "/createTimetable")
    public boolean createTimetable(@RequestBody TimetableDTO tt) throws IOException {
        // Initialize Variable Value
        SubjectDetails getIDSD = new SubjectDetails();
        StudentGroup getIDSG = new StudentGroup();
        getIDSG.setStugroid(tt.getStugroid());
        Room getID = new Room();
        getID.setRoomid(1);

        // Find Student Group By StudentGroupID
        String getSessionInStudentGroup = timetableService.getStudentGroupByStudentGroupID(tt.getStugroid());

        // Find Subject By CourId And SemId
        List<SubjectSubjectdetails> listSSD = subjectSubjectDetailtsService.findSSD(tt.getCourid(), tt.getSemid());

        // Count The Total Number Of Lessons Corresponding To The Number Of School Days
        int totalDays = 0, countDays = 0;
        for (int i = 0; i < listSSD.size(); i++) {
            totalDays = totalDays + listSSD.get(i).getSlots(); // Assign The Total Number Of Lessons To The Variable
            // 'totalDays'
        }

        ArrayList<Date> schoolDays = timetableService.checkSubjectDate(tt.getStartDate(), totalDays);

        // Start The Proscess Of Creating A New Timetable
        for (int i = 0; i < listSSD.size(); i++) { // List Subject
            int numRealNumner = listSSD.get(i).getSlots() / 2;
            int numSurplus = listSSD.get(i).getSlots() % 2;
            int countSlotOfSubj = 1;

            // Real number
            for (int x = 0; x < numRealNumner; x++) {
                for (int y = 0; y < 2; y++) {
                    Timetable ttRealNumber = new Timetable();
                    getIDSD.setSubjdetailsid(listSSD.get(i).getSubjdetailsid());
                    ttRealNumber.setSubjdetailsid(getIDSD);
                    ttRealNumber.setStugroid(getIDSG);
                    ttRealNumber.setAttenteaid(tt.getLecturerid());
                    ttRealNumber.setAttenedit(false);
                    ttRealNumber.setSemid(tt.getSemid() - 1);
                    ttRealNumber.setNote("No Notes");
                    ttRealNumber.setRoomid(getID);
                    ttRealNumber.setRemoveat("No");
                    ttRealNumber.setSubjnm(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj);
                    ttRealNumber.setSubjdate(schoolDays.get(countDays));
                    switch (getSessionInStudentGroup) {
                        case "M":
                            if (y == 0) {
                                ttRealNumber.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                        + (y + 1) + "?08:00-10:00");
                            } else {
                                ttRealNumber.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                        + (y + 1) + "?10:00-12:00");
                            }
                            break;
                        case "A":
                            if (y == 0) {
                                ttRealNumber.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                        + (y + 1) + "?13:30-15:30");
                            } else {
                                ttRealNumber.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                        + (y + 1) + "?15:30:-17:30");
                            }
                            break;
                        case "N":
                            if (y == 0) {
                                ttRealNumber.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                        + (y + 1) + "?18:00-20:00");
                            } else {
                                ttRealNumber.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                        + (y + 1) + "?20:00-22:00");
                            }
                            break;
                    }

                    timetableService.createTimetable(ttRealNumber);
                    countSlotOfSubj++;
                }
                countDays++;
            }

            // Surplus
            if (numSurplus > 0) {
                for (int x = 0; x < numSurplus; x++) {
                    for (int y = 0; y < 2; y++) {
                        if (y == 0) {
                            Timetable ttSurplus = new Timetable();
                            getIDSD.setSubjdetailsid(listSSD.get(i).getSubjdetailsid());
                            ttSurplus.setSubjdetailsid(getIDSD);
                            ttSurplus.setStugroid(getIDSG);
                            ttSurplus.setAttenteaid(tt.getLecturerid());
                            ttSurplus.setAttenedit(false);
                            ttSurplus.setSemid(tt.getSemid() - 1);
                            ttSurplus.setNote("No Notes");
                            ttSurplus.setRoomid(getID);
                            ttSurplus.setRemoveat("No");
                            ttSurplus.setSubjnm(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj);
                            ttSurplus.setSubjdate(schoolDays.get(countDays));
                            switch (getSessionInStudentGroup) {
                                case "M":
                                    ttSurplus.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                            + (y + 1) + "?08:00-10:00");
                                    break;
                                case "A":
                                    ttSurplus.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                            + (y + 1) + "?13:30-15:30");
                                    break;
                                case "N":
                                    ttSurplus.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                            + (y + 1) + "?18:00-20:00");
                                    break;
                            }
                            timetableService.createTimetable(ttSurplus);
                        } else {
                            Timetable ttSurplus = new Timetable();
                            Room getNghi = new Room();
                            getNghi.setRoomid(1);
                            getIDSD.setSubjdetailsid(1);
                            ttSurplus.setSubjdetailsid(getIDSD);
                            ttSurplus.setStugroid(getIDSG);
                            ttSurplus.setAttenteaid(0);
                            ttSurplus.setAttenedit(false);
                            ttSurplus.setSemid(tt.getSemid() - 1);
                            ttSurplus.setNote("No Notes");
                            ttSurplus.setRoomid(getNghi);
                            ttSurplus.setRemoveat("No");
                            ttSurplus.setSubjnm("Day Off");
                            ttSurplus.setSubjdate(schoolDays.get(countDays));
                            switch (getSessionInStudentGroup) {
                                case "M":
                                    ttSurplus.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                            + (y + 1) + "?10:00-12:00");
                                    break;
                                case "A":
                                    ttSurplus.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                            + (y + 1) + "?15:30:-17:30");
                                    break;
                                case "N":
                                    ttSurplus.setSlotofsubjdate(listSSD.get(i).getShortnm() + "-" + countSlotOfSubj + "?S"
                                            + (y + 1) + "?20:00-22:00");
                                    break;
                            }
                            timetableService.createTimetable(ttSurplus);
                        }
                    }
                    countDays++;
                }
            }
        }
        return true;
    }

    @RequestMapping(value = "/updateTimetable")
    public boolean updateTimetable(@RequestBody TimetableDTO tt) throws IOException {
        List<Timetable> listUpdate = new ArrayList<Timetable>();
        StudentGroup stugroid = new StudentGroup();
        stugroid.setStugroid(tt.getStugroid());
        List<Timetable> listTT = timetableService.findTTByClassAndSem(stugroid, tt.getSemid());
        int count = 0;
        int amount = (listTT.size())/2;
        ArrayList<Date> schoolDate = timetableService.checkSubjectDate(tt.getStartDate(), amount);
        if (listTT.size() > 0) {
            for (int i = 0; i < listTT.size(); i += 2) {
                Timetable item1 = new Timetable();
                Timetable item2 = new Timetable();
                item1.setSubjdate(schoolDate.get(count));
                item1.setAttenteaid(tt.getLecturerid());
                item1.setTimetableid(listTT.get(i).getTimetableid());

                item2.setSubjdate(schoolDate.get(count));
                item2.setAttenteaid(tt.getLecturerid());
                item2.setTimetableid(listTT.get(i + 1).getTimetableid());

                listUpdate.add(item1);
                listUpdate.add(item2);
                count++;
            }
            timetableService.updateTimetable(listUpdate);
        }

        return true;
    }

    @RequestMapping(value = "/updateTeacherInTimetable")
    public boolean updateTeacherInTimetable(@RequestBody TeacherTimetableDTO ttt) throws IOException {
        timetableService.updateTeacherInTimetable(ttt.getTeaid(), ttt.getTimetableid());
        return true;
    }

    @RequestMapping(value = "/removeTimetable")
    public boolean removeTimetable(@RequestBody TimetableDTO tt) throws IOException {
        StudentGroup stugroid = new StudentGroup();
        stugroid.setStugroid(tt.getStugroid());
        List<Timetable> listTT = timetableService.findTTByClassAndSem(stugroid, tt.getSemid());
        for (int i = 0; i < listTT.size(); i++) {
            timetableService.deleteTimetable(listTT.get(i).getTimetableid());
        }
        return true;
    }

    @GetMapping(value = "/getAllCourse", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        List<CourseDTO> list = new ArrayList<>();
        try {
            for (Courses courses : timetableService.getAllCourses()) {
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

    @GetMapping(value = "/getStudentGroupByBranch/{branchid}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> getStudentGroupByBranch(@PathVariable("branchid") int branchid) {
        List<StudentGroupDTO> list = new ArrayList<>();
        Branch getID = new Branch();
        getID.setBranchid(branchid);
        try {
            for (StudentGroup sg : timetableService.getStudentGroupByBranch(getID)) {
                StudentGroupDTO stuDto = new StudentGroupDTO();
                stuDto.setStugroid(sg.getStugroid());
                stuDto.setStugronm(sg.getStugronm());
                stuDto.setOpeningdate(sg.getOpeningdate());
                stuDto.setSession(sg.getSession());
                stuDto.setShift(sg.getShift());
                stuDto.setCourid(sg.getCourid().getCourid());

                list.add(stuDto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllBranch", produces = "application/json")
    public ResponseEntity<List<BranchDTO>> getAllBranch() {
        List<BranchDTO> list = new ArrayList<>();
        try {
            for (Branch br : timetableService.getAllBranch()) {
                BranchDTO branchDto = new BranchDTO();
                branchDto.setBranchid(br.getBranchid());
                branchDto.setBranchnm(br.getBranchnm());
                branchDto.setRemoveat(br.getRemoveat());
                list.add(branchDto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllCampus", produces = "application/json")
    public ResponseEntity<List<CampusDTO>> getAllCampus() {
        List<CampusDTO> list = new ArrayList<>();
        try {
            for (Campus br : timetableService.getALlCampus()) {
                CampusDTO campusDto = new CampusDTO();
                campusDto.setCampusid(br.getCampusid());
                campusDto.setCampusnm(br.getCampusnm());
                list.add(campusDto);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getBranchCampusByBranchIdAndCampusId/{branchid}/{campusid}", produces = "application/json")
    public ResponseEntity<List<BranchCampusDTO>> getBranchCampusByBranchIdAndCampusId(
            @PathVariable("branchid") int branchid, @PathVariable("campusid") int campusid) throws ParseException {
        List<BranchCampusDTO> list = new ArrayList<BranchCampusDTO>();
        Branch getBrID = new Branch();
        getBrID.setBranchid(branchid);
        Campus getCaID = new Campus();
        getCaID.setCampusid(campusid);

        try {
            for (BranchCampus bc : timetableService.getBranchCampusByBranchIdAndCampusId(getBrID, getCaID)) {
                BranchCampusDTO bcDTO = new BranchCampusDTO();
                bcDTO.setBranchcamid(bc.getBranchcamid());
                bcDTO.setBranchcamnm(bc.getBranchcamnm());
                bcDTO.setAddress(bc.getAddress());
                bcDTO.setBranchid(bc.getBranchid().getBranchid());
                bcDTO.setCampusid(bc.getCampusid().getCampusid());
                list.add(bcDTO);
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
            for (Lecturer lec : timetableService.getAllLecturer()) {
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

    @GetMapping(value = "/getStudentGroupByCourses/{id}", produces = "application/json")
    public ResponseEntity<List<StudentGroupDTO>> getStudentGroupByCourses(@PathVariable("id") int id) {
        List<StudentGroupDTO> list = new ArrayList<StudentGroupDTO>();
        Courses cc = new Courses();
        cc.setCourid(id);
        try {
            for (StudentGroup student : timetableService.getStudentGroupByCourses(cc)) {
                StudentGroupDTO s = new StudentGroupDTO();

                s.setStugroid(student.getStugroid());
                s.setStugronm(student.getStugronm());
                s.setOpeningdate(student.getOpeningdate());
                s.setSession(student.getSession());
                s.setShift(student.getShift());
                s.setCourid(student.getCourid().getCourid());

                list.add(s);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getSubjectByBranch/{id}", produces = "application/json")
    public ResponseEntity<List<SubjectDTO>> getSubjectByBranch(@PathVariable("id") int id) {
        List<SubjectDTO> list = new ArrayList<SubjectDTO>();
        Courses cc = new Courses();
        cc.setCourid(id);
        try {
            for (Subject sb : timetableService.getSubjectByBranch(id)) {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setSubjid(sb.getSubjid());
                subjectDTO.setShortnm(sb.getShortnm());
                subjectDTO.setSubjnm(sb.getSubjnm());
                subjectDTO.setBranchid(sb.getBranchid());
                subjectDTO.setRemoveat(sb.getRemoveat());
                list.add(subjectDTO);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getSemesterByStudentGroup/{id}", produces = "application/json")
    public ResponseEntity<List<SemesterDTO>> getSemesterByStudentGroup(@PathVariable("id") int id) {
        List<SemesterDTO> list = new ArrayList<SemesterDTO>();
        StudentGroup sg = new StudentGroup();
        sg.setStugroid(id);
        try {
            for (Semester semester : timetableService.getSemesterByStudentGroup(sg)) {
                SemesterDTO sem = new SemesterDTO();

                sem.setSemid(semester.getSemid());
                sem.setSemnm(semester.getSemnm());

                list.add(sem);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getStudentGroupByID/{id}", produces = "application/json")
    public ResponseEntity<StudentGroupDTO> getStudentGroupByID(@PathVariable("id") int id) {
        StudentGroupDTO sgDTO = new StudentGroupDTO();
        try {
            StudentGroup sg = studentGroupService.getStudentGroupByID(id);
            sgDTO.setStugroid(sg.getStugroid());
            sgDTO.setStugronm(sg.getStugronm());
            sgDTO.setOpeningdate(sg.getOpeningdate());
            sgDTO.setShift(sg.getShift());
            sgDTO.setSession(sg.getSession());
            sgDTO.setCourid(sg.getCourid().getCourid());
            return new ResponseEntity<>(sgDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rederSemesterOfStudentGroup/{classid}/{semid}", produces = "application/json")
    public ResponseEntity<List<RenderTimetable>> rederSemesterOfStudentGroup(@PathVariable("classid") int classid,
                                                                             @PathVariable("semid") int semid) {
        List<RenderTimetable> list = null;
        try {
            if (classid > 0) {
                if (semid > 0) {
                    list = timetableService.renderSubInSemOfClass(classid, semid);
                }
            }
            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/searchSemesterByStudentGroup/{id}", produces = "application/json")
    public ResponseEntity<List<SemesterDTO>> searchSemesterByStudentGroup(@PathVariable("id") int id) {
        List<SemesterDTO> list = new ArrayList<SemesterDTO>();
        StudentGroup sg = new StudentGroup();
        sg.setStugroid(id);
        try {
            for (Semester semester : timetableService.searchSemesterByStudentGroup(sg)) {
                SemesterDTO sem = new SemesterDTO();

                sem.setSemid(semester.getSemid());
                sem.setSemnm(semester.getSemnm());

                list.add(sem);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/renderSubOfFuncSearchSubByBranch/{subjid}", produces = "application/json")
    public ResponseEntity<List<SearchSubject>> renderSubOfFuncSearchSubByBranch(@PathVariable("subjid") int subjid) {
        List<SearchSubject> list = new ArrayList<SearchSubject>();
        try {
            if (subjid > 0) {
                for (SearchSubject ss : timetableService.renderSubOfFuncSearchSubByBranch(subjid)) {
                    SearchSubject searchSubject = new SearchSubject();
                    searchSubject.setSsid(ss.getSsid());
                    searchSubject.setStudytime(ss.getStudytime());
                    searchSubject.setSubjid(ss.getSubjid());
                    searchSubject.setShortnm(ss.getShortnm());
                    searchSubject.setSubjectname(ss.getSubjectname());
                    searchSubject.setSlots(ss.getSlots());
                    searchSubject.setSemname(ss.getSemname());
                    searchSubject.setCoursename(ss.getCoursename());
                    searchSubject.setTeaname(ss.getTeaname());
                    searchSubject.setStugronm(ss.getStugronm());
                    searchSubject.setStugroid(ss.getStugroid());

                    list.add(searchSubject);
                }
            }
            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/renderSubByDate/{startDate}/{endDate}", produces = "application/json")
    public ResponseEntity<List<SearchSubjectByDate>> renderSubByDate(@PathVariable("startDate") String startDate,
                                                                     @PathVariable("endDate") String endDate) throws ParseException {
        List<SearchSubjectByDate> list = new ArrayList<SearchSubjectByDate>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = sdf.parse(startDate);
        Date edate = sdf.parse(endDate);
        try {
            for (SearchSubjectByDate ssbd : timetableService.renderSubByDate(sdate, edate)) {
                SearchSubjectByDate searchSubjectByDate = new SearchSubjectByDate();
                searchSubjectByDate.setSsbdid(ssbd.getSsbdid());
                searchSubjectByDate.setSubjid(ssbd.getSubjid());
                searchSubjectByDate.setSubjdate(ssbd.getSubjdate());
                searchSubjectByDate.setShortnm(ssbd.getShortnm());
                searchSubjectByDate.setSubjectname(ssbd.getSubjectname());
                searchSubjectByDate.setSemname(ssbd.getSemname());
                searchSubjectByDate.setCoursename(ssbd.getCoursename());
                searchSubjectByDate.setTeaname(ssbd.getTeaname());
                searchSubjectByDate.setStugronm(ssbd.getStugronm());
                searchSubjectByDate.setStugroid(ssbd.getStugroid());
                list.add(searchSubjectByDate);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/renderDataByBranchCampusID/{branchcampusid}", produces = "application/json")
    public ResponseEntity<List<RenderLocation>> renderDataByBranchCampusID(
            @PathVariable("branchcampusid") int branchcampusid) throws ParseException {
        List<RenderLocation> list = null;
        try {
            list = timetableService.renderDataByBranchCampusId(branchcampusid);
            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getMinMaxOfSem/{id}", produces = "application/json")
    public ResponseEntity<List<MindateMaxdate>> getMinMaxOfSem(@PathVariable("id") int id) {
        // StudentGroup getID = new StudentGroup();
        // getID.setStugroid(id);
        List<MindateMaxdate> list = new ArrayList<MindateMaxdate>();
        try {
            for (MindateMaxdate tt : timetableService.getMinMaxDateOfSem(id)) {
                list.add(tt);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getLecturerByID/{id}", produces = "application/json")
    public ResponseEntity<LecturerDTO> getLecturerByID(@PathVariable("id") int id) {
        LecturerDTO lecturerDTO = new LecturerDTO();
        try {
            Lecturer sg = timetableService.getLecturerById(id);
            lecturerDTO.setLecturid(String.valueOf(sg.getLecturid()));
            lecturerDTO.setFullnm(sg.getFullnm());
            lecturerDTO.setRollnum(sg.getRollnum());
            return new ResponseEntity<>(lecturerDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getMinMaxDateToCheck/{stugroid}/{semid}/{inputDate}", produces = "application/json")
    public ResponseEntity<MindateMaxdate> getMinMaxDateToCheck(@PathVariable("stugroid") int stugroid,
                                                               @PathVariable("semid") int semid, @PathVariable("inputDate") String inputDate) {
        MindateMaxdate minMaxDTO = new MindateMaxdate();
        try {
            Date dateinput = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);

            int slots = timetableService.getMinMaxDateToCheck(stugroid, semid);
            int count = 0;
            ArrayList<Date> schoolDate = timetableService.checkSubjectDate(dateinput, slots);

            minMaxDTO.setMindate(schoolDate.get(0));
            minMaxDTO.setMaxdate(schoolDate.get(schoolDate.size() - 1));
            return new ResponseEntity<>(minMaxDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
