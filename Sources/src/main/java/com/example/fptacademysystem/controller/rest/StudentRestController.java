package com.example.fptacademysystem.controller.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.fptacademysystem.dto.*;
import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.StudentClassRepository;
import com.example.fptacademysystem.services.ParentService;
import com.example.fptacademysystem.services.StudentService;
import com.example.fptacademysystem.services.StudentAccountService;
import com.example.fptacademysystem.services.ParentAccountService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @Autowired
    StudentService studentService;
    @Autowired
    ParentService parentService;
    @Autowired
    ParentAccountService parentAccountService;
    @Autowired
    StudentAccountService studentAccountService;
    @Autowired
    StudentClassRepository studentClassService;

    /*
     * Admin
     */
    @PostMapping(value = "/admin/students/postCreate", produces = "application/json", consumes = { "multipart/form-data" })
    public String create(@ModelAttribute StudentDTO st) throws IOException {
        MultipartFile file = st.getImg();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = new Date();
        Date doi = new Date();

        // Check unique
        JSONObject jsonObject = new JSONObject();
        List<Student> studentList = studentService.findAll();
        List<Parent> parentList = parentService.findAll();
        String message = "";
        try {

            for (Student Student : studentList) {
                // kiem tra College email da ton tai chua
                if (st.getCollegeemail().equals(Student.getCollegeemail())) {
                    message = "College email";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
                // kiem tra Email da ton tai chua
                if (st.getEmail().equals(Student.getEmail())) {
                    message = "Email";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
                // kiem tra Mobile phone da ton tai chua
                if (st.getMobphone().equals(Student.getMobphone())) {
                    message = "Mobile phone";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
                // kiem tra Idcard da ton tai chua
                if (st.getIdcard().equals(Student.getIdcard())) {
                    message = "IDCard";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
            }

            for (Parent Parent : parentList) {
                // kiem tra Parent email da ton tai chua
                if (st.getParemail().equals(Parent.getParemail())) {
                    message = "Parent email";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
                // kiem tra Parent phone da ton tai chua
                if (st.getParphone().equals(Parent.getParphone())) {
                    message = "Parent Phone";
                    jsonObject.put("title", "error");
                    jsonObject.put("message", message + " Has Been Existed");
                    return jsonObject.toString();
                }
            }

            // ----------------------

            try {
                dob = format.parse(st.getDob());
                doi = format.parse(st.getDoi());
            } catch (ParseException ex) {
                Logger.getLogger(StudentRestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!file.getOriginalFilename().isEmpty()) {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
                        new File("../cms-academy-training-system/src/main/resources/static/dist/img/students",
                                file.getOriginalFilename())))) {
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }

                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                // Create student
                Student s = new Student();
                s.setFullnm(st.getFullnm());
                s.setEmail(st.getEmail());
                s.setCollegeemail(st.getCollegeemail());
                s.setRollnum("student");
                s.setDob(dob);
                s.setGender(st.getGender());
                s.setAddress(st.getAddress());
                s.setMobphone(st.getMobphone());
                s.setImg(filename);
                s.setIdcard(st.getIdcard());
                s.setDoi(doi);
                s.setPoi(st.getPoi());
                s.setStustatus("Studying");
                s.setMajor(st.getMajor());
                s.setRemoveat("No");
                StudentGroup stugroid = new StudentGroup();
                stugroid.setStugroid(st.getClassid());
                studentService.createStudent(s);

                // Asign rollnum
                // MAY BE CAUSE ERROR
                Student studentId = new Student();
                studentId.setStuid(studentService.findNewStudent());
                String rollnum = "Student" + studentId.getStuid();
                s.setRollnum(rollnum);
                // MAY BE CAUSE ERROR

                // Create student account
                StudentAccount studAcc = new StudentAccount();
                studAcc.setRollnum(rollnum);
                studAcc.setStuid(studentId);
                studAcc.setPass("123");
                studAcc.setRemoveat("No");
                studentAccountService.SaveStudentAccount(studAcc);
                // set rollnum for student

                Parent p = new Parent();
                p.setParnm(st.getParnm());
                // set rollnum for Parent
                p.setRollnum(rollnum);
                p.setParjob(st.getParjob());
                p.setParphone(st.getParphone());
                p.setParemail(st.getParemail());
                p.setAddress(st.getPaddress());
                p.setPow(st.getPow());
                p.setRemoveat("No");
                p.setStuid(studentId);

                parentService.createParent(p);

                // Create student account
                Parent parId = new Parent();
                parId.setParid(parentService.findNewParent());
                ParentAccount parAcc = new ParentAccount();
                parAcc.setRollnum(rollnum);
                parAcc.setParid(parId);
                parAcc.setPass("123");
                parAcc.setRemoveat("No");
                parentAccountService.SaveParentAccount(parAcc);

                message = "Create success";
                jsonObject.put("title", "success");
                jsonObject.put("message", message);
                return jsonObject.toString();

            } else {

                String filename = "default_user.png";

                // Create student
                Student s = new Student();
                s.setFullnm(st.getFullnm());
                s.setEmail(st.getEmail());
                s.setCollegeemail(st.getCollegeemail());
                s.setRollnum("student");
                s.setDob(dob);
                s.setGender(st.getGender());
                s.setAddress(st.getAddress());
                s.setMobphone(st.getMobphone());
                s.setImg(filename);
                s.setIdcard(st.getIdcard());
                s.setDoi(doi);
                s.setPoi(st.getPoi());
                s.setStustatus("Studying");
                s.setMajor(st.getMajor());
                s.setRemoveat("No");
                StudentGroup stugroid = new StudentGroup();
                stugroid.setStugroid(st.getClassid());
                studentService.createStudent(s);

                // Asign rollnum
                // MAY BE CAUSE ERROR
                Student studentId = new Student();
                studentId.setStuid(studentService.findNewStudent());
                String rollnum = "Student" + studentId.getStuid();
                s.setRollnum(rollnum);
                // MAY BE CAUSE ERROR

                // Create student account
                StudentAccount studAcc = new StudentAccount();
                studAcc.setRollnum(rollnum);
                studAcc.setStuid(studentId);
                studAcc.setPass("123");
                studAcc.setRemoveat("No");
                studentAccountService.SaveStudentAccount(studAcc);
                // set rollnum for student

                Parent p = new Parent();
                p.setParnm(st.getParnm());
                // set rollnum for Parent
                p.setRollnum(rollnum);
                p.setParjob(st.getParjob());
                p.setParphone(st.getParphone());
                p.setParemail(st.getParemail());
                p.setAddress(st.getPaddress());
                p.setPow(st.getPow());
                p.setRemoveat("No");
                p.setStuid(studentId);

                parentService.createParent(p);

                // Create student account
                Parent parId = new Parent();
                parId.setParid(parentService.findNewParent());
                ParentAccount parAcc = new ParentAccount();
                parAcc.setRollnum(rollnum);
                parAcc.setParid(parId);
                parAcc.setPass("123");
                parAcc.setRemoveat("No");
                parentAccountService.SaveParentAccount(parAcc);

                message = "Create success";
                jsonObject.put("title", "success");
                jsonObject.put("message", message);
                return jsonObject.toString();
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    @PutMapping(value = "/admin/students/postEdit", produces = "application/json")
    public String edit(StudentDTO st) throws IOException {
        MultipartFile file = st.getImg();
        String filename;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = new Date();
        Date doi = new Date();
        // Check unique
        JSONObject jsonObject = new JSONObject();
        List<Student> studentList = studentService.findAll();
        List<Parent> parentList = parentService.findAll();
        String message = "";
        try {

            for (Student Student : studentList) {
                if (!Student.getStuid().equals(st.getStuid())) {
                    // kiem tra College email da ton tai chua
                    if (st.getCollegeemail().equals(Student.getCollegeemail())) {
                        message = "College email";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Email da ton tai chua
                    if (st.getEmail().equals(Student.getEmail())) {
                        message = "Email";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Mobile phone da ton tai chua
                    if (st.getMobphone().equals(Student.getMobphone())) {
                        message = "Mobile phone";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Idcard da ton tai chua
                    if (st.getIdcard().equals(Student.getIdcard())) {
                        message = "IDCard";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                } else {
                    continue;
                }
            }
            for (Parent Parent : parentList) {
                Student stu = new Student();
                stu.setStuid(st.getStuid());
                Parent parent = parentService.findParentByStudentId(stu);
                if (!Parent.getParid().equals(parent.getParid())) {
                    // kiem tra Parent email da ton tai chua
                    if (st.getParemail().equals(Parent.getParemail())) {
                        message = "Parent email";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Parent phone da ton tai chua
                    if (st.getParphone().equals(Parent.getParphone())) {
                        message = "Parent Phone";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                } else {
                    continue;
                }
            }

            // ----------------------
            try {
                dob = format.parse(st.getDob());
                doi = format.parse(st.getDoi());
            } catch (ParseException ex) {
                Logger.getLogger(StudentRestController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!file.getOriginalFilename().isEmpty()) {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
                        new File("../cms-academy-training-system/src/main/resources/static/dist/img/students",
                                file.getOriginalFilename())))) {
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    filename = StringUtils.cleanPath(file.getOriginalFilename());
                }

                Student s = new Student();
                s = studentService.findOne(st.getStuid());
                s.setFullnm(st.getFullnm());
                s.setEmail(st.getEmail());
                s.setCollegeemail(st.getCollegeemail());
                s.setDob(dob);
                s.setGender(st.getGender());
                s.setAddress(st.getAddress());
                s.setMobphone(st.getMobphone());
                s.setImg(filename);
                s.setIdcard(st.getIdcard());
                s.setDoi(doi);
                s.setPoi(st.getPoi());
                s.setMajor(st.getMajor());
                StudentGroup stugroid = new StudentGroup();
                stugroid.setStugroid(st.getClassid());
                studentService.editStudent(s);

                if (!st.getStudentPass().equals("")) {
                    StudentAccount stacc = new StudentAccount();
                    stacc = studentAccountService.findByStudentId(s);
                    stacc.setPass(st.getStudentPass());
                    studentAccountService.SaveStudentAccount(stacc);
                }

                Parent p = new Parent();
                p = parentService.findParentByStudentId(s);
                p.setParnm(st.getParnm());
                p.setParjob(st.getParjob());
                p.setParphone(st.getParphone());
                p.setParemail(st.getParemail());
                p.setAddress(st.getPaddress());
                p.setPow(st.getPow());

                parentService.editParent(p);

                message = "Update success";
                jsonObject.put("title", "success");
                jsonObject.put("message", message);
                return jsonObject.toString();
            } else {
                Student s = new Student();
                s = studentService.findOne(st.getStuid());
                s.setFullnm(st.getFullnm());
                s.setEmail(st.getEmail());
                s.setCollegeemail(st.getCollegeemail());
                s.setDob(dob);
                s.setGender(st.getGender());
                s.setAddress(st.getAddress());
                s.setMobphone(st.getMobphone());
                s.setImg(s.getImg());
                s.setIdcard(st.getIdcard());
                s.setDoi(doi);
                s.setPoi(st.getPoi());
                s.setMajor(st.getMajor());
                StudentGroup stugroid = new StudentGroup();
                stugroid.setStugroid(st.getClassid());
                studentService.editStudent(s);

                if (!st.getStudentPass().equals("")) {
                    StudentAccount stacc = new StudentAccount();
                    stacc = studentAccountService.findByStudentId(s);
                    stacc.setPass(st.getStudentPass());
                    studentAccountService.SaveStudentAccount(stacc);
                }

                Parent p = new Parent();
                p = parentService.findParentByStudentId(s);
                p.setParnm(st.getParnm());
                p.setParjob(st.getParjob());
                p.setParphone(st.getParphone());
                p.setParemail(st.getParemail());
                p.setAddress(st.getPaddress());
                p.setPow(st.getPow());

                parentService.editParent(p);

                message = "Update success";
                jsonObject.put("title", "success");
                jsonObject.put("message", message);
                return jsonObject.toString();
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    private StudentDTO findExistStudentInfo(int id) {
        Student st = new Student();
        st = studentService.findOne(id);
        Parent pa = new Parent();
        pa = parentService.findParentByStudentId(st);
        StudentAccount stacc = new StudentAccount();
        stacc = studentAccountService.findByStudentId(st);
        StudentDTO sdto = new StudentDTO();

        sdto.setFullnm(st.getFullnm());
        sdto.setRollnum(st.getRollnum());
        sdto.setEmail(st.getEmail());
        sdto.setCollegeemail(st.getCollegeemail());
        sdto.setDob(st.getDob().toString());
        sdto.setGender(st.getGender());
        sdto.setAddress(st.getAddress());
        sdto.setMobphone(st.getMobphone());
        sdto.setStudentavatarname(st.getImg());
        sdto.setIdcard(st.getIdcard());
        sdto.setDoi(st.getDoi().toString());
        sdto.setPoi(st.getPoi());
        sdto.setStustatus(st.getStustatus());
        sdto.setMajor(st.getMajor());
        sdto.setStudentPass(stacc.getPass());
        sdto.setParnm(pa.getParnm());
        sdto.setParjob(pa.getParjob());
        sdto.setParphone(pa.getParphone());
        sdto.setParemail(pa.getParemail());
        sdto.setPaddress(pa.getAddress());
        sdto.setPow(pa.getPow());

        return sdto;
    }

    @GetMapping(value = "/admin/students/findStudentById/{id}")
    public ResponseEntity<StudentDTO> find(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.findExistStudentInfo(id), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/students/deletestudent/{id}")
    public void delete(@PathVariable("id") int id) {
        Student st = new Student();
        st.setStuid(id);
        Parent pa = new Parent();
        pa = parentService.findParentByStudentId(st);
        parentAccountService.deleteByParentId(pa);
        parentService.deleteParent(st);
        studentAccountService.deleteByStudentId(st);
        studentService.deleteStudent(id);
        studentClassService.deleteStudentClassByStudent(st);
    }
    /*
     * End Amin
     */

    /*
     * User
     */
    @PutMapping(value = "/user/students/postEdit", produces = "application/json")
    public String edituser(StudentDTO st) throws IOException {
        MultipartFile file = st.getImg();
        String filename;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = new Date();
        Date doi = new Date();
        // Check unique
        JSONObject jsonObject = new JSONObject();
        List<Student> studentList = studentService.findAll();
        String message = "";
        try {

            for (Student Student : studentList) {
                if (!Student.getStuid().equals(st.getStuid())) {
                    // kiem tra College email da ton tai chua
                    if (st.getCollegeemail().equals(Student.getCollegeemail())) {
                        message = "College email";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Email da ton tai chua
                    if (st.getEmail().equals(Student.getEmail())) {
                        message = "Email";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Mobile phone da ton tai chua
                    if (st.getMobphone().equals(Student.getMobphone())) {
                        message = "Mobile phone";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                    // kiem tra Idcard da ton tai chua
                    if (st.getIdcard().equals(Student.getIdcard())) {
                        message = "IDCard";
                        jsonObject.put("title", "error");
                        jsonObject.put("message", message + " Has Been Existed");
                        return jsonObject.toString();
                    }
                } else {
                    continue;
                }
            }
            // ----------------------
            try {
                dob = format.parse(st.getDob());
                doi = format.parse(st.getDoi());
            } catch (ParseException ex) {
                Logger.getLogger(StudentRestController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!file.getOriginalFilename().isEmpty()) {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
                        new File("../cms-academy-training-system/src/main/resources/static/dist/img/students",
                                file.getOriginalFilename())))) {
                    outputStream.write(file.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    filename = StringUtils.cleanPath(file.getOriginalFilename());
                }

                Student s = new Student();
                s = studentService.findOne(st.getStuid());
                s.setFullnm(st.getFullnm());
                s.setEmail(st.getEmail());
                s.setCollegeemail(st.getCollegeemail());
                s.setDob(dob);
                s.setGender(st.getGender());
                s.setAddress(st.getAddress());
                s.setMobphone(st.getMobphone());
                s.setImg(filename);
                s.setIdcard(st.getIdcard());
                s.setDoi(doi);
                s.setPoi(st.getPoi());
                s.setMajor(st.getMajor());
                StudentGroup stugroid = new StudentGroup();
                stugroid.setStugroid(st.getClassid());
                studentService.editStudent(s);

                if (!st.getStudentPass().equals("")) {
                    StudentAccount stacc = new StudentAccount();
                    stacc = studentAccountService.findByStudentId(s);
                    stacc.setPass(st.getStudentPass());
                    studentAccountService.SaveStudentAccount(stacc);
                }

                message = "Update success";
                jsonObject.put("title", "success");
                jsonObject.put("message", message);
                return jsonObject.toString();
            } else {
                Student s = new Student();
                s = studentService.findOne(st.getStuid());
                s.setFullnm(st.getFullnm());
                s.setEmail(st.getEmail());
                s.setCollegeemail(st.getCollegeemail());
                s.setDob(dob);
                s.setGender(st.getGender());
                s.setAddress(st.getAddress());
                s.setMobphone(st.getMobphone());
                s.setImg(s.getImg());
                s.setIdcard(st.getIdcard());
                s.setDoi(doi);
                s.setPoi(st.getPoi());
                s.setMajor(st.getMajor());
                StudentGroup stugroid = new StudentGroup();
                stugroid.setStugroid(st.getClassid());
                studentService.editStudent(s);

                if (!st.getStudentPass().equals("")) {
                    StudentAccount stacc = new StudentAccount();
                    stacc = studentAccountService.findByStudentId(s);
                    stacc.setPass(st.getStudentPass());
                    studentAccountService.SaveStudentAccount(stacc);
                }

                message = "Update success";
                jsonObject.put("title", "success");
                jsonObject.put("message", message);
                return jsonObject.toString();
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    /*
     * End User
     */
    @GetMapping(value = "/user/students/alltimetable")
    public ResponseEntity<List<RenderTimetable>> getalltimetable(int stugroid, int semid, HttpSession session) {
        int id = (int) session.getAttribute("studentId");
        List<RenderTimetable> list = new ArrayList<>();
        List<RenderTimetable> TimetableForClass = new ArrayList<>();
        list = studentService.findAllStudentTimetable(id);
        for(RenderTimetable rt : list){

            if(rt.getStugroid() == (stugroid) && rt.getSemid() == (semid -1)){
                TimetableForClass.add(rt);
            }

        }
        return new ResponseEntity<>(TimetableForClass, HttpStatus.OK);
    }
    @GetMapping(value = "/user/students/getattendance")
    public ResponseEntity<AttendanceDTO> getallAttendance(int timetableid, HttpSession session) {
        int id = (int) session.getAttribute("studentId");
        return new ResponseEntity<>(studentService.findAllStudentAttendances(id, timetableid), HttpStatus.OK);
    }
    @GetMapping(value = "/user/student/timetable/findStudentgroup")
    public ResponseEntity<List<StudentGroupDTO>> getallStudentGroup(HttpSession session) {
        int id = (int) session.getAttribute("studentId");
        return new ResponseEntity<>(studentService.findStudentGroupByStudentId(id), HttpStatus.OK);
    }
    @GetMapping(value = "/user/student/timetable/findAllSemester")
    public ResponseEntity<List<SemesterDTO>> getallSemester() {
        return new ResponseEntity<>(studentService.findAllSemester(), HttpStatus.OK);
    }

}