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
public class FeedbackDTO implements Serializable {

    private String dateoffeedback;
    private String enddatefeedback;
    private String feedbackstatus;
    private String namefeedback;
    private String stugroid;
    private String lecturid;
    private String subjdetailsid;
    private String stugroupnm;
    private String lecturernm;
    private String subjectnm;
    private String feedbackid;
    private String courseid;
    private String semesterid;
    private boolean check;
    private String count;

    public FeedbackDTO() {
        this.check = false;
    }

    public String getDateoffeedback() {
        return dateoffeedback;
    }

    public void setDateoffeedback(String dateoffeedback) {
        this.dateoffeedback = dateoffeedback;
    }

    public String getNamefeedback() {
        return namefeedback;
    }

    public void setNamefeedback(String namefeedback) {
        this.namefeedback = namefeedback;
    }

    public String getStugroid() {
        return stugroid;
    }

    public void setStugroid(String stugroid) {
        this.stugroid = stugroid;
    }

    public String getLecturid() {
        return lecturid;
    }

    public void setLecturid(String lecturid) {
        this.lecturid = lecturid;
    }

    public String getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(String subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
    }

    public String getStugroupnm() {
        return stugroupnm;
    }

    public void setStugroupnm(String stugroupnm) {
        this.stugroupnm = stugroupnm;
    }

    public String getLecturernm() {
        return lecturernm;
    }

    public void setLecturernm(String lecturernm) {
        this.lecturernm = lecturernm;
    }

    public String getSubjectnm() {
        return subjectnm;
    }

    public void setSubjectnm(String subjectnm) {
        this.subjectnm = subjectnm;
    }

    public String getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(String feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getSemesterid() {
        return semesterid;
    }

    public void setSemesterid(String semesterid) {
        this.semesterid = semesterid;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getEnddatefeedback() {
        return enddatefeedback;
    }

    public void setEnddatefeedback(String enddatefeedback) {
        this.enddatefeedback = enddatefeedback;
    }

    public String getFeedbackstatus() {
        return feedbackstatus;
    }

    public void setFeedbackstatus(String feedbackstatus) {
        this.feedbackstatus = feedbackstatus;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
