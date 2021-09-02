/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package com.example.fptacademysystem.services;import com.example.fptacademysystem.dto.CourseDTO;import com.example.fptacademysystem.dto.ImportSubjectDTO;import com.example.fptacademysystem.dto.SubjectDTO;import com.example.fptacademysystem.model.Branch;import com.example.fptacademysystem.model.Courses;import com.example.fptacademysystem.model.Semester;import com.example.fptacademysystem.model.Subject;import com.example.fptacademysystem.model.SubjectDetails;import com.example.fptacademysystem.repository.BranchRepository;import com.example.fptacademysystem.repository.CourseRepository;import com.example.fptacademysystem.repository.SemesterRepository;import com.example.fptacademysystem.repository.SubjectDetailRepository;import com.example.fptacademysystem.repository.SubjectRepository;import java.io.IOException;import java.util.ArrayList;import java.util.HashSet;import java.util.List;import java.util.Set;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.web.multipart.MultipartFile;/** * * @author ADMIN */@Servicepublic class ImportSubjectExcel {    //service    @Autowired    ReadExcelCourse readexcelservice;    @Autowired    SubjectService subjectservice;    @Autowired    CoursesService courseservice;    // repisitory    @Autowired    SubjectDetailRepository subjectDetailRepository;    @Autowired    BranchRepository branchRepository;    @Autowired    SubjectRepository subjectRepository;    @Autowired    SemesterRepository semesterRepository;    @Autowired    CourseRepository courseRepository;    public void importData(MultipartFile filePath) throws IOException {        List<ImportSubjectDTO> list = readexcelservice.showDataExcel(filePath);        List<ImportSubjectDTO> newListRemoveDataNull = new ArrayList<>();        for (ImportSubjectDTO dto : list)        {            if (dto.getBranchname() != null || dto.getCoursename() != null || dto.getSubjectname() != null || dto.getSubjectshortname() != null)            {                newListRemoveDataNull.add(dto);            }        }        //create courses        createCourses(newListRemoveDataNull);        System.out.println("Return successfully!!!");        // craete subject        createSubject(newListRemoveDataNull);    }    private void createCourses(List<ImportSubjectDTO> list) {        List<CourseDTO> listCourse = changeCoursesDTO();        Set<String> setString = new HashSet<>();        for (ImportSubjectDTO dto : list)        {            setString.add(dto.getCoursename());        }        Set<ImportSubjectDTO> listAfterRemove = new HashSet<>();        for (ImportSubjectDTO dto : list)        {            for (String str : setString)            {                if (dto.getCoursename().equals(str))                {                    if (!listAfterRemove.isEmpty())                    {                        for (ImportSubjectDTO remove : listAfterRemove)                        {                            if (!remove.getCoursename().equals(dto.getCoursename()))                            {                                listAfterRemove.add(dto);                            }                        }                    } else                    {                        listAfterRemove.add(dto);                    }                }            }        }        for (CourseDTO course : listCourse)        {            listAfterRemove.removeIf(filter -> filter.getCoursename().equals(course.getCournm()));        }        if (listAfterRemove.isEmpty())        {            return;        } else        {            for (ImportSubjectDTO importSubjectDTO : listAfterRemove)            {                System.out.println(importSubjectDTO);                Courses courses = new Courses();                courses.setCournm(importSubjectDTO.getCoursename());                Branch branch = branchRepository.findBranchByName(importSubjectDTO.getBranchname());                Branch b = new Branch(branch.getBranchid());                courses.setBranchid(b);                courses.setRemoveat("No");                courseservice.create(courses);            }        }    }    private void createSubject(List<ImportSubjectDTO> list) {        List<SubjectDTO> listSubject = changeSubjectDTO();        for (int i = 0; i < listSubject.size(); i++)        {            System.out.println(listSubject.get(i));        }        for (ImportSubjectDTO dto : list)        {            System.out.println(dto);        }        if (!listSubject.isEmpty())        {            for (int i = 0; i < list.size(); i++)            {                boolean checkExist = false;                for (int j = 0; j < listSubject.size(); j++)                {                    if (list.get(i).getSubjectname().length() <= 255 && list.get(i).getSubjectname().equals(listSubject.get(j).getSubjnm()) && list.get(i).getSubjectshortname().equals(listSubject.get(j).getShortnm()))                    {                        checkExist = true;                    }                }                String str = list.get(i).getSubjectname()+ "\t" + list.get(i).getSubjectshortname();                if (checkExist == false)                {                    //create subject                    Subject s = new Subject();                    s.setSubjnm(list.get(i).getSubjectname());                    s.setShortnm(list.get(i).getSubjectshortname());                    Branch branch = branchRepository.findBranchByName(list.get(i).getBranchname());                    s.setBranchid(branch.getBranchid());                    s.setRemoveat("No");                    subjectservice.create(s);                    //create subject details                    SubjectDetails detail = new SubjectDetails();                    detail.setSlots(list.get(i).getSlot());                    detail.setFee(new Double(list.get(i).getFee()));                    Subject sub = new Subject(subjectRepository.findMaxSubjectId());                    detail.setSubjid(sub);                    Semester semester = semesterRepository.findOneSemesterById(list.get(i).getSemid() + 1);                    Semester sem = new Semester(semester.getSemid());                    detail.setSemid(sem);                    Courses courses = courseRepository.findCoursesByName(list.get(i).getCoursename());                    Courses c = new Courses(courses.getCourid());                    detail.setCourid(c);                    detail.setRemoveat("No");                    subjectDetailRepository.save(detail);                }            }        } else        {            for (ImportSubjectDTO dto : list)            {                //create subject                Subject s = new Subject();                s.setSubjnm(dto.getSubjectname());                s.setShortnm(dto.getSubjectshortname());                Branch branch = branchRepository.findBranchByName(dto.getBranchname());                s.setBranchid(branch.getBranchid());                s.setRemoveat("No");                subjectservice.create(s);                //create subject details                SubjectDetails detail = new SubjectDetails();                detail.setSlots(dto.getSlot());                detail.setFee(new Double(dto.getFee()));                Subject sub = new Subject(subjectRepository.findMaxSubjectId());                detail.setSubjid(sub);                Semester semester = semesterRepository.findOneSemesterById(dto.getSemid() + 1);                Semester sem = new Semester(semester.getSemid());                detail.setSemid(sem);                Courses courses = courseRepository.findCoursesByName(dto.getCoursename());                Courses c = new Courses(courses.getCourid());                detail.setCourid(c);                detail.setRemoveat("No");                subjectDetailRepository.save(detail);            }        }    }    private String capitalizeFirstLetter(String letter) {        // create a string        String name = letter;        // get First letter of the string        String firstLetStr = name.substring(0, 1);        // Get remaining letter using substring        String remLetStr = name.substring(1);        // convert the first letter of String to uppercase        firstLetStr = firstLetStr.toUpperCase();        // concantenate the first letter and remaining string        String firstLetterCapitalizedName = firstLetStr + remLetStr;        return firstLetterCapitalizedName;    }    private List<CourseDTO> changeCoursesDTO() {        List<Courses> list = courseservice.Findall();        List<CourseDTO> listDTO = new ArrayList<>();        for (Courses course : list)        {            CourseDTO dto = new CourseDTO();            dto.setCourid(course.getCourid());            dto.setCournm(course.getCournm());            Branch branch = branchRepository.findBranchById(course.getBranchid().getBranchid());            dto.setBranchid(branch.getBranchid());            dto.setBranchnm(branch.getBranchnm());            listDTO.add(dto);        }        return listDTO;    }    private List<SubjectDTO> changeSubjectDTO() {        List<Subject> listSubject = subjectservice.Findall();        List<SubjectDTO> listDTO = new ArrayList<>();        for (Subject subject : listSubject)        {            SubjectDTO dto = new SubjectDTO();            dto.setSubjid(subject.getSubjid());            dto.setSubjnm(subject.getSubjnm());            dto.setShortnm(subject.getShortnm());            dto.setBranchid(subject.getBranchid());            Branch branch = branchRepository.findBranchById(subject.getBranchid());            dto.setBrandnm(branch.getBranchnm());            dto.setRemoveat(subject.getRemoveat());            listDTO.add(dto);        }        return listDTO;    }    public String removeAllWhiteSpaces(String letter) {        String str = letter;        String noSpaceStr = str.replaceAll("\\s", ""); // using built in method        return noSpaceStr;    }}