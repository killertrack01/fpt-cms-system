package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.CourseDTO;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.text.ParseException;

import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.dto.LecturerDTO;
import com.example.fptacademysystem.dto.StudentClassDTO;
import com.example.fptacademysystem.repository.BranchRepository;
import com.example.fptacademysystem.repository.LecturerAccountRepository;
import com.example.fptacademysystem.repository.LecturerRepository;
import com.example.fptacademysystem.repository.StudentClassRepository;
import com.example.fptacademysystem.repository.TimetableRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import org.springframework.data.domain.Sort;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LecturerService implements ILecturer {

    @Autowired
    LecturerRepository repo;

    @Autowired
    LecturerAccountRepository accrepo;

    @Autowired
    BranchRepository branchrepo;

    @Autowired
    TimetableRepository timetableRepository;

    @Autowired
    StudentClassRepository stuclassrepo;

    @Autowired
    StudentService studentService;

    @Override
    public List<Lecturer> findAll() {
        return repo.findAllLecturers(Sort.by("fullnm").ascending());
    }

    @Override
    public int findNewLecturer() {
        return repo.findNewLecturer();
    }

    @Override
    public void postCreate(LecturerDTO teacher) {
        // tao lecturer
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = new Date();
        Date doi = new Date();
        //save file on the system
        MultipartFile file = teacher.getFile();
        if (!file.getOriginalFilename().isEmpty())
        {
            try (BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("../cms-academy-training-system/src/main/resources/static/dist/img/teachers",
                                    file.getOriginalFilename()))))
            {
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException ex)
            {
                Logger.getLogger(LecturerService.class.getName()).log(Level.SEVERE, null, ex);
            }
            String img = StringUtils.cleanPath(file.getOriginalFilename());
            try
            {
                dob = format.parse(teacher.getDob());
                doi = format.parse(teacher.getDoi());
            } catch (ParseException ex)
            {
                Logger.getLogger(LecturerService.class.getName()).log(Level.SEVERE, null, ex);
            }
            Lecturer lecturer = new Lecturer();
            lecturer.setRollnum(teacher.getRollnum());
            lecturer.setFullnm(teacher.getFullnm());
            lecturer.setDob(dob);
            lecturer.setIdcard(teacher.getIdcard());
            lecturer.setGender(teacher.getGender());
            lecturer.setEmail(teacher.getEmail());
            lecturer.setCompanyemail(teacher.getCompanyemail());
            lecturer.setPoi(teacher.getPoi());
            lecturer.setDoi(doi);
            lecturer.setPhone(teacher.getPhone());
            lecturer.setImg(img);
            lecturer.setContract(teacher.getContract());
            lecturer.setMajor(teacher.getMajor());
            lecturer.setLecturertype(teacher.getLecturertype());
            lecturer.setAddress(teacher.getAddress());
            lecturer.setRemoveat("No");
            repo.save(lecturer);
        }

    }

    @Override
    public Optional<Lecturer> findById(int lecturid) {
        return repo.findById(lecturid);
    }

    @Override
    public void postUpdate(LecturerDTO teacher) {
        // tao lecturer
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = new Date();
        Date doi = new Date();
        String img = "";
        Optional<Lecturer> exist = repo.findById(Integer.parseInt(teacher.getLecturid()));

        try
        {
            dob = format.parse(teacher.getDob());
            doi = format.parse(teacher.getDoi());
        } catch (ParseException ex)
        {
            Logger.getLogger(LecturerService.class.getName()).log(Level.SEVERE, null, ex);
        }

        Lecturer lecturer = new Lecturer();
        lecturer.setLecturid(Integer.parseInt(teacher.getLecturid()));
        lecturer.setRollnum(teacher.getRollnum());
        lecturer.setFullnm(teacher.getFullnm());
        lecturer.setDob(dob);
        lecturer.setIdcard(teacher.getIdcard());
        lecturer.setGender(teacher.getGender());
        lecturer.setEmail(teacher.getEmail());
        lecturer.setCompanyemail(teacher.getCompanyemail());
        lecturer.setPoi(teacher.getPoi());
        lecturer.setDoi(doi);
        lecturer.setPhone(teacher.getPhone());
        lecturer.setContract(teacher.getContract());
        lecturer.setMajor(teacher.getMajor());
        lecturer.setLecturertype(teacher.getLecturertype());
        lecturer.setAddress(teacher.getAddress());
        lecturer.setRemoveat("No");

        //save file on the system
        MultipartFile file = teacher.getFile();
        if (!file.getOriginalFilename().isEmpty())
        {
            try (BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("../cms-academy-training-system/src/main/resources/static/dist/img/teachers", file.getOriginalFilename()))))
            {
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException ex)
            {
                Logger.getLogger(LecturerService.class.getName()).log(Level.SEVERE, null, ex);
            }
            img = StringUtils.cleanPath(file.getOriginalFilename());
            lecturer.setImg(img);
            repo.save(lecturer);
        } else
        {
            img = exist.get().getImg();
            lecturer.setImg(img);
            repo.save(lecturer);
        }
    }

    @Override
    public boolean deleteLecturer(int lecturid) {
        List<Timetable> listTimetable = timetableRepository.findAll();
        for (Timetable timetable : listTimetable)
        {
            if(timetable.getAttenteaid() == lecturid){
                return false;
            }
        }
        repo.deleteLecturer(lecturid);
        return true;
    }

    @Override
    public Lecturer findLecturer(int lecturid) {
        return repo.findLecturer(lecturid);
    }

    @Override
    public Lecturer findLecturerByRollnum(String rollnum) {
        return repo.findLecturerByRollnum(rollnum);
    }

    @Override
    public List<Branch> findAllBranch() {
        return repo.findAllBranch();
    }

    @Override
    public List<Subject> findAllSubject(Lecturer lecturer) {
        String branchnm = lecturer.getMajor();
        Branch branch = repo.findBranchByMajor(branchnm);
        Branch b = new Branch(branch.getBranchid());
        List<Courses> courses = repo.findCoursesByBranch(b);
        List<SubjectSubjectdetails> listDetails = new ArrayList<>();
        List<Subject> listSubject = new ArrayList<>();
        for (Courses c : courses)
        {
            List<SubjectSubjectdetails> list = repo.findSubjectDetails(c.getCourid());
            listDetails.addAll(list);
        }
        for (SubjectSubjectdetails s : listDetails)
        {
            Subject subject = repo.findOneSubject(s.getSubjid());
            listSubject.add(subject);
        }
        return listSubject;
    }

    @Override
    public List<StudentClassDTO> findStudentByStudentGroupId(int stugroid) {
        StudentGroup group = new StudentGroup(stugroid);
        List<StudentClass> list = repo.findStudentByStudentGroupId(group);
        List<StudentClassDTO> listDTO = new ArrayList<>();
        for (StudentClass s : list)
        {
            StudentClassDTO dto = new StudentClassDTO();
            dto.setStudclassid(s.getStudclassid());
            dto.setStugroid(s.getStugroid().getStugroid());
            dto.setCass(s.getCass());
            Student student = studentService.findOne(s.getStuid().getStuid());
            dto.setStuid(s.getStuid().getStuid());
            dto.setStudentRoll(student.getRollnum());
            dto.setStudentName(student.getFullnm());
            dto.setStudentemail(student.getEmail());
            listDTO.add(dto);
        }
        return listDTO;
    }

    @Override
    public List<CourseDTO> findAllCoursesByBranch(Lecturer lecturer) {
        String branchnm = lecturer.getMajor();
        Branch branch = repo.findBranchByMajor(branchnm);
        Branch b = new Branch(branch.getBranchid());
        List<Courses> courses = repo.findCoursesByBranch(b);
        if(courses.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        List<CourseDTO> list = new ArrayList<>();
        for (Courses course : courses)
        {
            Branch br = branchrepo.findById(course.getBranchid().getBranchid()).get();
            CourseDTO dto = new CourseDTO();
            dto.setCourid(course.getCourid());
            dto.setCournm(course.getCournm());
            dto.setBranchid(br.getBranchid());
            dto.setBranchnm(br.getBranchnm());

            list.add(dto);
        }
        return list;
    }

    @Override
    public List<RenderStudentgroup> getAllStudentGroupByCourses(int courID) {
        return repo.getAllStudentGroupByCourses(courID);
    }

    @Override
    public List<RenderStudentgroup> getTimetableByLecturerCourseGroupId(Lecturer lecturer) {
        List<RenderStudentgroup> list = repo.getAllStudentGroup();
        List<RenderStudentgroup> checkList = new ArrayList<>();
        if(!list.isEmpty()){
            for (RenderStudentgroup rs : list)
            {
                List<RenderTimetable> listTimetable = repo.getTimetableByLecturerCourseGroupId(rs.getCourid(), lecturer.getLecturid(), rs.getStugroid());
                if(listTimetable.isEmpty()){
                    checkList.add(rs);
                }
            }
            list.removeAll(checkList);
            return list;
        }else{
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentGroup> getStudentGroupByBranchCampusId(BranchCampus branchcampusID, int teacherID) {
        // TODO Auto-generated method stub
        return repo.getStudentGroupByBranchCampusId(branchcampusID, teacherID);
    }

    @Override
    public List<Timetable> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(StudentGroup stugroID, int semID,
                                                                                  int subjID,int teacherID) {
        // TODO Auto-generated method stub
        return repo.getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(stugroID, semID, subjID, teacherID);
    }

    // Render Attendance By Teacher ID
    @Override
    public List<ReportAttendance> getAttendanceReport(int stugroid, int semid, int subjid, int teachid) {
        return repo.getAttendanceReport(stugroid, semid, subjid, teachid);
    }

    @Override
    public List<Lecturer> getAlllecture() {
        // TODO Auto-generated method stub
        return repo.getAlllecture();
    }

    @Override
    public List<RenderTeachingHours> renderteachinghours(int teaID, int yearVal, int monthVal) {
        // TODO Auto-generated method stub
        return repo.renderteachinghours(teaID, yearVal, monthVal);
    }

    @Override
    public List<String> groupByRenderteachinghours(int teaID, int yearVal, int monthVal) {
        // TODO Auto-generated method stub
        return repo.groupByRenderteachinghours(teaID, yearVal, monthVal);
    }




    @Override
    public List<RenderTeachingHours> renderAllTeachingHours(int yearVal, int monthVal) {
        // TODO Auto-generated method stub
        return repo.renderAllTeachingHours(yearVal, monthVal);
    }

    @Override
    public List<String> groupByAllRenderTeachingHours(int yearVal, int monthVal) {
        // TODO Auto-generated method stub
        return repo.groupByAllRenderTeachingHours(yearVal, monthVal);
    }

//    @Override
//    public boolean checkTheRightToEditStudentAttendance(int stugroID, int semID, int subjID, Date subjDate,
//                                                        String sessionVal) {
//        // TODO Auto-generated method stub
//        return repo.checkTheRightToEditSA(stugroID, semID, subjID, subjDate, sessionVal);
//    }
}
