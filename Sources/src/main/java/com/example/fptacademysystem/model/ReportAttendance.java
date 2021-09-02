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
@Table(name = "report_attendance", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportAttendance.findAll", query = "SELECT r FROM ReportAttendance r"),
    @NamedQuery(name = "ReportAttendance.findByRaid", query = "SELECT r FROM ReportAttendance r WHERE r.raid = :raid"),
    @NamedQuery(name = "ReportAttendance.findByAttenid", query = "SELECT r FROM ReportAttendance r WHERE r.attenid = :attenid"),
    @NamedQuery(name = "ReportAttendance.findByPresent", query = "SELECT r FROM ReportAttendance r WHERE r.present = :present"),
    @NamedQuery(name = "ReportAttendance.findByTimetableid", query = "SELECT r FROM ReportAttendance r WHERE r.timetableid = :timetableid"),
    @NamedQuery(name = "ReportAttendance.findByStuid", query = "SELECT r FROM ReportAttendance r WHERE r.stuid = :stuid"),
    @NamedQuery(name = "ReportAttendance.findByRemoveat", query = "SELECT r FROM ReportAttendance r WHERE r.removeat = :removeat"),
    @NamedQuery(name = "ReportAttendance.findByFullnm", query = "SELECT r FROM ReportAttendance r WHERE r.fullnm = :fullnm"),
    @NamedQuery(name = "ReportAttendance.findBySubjdate", query = "SELECT r FROM ReportAttendance r WHERE r.subjdate = :subjdate"),
    @NamedQuery(name = "ReportAttendance.findByShortnm", query = "SELECT r FROM ReportAttendance r WHERE r.shortnm = :shortnm"),
    @NamedQuery(name = "ReportAttendance.findBySlotofsubjdate", query = "SELECT r FROM ReportAttendance r WHERE r.slotofsubjdate = :slotofsubjdate"),
    @NamedQuery(name = "ReportAttendance.findByStugroid", query = "SELECT r FROM ReportAttendance r WHERE r.stugroid = :stugroid"),
    @NamedQuery(name = "ReportAttendance.findBySemid", query = "SELECT r FROM ReportAttendance r WHERE r.semid = :semid"),
    @NamedQuery(name = "ReportAttendance.findBySubjid", query = "SELECT r FROM ReportAttendance r WHERE r.subjid = :subjid"),
    @NamedQuery(name = "ReportAttendance.findByRollnum", query = "SELECT r FROM ReportAttendance r WHERE r.rollnum = :rollnum"),
    @NamedQuery(name = "ReportAttendance.findByAttenteaid", query = "SELECT r FROM ReportAttendance r WHERE r.attenteaid = :attenteaid")})
public class ReportAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "raid", nullable = false)
    private long raid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attenid", nullable = false)
    private int attenid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "present", nullable = false)
    private boolean present;
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
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullnm", nullable = false, length = 255)
    private String fullnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date subjdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "shortnm", nullable = false, length = 255)
    private String shortnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "slotofsubjdate", nullable = false, length = 50)
    private String slotofsubjdate;
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
    @Size(min = 1, max = 255)
    @Column(name = "rollnum", nullable = false, length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attenteaid", nullable = false)
    private int attenteaid;

    public ReportAttendance() {
    }

    public long getRaid() {
        return raid;
    }

    public void setRaid(long raid) {
        this.raid = raid;
    }

    public int getAttenid() {
        return attenid;
    }

    public void setAttenid(int attenid) {
        this.attenid = attenid;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
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

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }

    public String getFullnm() {
        return fullnm;
    }

    public void setFullnm(String fullnm) {
        this.fullnm = fullnm;
    }

    public Date getSubjdate() {
        return subjdate;
    }

    public void setSubjdate(Date subjdate) {
        this.subjdate = subjdate;
    }

    public String getShortnm() {
        return shortnm;
    }

    public void setShortnm(String shortnm) {
        this.shortnm = shortnm;
    }

    public String getSlotofsubjdate() {
        return slotofsubjdate;
    }

    public void setSlotofsubjdate(String slotofsubjdate) {
        this.slotofsubjdate = slotofsubjdate;
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

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public int getAttenteaid() {
        return attenteaid;
    }

    public void setAttenteaid(int attenteaid) {
        this.attenteaid = attenteaid;
    }
    
}
