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
@Table(name = "render_timetable", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenderTimetable.findAll", query = "SELECT r FROM RenderTimetable r"),
    @NamedQuery(name = "RenderTimetable.findByTimetableid", query = "SELECT r FROM RenderTimetable r WHERE r.timetableid = :timetableid"),
    @NamedQuery(name = "RenderTimetable.findBySubjnm", query = "SELECT r FROM RenderTimetable r WHERE r.subjnm = :subjnm"),
    @NamedQuery(name = "RenderTimetable.findBySubjdate", query = "SELECT r FROM RenderTimetable r WHERE r.subjdate = :subjdate"),
    @NamedQuery(name = "RenderTimetable.findBySubjdateofmonth", query = "SELECT r FROM RenderTimetable r WHERE r.subjdateofmonth = :subjdateofmonth"),
    @NamedQuery(name = "RenderTimetable.findBySlotofsubjdate", query = "SELECT r FROM RenderTimetable r WHERE r.slotofsubjdate = :slotofsubjdate"),
    @NamedQuery(name = "RenderTimetable.findByAttenteaid", query = "SELECT r FROM RenderTimetable r WHERE r.attenteaid = :attenteaid"),
    @NamedQuery(name = "RenderTimetable.findBySemid", query = "SELECT r FROM RenderTimetable r WHERE r.semid = :semid"),
    @NamedQuery(name = "RenderTimetable.findByAttenedit", query = "SELECT r FROM RenderTimetable r WHERE r.attenedit = :attenedit"),
    @NamedQuery(name = "RenderTimetable.findByRoomid", query = "SELECT r FROM RenderTimetable r WHERE r.roomid = :roomid"),
    @NamedQuery(name = "RenderTimetable.findByNote", query = "SELECT r FROM RenderTimetable r WHERE r.note = :note"),
    @NamedQuery(name = "RenderTimetable.findBySubjdetailsid", query = "SELECT r FROM RenderTimetable r WHERE r.subjdetailsid = :subjdetailsid"),
    @NamedQuery(name = "RenderTimetable.findByStugroid", query = "SELECT r FROM RenderTimetable r WHERE r.stugroid = :stugroid"),
    @NamedQuery(name = "RenderTimetable.findByRemoveat", query = "SELECT r FROM RenderTimetable r WHERE r.removeat = :removeat"),
    @NamedQuery(name = "RenderTimetable.findBySubjectname", query = "SELECT r FROM RenderTimetable r WHERE r.subjectname = :subjectname"),
    @NamedQuery(name = "RenderTimetable.findBySlots", query = "SELECT r FROM RenderTimetable r WHERE r.slots = :slots"),
    @NamedQuery(name = "RenderTimetable.findBySemSD", query = "SELECT r FROM RenderTimetable r WHERE r.semSD = :semSD"),
    @NamedQuery(name = "RenderTimetable.findByCourseSD", query = "SELECT r FROM RenderTimetable r WHERE r.courseSD = :courseSD"),
    @NamedQuery(name = "RenderTimetable.findByTeanm", query = "SELECT r FROM RenderTimetable r WHERE r.teanm = :teanm"),
    @NamedQuery(name = "RenderTimetable.findByStugronm", query = "SELECT r FROM RenderTimetable r WHERE r.stugronm = :stugronm"),
    @NamedQuery(name = "RenderTimetable.findByBranchcamid", query = "SELECT r FROM RenderTimetable r WHERE r.branchcamid = :branchcamid"),
    @NamedQuery(name = "RenderTimetable.findByBranchcamnm", query = "SELECT r FROM RenderTimetable r WHERE r.branchcamnm = :branchcamnm"),
    @NamedQuery(name = "RenderTimetable.findByBranchid", query = "SELECT r FROM RenderTimetable r WHERE r.branchid = :branchid"),
    @NamedQuery(name = "RenderTimetable.findByBranchnm", query = "SELECT r FROM RenderTimetable r WHERE r.branchnm = :branchnm"),
    @NamedQuery(name = "RenderTimetable.findByCampusid", query = "SELECT r FROM RenderTimetable r WHERE r.campusid = :campusid"),
    @NamedQuery(name = "RenderTimetable.findByCampusnm", query = "SELECT r FROM RenderTimetable r WHERE r.campusnm = :campusnm"),
    @NamedQuery(name = "RenderTimetable.findByAddress", query = "SELECT r FROM RenderTimetable r WHERE r.address = :address")})
