/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "find_student_in_attendance", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FindStudentInAttendance.findAll", query = "SELECT f FROM FindStudentInAttendance f"),
    @NamedQuery(name = "FindStudentInAttendance.findByCosid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.cosid = :cosid"),
    @NamedQuery(name = "FindStudentInAttendance.findByBranchcamid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.branchcamid = :branchcamid"),
    @NamedQuery(name = "FindStudentInAttendance.findByAttenid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.attenid = :attenid"),
    @NamedQuery(name = "FindStudentInAttendance.findByRollnum", query = "SELECT f FROM FindStudentInAttendance f WHERE f.rollnum = :rollnum"),
    @NamedQuery(name = "FindStudentInAttendance.findByFullnm", query = "SELECT f FROM FindStudentInAttendance f WHERE f.fullnm = :fullnm"),
    @NamedQuery(name = "FindStudentInAttendance.findByPresent", query = "SELECT f FROM FindStudentInAttendance f WHERE f.present = :present"),
    @NamedQuery(name = "FindStudentInAttendance.findByNote", query = "SELECT f FROM FindStudentInAttendance f WHERE f.note = :note"),
    @NamedQuery(name = "FindStudentInAttendance.findByTimetableid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.timetableid = :timetableid"),
    @NamedQuery(name = "FindStudentInAttendance.findByStuid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.stuid = :stuid"),
    @NamedQuery(name = "FindStudentInAttendance.findBySubjdetailsid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.subjdetailsid = :subjdetailsid"),
    @NamedQuery(name = "FindStudentInAttendance.findByStugroid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.stugroid = :stugroid"),
    @NamedQuery(name = "FindStudentInAttendance.findBySemid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.semid = :semid"),
    @NamedQuery(name = "FindStudentInAttendance.findBySubjid", query = "SELECT f FROM FindStudentInAttendance f WHERE f.subjid = :subjid"),
    @NamedQuery(name = "FindStudentInAttendance.findBySubjdate", query = "SELECT f FROM FindStudentInAttendance f WHERE f.subjdate = :subjdate"),
    @NamedQuery(name = "FindStudentInAttendance.findByAttenedit", query = "SELECT f FROM FindStudentInAttendance f WHERE f.attenedit = :attenedit"),
    @NamedQuery(name = "FindStudentInAttendance.findBySession", query = "SELECT f FROM FindStudentInAttendance f WHERE f.session = :session"),
    @NamedQuery(name = "FindStudentInAttendance.findByRemoveat", query = "SELECT f FROM FindStudentInAttendance f WHERE f.removeat = :removeat")})
public class FindStudentInAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cosid", nullable = false)
    private long cosid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchcamid", nullable = false)
    private int branchcamid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attenid", nullable = false)
    private int attenid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rollnum", nullable = false, length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullnm", nullable = false, length = 255)
    private String fullnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "present", nullable = false)
    private boolean present;
    @Size(max = 255)
    @Column(name = "note", length = 255)
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timetableid", nullable = false)
    private int timetableid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stuid", nullable = false)
    private int stuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdetailsid", nullable = false)
    private int subjdetailsid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semid", nullable = false)
    private int semid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjid", nullable = false)
    private int subjid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date subjdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attenedit", nullable = false)
    private boolean attenedit;
    @Size(max = 2)
    @Column(name = "session", length = 2)
    private String session;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;

    public FindStudentInAttendance() {
    }

    public long getCosid() {
        return cosid;
    }

    public void setCosid(long cosid) {
        this.cosid = cosid;
    }

    public int getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(int branchcamid) {
        this.branchcamid = branchcamid;
    }

    public int getAttenid() {
        return attenid;
    }

    public void setAttenid(int attenid) {
        this.attenid = attenid;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getFullnm() {
        return fullnm;
    }

    public void setFullnm(String fullnm) {
        this.fullnm = fullnm;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(int timetableid) {
        this.timetableid = timetableid;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public int getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(int subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
    }

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public int getSubjid() {
        return subjid;
    }

    public void setSubjid(int subjid) {
        this.subjid = subjid;
    }

    public Date getSubjdate() {
        return subjdate;
    }

    public void setSubjdate(Date subjdate) {
        this.subjdate = subjdate;
    }

    public boolean getAttenedit() {
        return attenedit;
    }

    public void setAttenedit(boolean attenedit) {
        this.attenedit = attenedit;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }
    
}
