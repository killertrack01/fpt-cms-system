/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.dto;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class GpaLecturerDTO implements Serializable{
    private String gpalecid;
    private String gpamonth;
    private String gpayear;
    private String gpasubject;
    private String gpastudentgroup;
    private String gpascore;
    private String lecturid;
    private String subjectnm;
    private String studentgroupnm;
    private String lecturernm;
    private String gpadate;
    
    public GpaLecturerDTO(){}
    
    public GpaLecturerDTO(String gpamonth, String gpayear, String lecturid){
        this.gpamonth = gpamonth;
        this.gpayear = gpayear;
        this.lecturid = lecturid;
    }

    public String getGpalecid() {
        return gpalecid;
    }

    public void setGpalecid(String gpalecid) {
        this.gpalecid = gpalecid;
    }

    public String getGpamonth() {
        return gpamonth;
    }
    
    public void setGpamonth(String gpamonth) {
        this.gpamonth = gpamonth;
    }

    public String getGpayear() {
        return gpayear;
    }

    public void setGpayear(String gpayear) {
        this.gpayear = gpayear;
    }

    public String getGpasubject() {
        return gpasubject;
    }

    public void setGpasubject(String gpasubject) {
        this.gpasubject = gpasubject;
    }

    public String getGpastudentgroup() {
        return gpastudentgroup;
    }

    public void setGpastudentgroup(String gpastudentgroup) {
        this.gpastudentgroup = gpastudentgroup;
    }

    public String getGpascore() {
        return gpascore;
    }

    public void setGpascore(String gpascore) {
        this.gpascore = gpascore;
    }

    public String getLecturid() {
        return lecturid;
    }

    public void setLecturid(String lecturid) {
        this.lecturid = lecturid;
    }

    public String getSubjectnm() {
        return subjectnm;
    }

    public void setSubjectnm(String subjectnm) {
        this.subjectnm = subjectnm;
    }

    public String getStudentgroupnm() {
        return studentgroupnm;
    }

    public void setStudentgroupnm(String studentgroupnm) {
        this.studentgroupnm = studentgroupnm;
    }

    public String getLecturernm() {
        return lecturernm;
    }

    public void setLecturernm(String lecturernm) {
        this.lecturernm = lecturernm;
    }

    public String getGpadate() {
        return gpadate;
    }

    public void setGpadate(String gpadate) {
        this.gpadate = gpadate;
    } 
    
}
