package com.example.fptacademysystem.services;import com.example.fptacademysystem.dto.GetDataToCreateAttendanceDTO;import com.example.fptacademysystem.model.*;import com.example.fptacademysystem.repository.AttendanceRepository;import com.example.fptacademysystem.repository.TimetableRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.ArrayList;import java.util.Date;import java.util.List;@Servicepublic class AttendanceService implements IAttendance{    @Autowired    AttendanceRepository dao;    @Autowired    TimetableRepository timetableRepository;    @Override    public List<BranchCampus> getAllBranchCampus() {        // TODO Auto-generated method stub        return dao.getAllBranchCampus();    }    @Override    public List<StudentGroup> getStudentGroupByBranchCampusId(BranchCampus branchcampusID) {        // TODO Auto-generated method stub        return dao.getStudentGroupByBranchCampusId(branchcampusID);    }    @Override    public List<Integer> getSemsterByStudentGroupId(int stugroID) {        // TODO Auto-generated method stub        return dao.getSemsterByStudentGroupId(stugroID);    }    @Override    public List<SearchSubjectAttendance> getSubjectBySemsterIdAndStudentGroupId(int stugroID, int semID) {        // TODO Auto-generated method stub        return dao.getSubjectBySemsterIdAndStudentGroupId(stugroID, semID);    }    @Override    public List<Timetable> getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(StudentGroup stugroID, int semID,                                                                                  int subjID) {        // TODO Auto-generated method stub        return dao.getSubjectDateBySemsterIdAndStudentGroupIdAndSubjectId(stugroID, semID, subjID);    }    // -------------------------------------------------------------------- Commonly Used Function For Creating And Editing    public List<GetDataToCreateAttendanceDTO> commonlyUsedFuncForCreateAndUpdate(int stugroID, int semID,int subjectID) {        GetDataToCreateAttendanceDTO student = null;        boolean checkExist = false;        List<GetDataToCreateAttendanceDTO> listStudent = new ArrayList<GetDataToCreateAttendanceDTO>();        List<StudentCurrentClass> getCC = dao.getStudentCurrentClass(stugroID, subjectID);        List<StudentLearnAgain> getLA = dao.getStudentLearnAgain(stugroID, semID, subjectID);        if (getCC.size() > 0) {            for (StudentCurrentClass getRowCC : getCC) {                student = new GetDataToCreateAttendanceDTO();                student.setRollnum(getRowCC.getRollnum());                student.setStunm(getRowCC.getFullnm());                student.setStudentstatus(0);                listStudent.add(student);            }        }        if (getLA.size() > 0) {            for (StudentLearnAgain getRowLA : getLA) {                for (int i = 0; i < listStudent.size(); i++) {                    if (getRowLA.getRollnum() == listStudent.get(i).getRollnum()) {                        checkExist = true;                    }                }                if (checkExist == false) {                    student = new GetDataToCreateAttendanceDTO();                    student.setRollnum(getRowLA.getRollnum());                    student.setStunm(getRowLA.getFullnm());                    student.setStudentstatus(1);                    listStudent.add(student);                }            }        }        return listStudent;    }    // -------------------------------------------------------------------- Create Attendance    @Override    public List<GetDataToCreateAttendanceDTO> getStudentInStudentGroupToCreate(int stugroID, int semID, int subjectID) {        // TODO Auto-generated method stub        return commonlyUsedFuncForCreateAndUpdate(stugroID, semID, subjectID);    }    @Override    public void createAttendance(Attendance att) {        // TODO Auto-generated method stub        dao.save(att);    }    @Override    public List<Timetable> findTimetable(StudentGroup stugroID, int semID, SubjectDetails subjdetailsID,                                         Date subjDate) {        // TODO Auto-generated method stub        return dao.findTimetable(stugroID, semID, subjdetailsID, subjDate);    }    @Override    public int findStudent(String rollNum) {        // TODO Auto-generated method stub        return dao.findStudent(rollNum);    }    // -------------------------------------------------------------------- Update Attendance    @Override    public int checkExist(int stugroID, int semID, int subjID, Date subjDate, String sessionVal) {        // TODO Auto-generated method stub        return dao.checkExist(stugroID, semID, subjID, subjDate, sessionVal);    }    @Override    public List<GetDataToCreateAttendanceDTO> getStudentInStudentGroupToUpdate(int stugroID, int semID, int subjID,                                                                               Date subjDate, String sessionVal) {        // TODO Auto-generated method stub        List<GetDataToCreateAttendanceDTO> mergeListStudent = new ArrayList<GetDataToCreateAttendanceDTO>();        GetDataToCreateAttendanceDTO item = null;        List<GetDataToCreateAttendanceDTO> listStudentInClass = commonlyUsedFuncForCreateAndUpdate(stugroID, semID, subjID);        List<FindStudentInAttendance> listStudentUpdate = dao.findStundentToUpdate(stugroID, semID, subjID, subjDate, sessionVal);        for (int i = 0; i < listStudentInClass.size(); i++) {            for (int j = 0; j < listStudentUpdate.size(); j++) {                if (listStudentInClass.get(i).getRollnum().equals(listStudentUpdate.get(j).getRollnum())) {                    item = new GetDataToCreateAttendanceDTO();                    //Get Student From List Student Class                    item.setStunm(listStudentInClass.get(i).getStunm());                    item.setRollnum(listStudentInClass.get(i).getRollnum());                    item.setStudentstatus(listStudentInClass.get(i).getStudentstatus());                    //Get Student From List Student Update                    item.setBranchcamid(listStudentUpdate.get(j).getBranchcamid());                    item.setNote(listStudentUpdate.get(j).getNote());                    item.setPresent(listStudentUpdate.get(j).getPresent());                    item.setSemid(listStudentUpdate.get(j).getSemid());                    item.setStudentgroupid(listStudentUpdate.get(j).getStugroid());                    item.setSubjdate(listStudentUpdate.get(j).getSubjdate());                    item.setSubjectdetailsid(listStudentUpdate.get(j).getSubjdetailsid());                    item.setSubjectid(listStudentUpdate.get(j).getSubjid());                    item.setSession(listStudentUpdate.get(j).getSession());                    item.setAttenid(listStudentUpdate.get(j).getAttenid());                    item.setAttenedit(listStudentUpdate.get(j).getAttenedit());                    mergeListStudent.add(item);                }            }        }        return mergeListStudent;    }    @Override    public void updateAttendance(Boolean present, String note, int attenID) {        // TODO Auto-generated method stub        dao.updateAttendance(present, note, attenID);    }    @Override    public List<ReportAttendance> getAttendanceReport(int stugroid, int semid, int subjid) {        return dao.getAttendanceReport(stugroid, semid, subjid);    }    // Attendance Report//    @Override//    public List<RenderAttendanceReport> findAllAttendanceReport() {//        return dao.getAttendanceReport();//    }}