public class RenderTimetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "timetableid", nullable = false)
    private int timetableid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subjnm", nullable = false, length = 255)
    private String subjnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date subjdate;
    @Size(max = 9)
    @Column(name = "subjdateofmonth", length = 9)
    private String subjdateofmonth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "slotofsubjdate", nullable = false, length = 50)
    private String slotofsubjdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attenteaid", nullable = false)
    private int attenteaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semid", nullable = false)
    private int semid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attenedit", nullable = false)
    private boolean attenedit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roomid", nullable = false)
    private int roomid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "note", nullable = false, length = 1000)
    private String note;
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
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subjectname", nullable = false, length = 255)
    private String subjectname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slots", nullable = false)
    private int slots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semSD", nullable = false)
    private int semSD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseSD", nullable = false)
    private int courseSD;
    @Size(max = 255)
    @Column(name = "teanm", length = 255)
    private String teanm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchcamid", nullable = false)
    private int branchcamid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "branchcamnm", nullable = false, length = 255)
    private String branchcamnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchid", nullable = false)
    private int branchid;
    @Size(max = 255)
    @Column(name = "branchnm", length = 255)
    private String branchnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "campusid", nullable = false)
    private int campusid;
    @Size(max = 255)
    @Column(name = "campusnm", length = 255)
    private String campusnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    public RenderTimetable() {
    }

    public int getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(int timetableid) {
        this.timetableid = timetableid;
    }

    public String getSubjnm() {
        return subjnm;
    }

    public void setSubjnm(String subjnm) {
        this.subjnm = subjnm;
    }

    public Date getSubjdate() {
        return subjdate;
    }

    public void setSubjdate(Date subjdate) {
        this.subjdate = subjdate;
    }

    public String getSubjdateofmonth() {
        return subjdateofmonth;
    }

    public void setSubjdateofmonth(String subjdateofmonth) {
        this.subjdateofmonth = subjdateofmonth;
    }

    public String getSlotofsubjdate() {
        return slotofsubjdate;
    }

    public void setSlotofsubjdate(String slotofsubjdate) {
        this.slotofsubjdate = slotofsubjdate;
    }

    public int getAttenteaid() {
        return attenteaid;
    }

    public void setAttenteaid(int attenteaid) {
        this.attenteaid = attenteaid;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public boolean getAttenedit() {
        return attenedit;
    }

    public void setAttenedit(boolean attenedit) {
        this.attenedit = attenedit;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getSemSD() {
        return semSD;
    }

    public void setSemSD(int semSD) {
        this.semSD = semSD;
    }

    public int getCourseSD() {
        return courseSD;
    }

    public void setCourseSD(int courseSD) {
        this.courseSD = courseSD;
    }

    public String getTeanm() {
        return teanm;
    }

    public void setTeanm(String teanm) {
        this.teanm = teanm;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public int getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(int branchcamid) {
        this.branchcamid = branchcamid;
    }

    public String getBranchcamnm() {
        return branchcamnm;
    }

    public void setBranchcamnm(String branchcamnm) {
        this.branchcamnm = branchcamnm;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getBranchnm() {
        return branchnm;
    }

    public void setBranchnm(String branchnm) {
        this.branchnm = branchnm;
    }

    public int getCampusid() {
        return campusid;
    }

    public void setCampusid(int campusid) {
        this.campusid = campusid;
    }

    public String getCampusnm() {
        return campusnm;
    }

    public void setCampusnm(String campusnm) {
        this.campusnm = campusnm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
