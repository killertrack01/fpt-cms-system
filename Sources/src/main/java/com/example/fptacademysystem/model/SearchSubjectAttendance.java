/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "search_subject_attendance", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SearchSubjectAttendance.findAll", query = "SELECT s FROM SearchSubjectAttendance s"),
    @NamedQuery(name = "SearchSubjectAttendance.findBySubjids", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.subjids = :subjids"),
    @NamedQuery(name = "SearchSubjectAttendance.findByShortnm", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.shortnm = :shortnm"),
    @NamedQuery(name = "SearchSubjectAttendance.findBySubjnm", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.subjnm = :subjnm"),
    @NamedQuery(name = "SearchSubjectAttendance.findByBranchid", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.branchid = :branchid"),
    @NamedQuery(name = "SearchSubjectAttendance.findByRemoveat", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.removeat = :removeat"),
    @NamedQuery(name = "SearchSubjectAttendance.findBySubjidsd", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.subjidsd = :subjidsd"),
    @NamedQuery(name = "SearchSubjectAttendance.findBySemid", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.semid = :semid"),
    @NamedQuery(name = "SearchSubjectAttendance.findByCourid", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.courid = :courid"),
    @NamedQuery(name = "SearchSubjectAttendance.findByStugroid", query = "SELECT s FROM SearchSubjectAttendance s WHERE s.stugroid = :stugroid")})
public class SearchSubjectAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjids", nullable = false)
    private int subjids;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "shortnm", nullable = false, length = 255)
    private String shortnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subjnm", nullable = false, length = 255)
    private String subjnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchid", nullable = false)
    private int branchid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjidsd", nullable = false)
    private int subjidsd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semid", nullable = false)
    private int semid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courid", nullable = false)
    private int courid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;

    public SearchSubjectAttendance() {
    }

    public int getSubjids() {
        return subjids;
    }

    public void setSubjids(int subjids) {
        this.subjids = subjids;
    }

    public String getShortnm() {
        return shortnm;
    }

    public void setShortnm(String shortnm) {
        this.shortnm = shortnm;
    }

    public String getSubjnm() {
        return subjnm;
    }

    public void setSubjnm(String subjnm) {
        this.subjnm = subjnm;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }

    public int getSubjidsd() {
        return subjidsd;
    }

    public void setSubjidsd(int subjidsd) {
        this.subjidsd = subjidsd;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public int getCourid() {
        return courid;
    }

    public void setCourid(int courid) {
        this.courid = courid;
    }

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }
    
}
