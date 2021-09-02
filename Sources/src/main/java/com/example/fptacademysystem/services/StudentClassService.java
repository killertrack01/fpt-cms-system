package com.example.fptacademysystem.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.fptacademysystem.dto.StudentDTO;
import com.example.fptacademysystem.dto.StudentGroupDTO;
import com.example.fptacademysystem.dto.SubjectDetailsDTO;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.NewClass;
import com.example.fptacademysystem.model.NewStudent;
import com.example.fptacademysystem.model.RenderStudentgroup;
import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.repository.CourseRepository;
import com.example.fptacademysystem.repository.StudentClassRepository;
import com.example.fptacademysystem.repository.StudentGroupRepository;
import com.example.fptacademysystem.repository.StudentRepository;
import com.example.fptacademysystem.repository.SubjectDetailRepository;
import com.example.fptacademysystem.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentClassService implements IStudentclass{

    @Autowired
    StudentClassRepository studentClassrepo;

    @Autowired
    CourseRepository courserepo;

    @Autowired
    StudentGroupRepository sgrouprepo;

    @Autowired
    SubjectDetailRepository subjdetailrepo;

    @Autowired
    SubjectRepository subjrepo;

    @Autowired
    StudentRepository studentrepo;

    @Override
    public void save(StudentClass stcl) {
        studentClassrepo.save(stcl);
    }

    @Override
    public List<Courses> getAllCourse() {
        return courserepo.findAll();
    }

    @Override
    public List<NewStudent> findNewStudent(String major) {
        return studentClassrepo.findNewStudent(major);
    }

    @Override
    public List<NewClass> findNewClassByCourseId(int courseid) {
        return studentClassrepo.findNewClassByCourseId(courseid);
    }

    @Override
    public int numberOfStudentInClass(int StudentGroupId) {
        int count = 0;
        StudentGroup sg = new StudentGroup();
        sg.setStugroid(StudentGroupId);
        List<StudentClass> list = new ArrayList<>();
        list = studentClassrepo.findStudentByStudentGroupId(sg);
        for (StudentClass studentclass :list){
            count++;
        }
        return count;
    }


    // New

    //find current subject detail id in class
    @Override
    public int findCurrentSubjectDetailId(int stugroid) {

        String date = LocalDate.now().toString();
        return studentClassrepo.findCurrentSubjectByStudentGroupId(stugroid, date);
    }

    //check if the subject is complete
    @Override
    public boolean checkSubjectEnd(int stugroid, int subjdetailid) {

        Date lastsubjdate = studentClassrepo.findSubjectEndDay(stugroid, subjdetailid);
        Date nextsubjdate = studentClassrepo.findSubjectStartDay(stugroid, subjdetailid + 1);
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);

        System.out.println(lastsubjdate);
        System.out.println(nextsubjdate);
        System.out.println(currentdate);

        if(!lastsubjdate.after(currentdate) && !nextsubjdate.before(currentdate) ){
            return true;
        }else{
            return false;
        }
    }

    //check if the subject is complete
    @Override
    public boolean checkSubjectStart(int stugroid, int subjdetailid) {

        Date date = studentClassrepo.findSubjectStartDay(stugroid, subjdetailid);
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);

        if(date.after(currentdate)){
            return true;
        }else{
            return false;
        }
    }

    //get all studentGroup by course
    @Override
    public List<RenderStudentgroup> getStudentGroups(int courseid) {
        List<RenderStudentgroup> list = new ArrayList<>();
        for (int studentGroupId :studentClassrepo.findAllStudyingStudentGroupId(courseid)){
            StudentGroup sg = new StudentGroup();
            RenderStudentgroup rsg = new RenderStudentgroup();
            sg = sgrouprepo.findById(studentGroupId).get();

            rsg.setStugroid(studentGroupId);
            rsg.setStugronm(sg.getStugronm());

            list.add(rsg);
        }
        return list;
    }
    //find available student group by course and subject detail from current student group
    @Override
    public List<StudentGroupDTO> findAvailableStudentGroups(int courseid, int stugroid) {

        int subjdetailsid= this.findCurrentSubjectDetailId(stugroid);
        List<Integer> studentGroupIdList = studentClassrepo.findStudentGroupHaveSameNextSubject(courseid, subjdetailsid, stugroid);
        List<StudentGroupDTO> list = new ArrayList<>();

        for (Integer studentGroupId : studentGroupIdList){

            java.sql.Date NextSubjectStartDateInNewClass      = (java.sql.Date) studentClassrepo.findSubjectStartDay(studentGroupId, subjdetailsid +1);
            java.sql.Date CurrentSubjectEndDateInCurrentClass = (java.sql.Date) studentClassrepo.findSubjectEndDay(stugroid, subjdetailsid);

            if(NextSubjectStartDateInNewClass.after(CurrentSubjectEndDateInCurrentClass)){

                StudentGroup sg = new StudentGroup();
                StudentGroupDTO sgdto = new StudentGroupDTO();
                SubjectDetails subjde = new SubjectDetails();

                subjde = subjdetailrepo.findById(subjdetailsid +1).get();
                sg = sgrouprepo.getStudentGroupByID(studentGroupId);

                sgdto.setStugroid(sg.getStugroid());
                sgdto.setCourid(sg.getCourid().getCourid());
                sgdto.setCournm(sg.getCourid().getCournm());
                sgdto.setStugronm(sg.getStugronm());
                sgdto.setNextSubjid(subjdetailsid +1);
                sgdto.setNextSubjnm(subjde.getSubjid().getSubjnm());

                list.add(sgdto);
            }

        }
        return list;
    }

    @Override
    public List<StudentClass> findAllStudentByStudentGroupId(int StudentGroupId) {
        StudentGroup sg = new StudentGroup();
        sg.setStugroid(StudentGroupId);
        return studentClassrepo.findStudentByStudentGroupId(sg);
    }

    @Override
    public List<StudentClass> findAllStudentByStudentGroupIdForIndex(int StudentGroupId) {
        StudentGroup sg = new StudentGroup();
        sg.setStugroid(StudentGroupId);
        return studentClassrepo.findAllStudentByStudentGroupId(sg);
    }

    @Override
    public StudentDTO findStudentById(int studentid) {
        Student st = new Student();
        StudentDTO sdto = new StudentDTO();
        st = studentrepo.findById(studentid).get();

        sdto.setStuid(studentid);
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

        return sdto;
    }

    @Override
    public StudentClass findCurrentClassByStudentIdAndStugroid(int stuid, int stugroid) {
        return studentClassrepo.findCurrentClassByStudentIdAndStugroid(stuid, stugroid);
    }

    @Override
    public Date findSubjectStartDay(int stugroid, int subjectid) {
        return studentClassrepo.findSubjectStartDay(stugroid, subjectid);
    }

    @Override
    public List<Integer> getCompleteSubject(int stuid) {
        if(studentClassrepo.checkIfStudentCompleteSubject( stuid) != null){
            return studentClassrepo.checkIfStudentCompleteSubject(stuid);
        }else{
            return null;
        }
    }

    @Override
    public Student findStudentByRollnum(String rollnum) {
        Student student = studentrepo.findStudentByRollnum(rollnum);
        Student stu = new Student();
        if(student!=null){
            stu.setStuid(student.getStuid());
            stu.setFullnm(student.getFullnm());
            stu.setRollnum(student.getRollnum());
            stu.setEmail(student.getEmail());
            stu.setCollegeemail(student.getCollegeemail());
            stu.setDob(student.getDob());
            stu.setGender(student.getGender());
            stu.setAddress(student.getAddress());
            stu.setMobphone(student.getMobphone());
            stu.setIdcard(student.getIdcard());
            stu.setDoi(student.getDoi());
            stu.setPoi(student.getPoi());
            stu.setStustatus(student.getStustatus());
            stu.setMajor(student.getMajor());
        }else{
            stu.setStuid(0);
        }
        return stu;
    }

    @Override
    public List<Integer> getCurrentLearningSubjectDetailIdByStudentId(int stuid) {
        String date = LocalDate.now().toString();

        if(studentClassrepo.getCurrentLearningSubjectDetailIdByStudentId(date, stuid)!=null) {
            return studentClassrepo.getCurrentLearningSubjectDetailIdByStudentId(date, stuid);
        }else{
            return null;
        }
    }

    @Override
    public List<StudentGroupDTO> findAvailableStudentGroupsForReLearning(int subjdetailid) {
        List<Integer> studentGroupIdList = studentClassrepo.findStudentGroupHaveAvailableNextSubject(subjdetailid);
        List<StudentGroupDTO> list = new ArrayList<>();

        for (Integer studentGroupId : studentGroupIdList){
            if(this.checkSubjectStart(studentGroupId, subjdetailid)){

                String newSubjStartDate = this.findSubjectStartDay(studentGroupId, subjdetailid).toString();
                StudentGroup sg = new StudentGroup();
                StudentGroupDTO sgdto = new StudentGroupDTO();
                SubjectDetails subjde = new SubjectDetails();

                subjde = subjdetailrepo.findById(subjdetailid).get();
                sg = sgrouprepo.getStudentGroupByID(studentGroupId);

                sgdto.setStugroid(sg.getStugroid());
                sgdto.setCourid(sg.getCourid().getCourid());
                sgdto.setCournm(sg.getCourid().getCournm());
                sgdto.setStugronm(""+sg.getStugronm()+"_("+newSubjStartDate+")");
                sgdto.setNextSubjid(subjdetailid);
                sgdto.setNextSubjnm(subjde.getSubjid().getSubjnm());

                list.add(sgdto);
            }

        }
        if(list.size()>0){
            return list;
        }else{
            return null;
        }

    }

    @Override
    public List<SubjectDetailsDTO> getSubjectNameAndSubjectDetailId(List<Integer> list) {
        List<SubjectDetailsDTO> sdlist = new ArrayList<>();
        for(Integer id : list){
            SubjectDetails subjdetail = subjdetailrepo.findById(id).get();
            SubjectDetailsDTO sdto = new SubjectDetailsDTO();

            sdto.setSubjdetailsid(subjdetail.getSubjdetailsid());
            Subject s=new Subject();
            s=subjrepo.findById(subjdetail.getSubjid().getSubjid()).get();
            sdto.setSubjnm(s.getSubjnm()+"_("+s.getShortnm()+")");

            sdlist.add(sdto);
        }
        return sdlist;
    }

    @Override
    public List<StudentGroup> getAllStudentGroupsByCourse(int courseid) {
        Courses courses = new Courses();
        courses.setCourid(courseid);
        List<StudentGroup> list = new ArrayList<>();
        for (StudentGroup sg : studentClassrepo.findAllStudentGroupByCourse(courses)){
            StudentGroup stg = new StudentGroup();
            stg.setStugroid(sg.getStugroid());
            stg.setStugronm(sg.getStugronm());

            list.add(stg);
        }
        return list;
    }

    @Override
    public List<RenderStudentgroup> getAllStudentGroupsForIndex(int courseid) {
        return sgrouprepo.getAllStudentGroupByCourses(courseid);
    }
}
