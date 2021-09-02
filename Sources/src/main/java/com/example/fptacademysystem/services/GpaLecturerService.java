/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.GpaLecturerDTO;
import com.example.fptacademysystem.model.GpaLecturer;
import com.example.fptacademysystem.model.SubjectDetails;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.RenderGpalecturer;
import com.example.fptacademysystem.repository.GpaLecturerRepository;
import com.example.fptacademysystem.repository.LecturerRepository;
import com.example.fptacademysystem.repository.StudentGroupRepository;
import com.example.fptacademysystem.repository.SubjectDetailRepository;
import com.example.fptacademysystem.repository.SubjectRepository;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class GpaLecturerService implements IGpaLecturer {

    @Autowired
    GpaLecturerRepository repo;

    @Autowired
    StudentGroupRepository sgrouprepo;

    @Autowired
    SubjectDetailRepository sdetailrepo;

    @Autowired
    SubjectRepository subrepo;

    @Autowired
    LecturerRepository lecturerRepo;

    @Override
    public void create(GpaLecturer gpaLecturer) {
        repo.save(gpaLecturer);
    }

    @Override
    public List<GpaLecturer> findAll() {
        return repo.findAll();
    }

    @Override
    public List<GpaLecturerDTO> findGpaForOneLecturer(GpaLecturerDTO dto) {
        int month = Integer.parseInt(dto.getGpamonth());
        int year = Integer.parseInt(dto.getGpayear());
        int lecturid = Integer.parseInt(dto.getLecturid());
        List<GpaLecturer> listGpaLecturers = repo.findGpaForOneLectruer(month, year, lecturid);
        if (!listGpaLecturers.isEmpty())
        {
            List<GpaLecturerDTO> list = new ArrayList<>();
            for (GpaLecturer gpalecturer : listGpaLecturers)
            {
                StudentGroup studentGroup = sgrouprepo.findById(gpalecturer.getGpastudentgroup()).get();
                SubjectDetails subjectDetails = sdetailrepo.findById(gpalecturer.getGpasubject()).get();
                Subject subject = subjectDetails.getSubjid();
                Subject objectSubject = subrepo.findById(subject.getSubjid()).get();
                Lecturer lecturer = lecturerRepo.findLecturer(gpalecturer.getLecturid().getLecturid());

                Date date = gpalecturer.getGpamonth();
                //create Object and add to list
                GpaLecturerDTO gpadto = new GpaLecturerDTO();
                gpadto.setGpalecid(gpalecturer.getGpalecid().toString());
                gpadto.setGpamonth(String.valueOf(date.getMonth() + 1));
                gpadto.setGpasubject(String.valueOf(gpalecturer.getGpasubject()));
                gpadto.setGpastudentgroup(String.valueOf(gpalecturer.getGpastudentgroup()));
                gpadto.setGpascore(String.valueOf(gpalecturer.getGpascore()));
                gpadto.setLecturid(gpalecturer.getLecturid().getLecturid().toString());
                gpadto.setSubjectnm(objectSubject.getSubjnm());
                gpadto.setStudentgroupnm(studentGroup.getStugronm());
                gpadto.setLecturernm(lecturer.getFullnm());
                gpadto.setGpadate(gpalecturer.getGpamonth().toString());

                list.add(gpadto);
            }
            return list;
        } else
        {
            return Collections.emptyList();
        }
    }

    @Override
    public List<RenderGpalecturer> findGpaYearForOneLecturer(int gpayear, int lecturid) {
        List<RenderGpalecturer> list = repo.findGpaYearForOneLectruer(gpayear, lecturid, Sort.by("gpamonth").ascending());
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        return list;
    }


    @Override
    public List<RenderGpalecturer> findAllGpaYearLecturer(int gpayear) {
        List<RenderGpalecturer> list = repo.findAllGpaYearLecturer(gpayear, Sort.by("gpamonth").ascending());
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        return list;
    }

}
