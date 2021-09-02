package com.example.fptacademysystem.services;

import java.util.List;


import com.example.fptacademysystem.model.SubjectSubjectdetails;

public interface ISubjectSubjectDetails {
    List<SubjectSubjectdetails> findSSD(int coursesID,int semID);
}
