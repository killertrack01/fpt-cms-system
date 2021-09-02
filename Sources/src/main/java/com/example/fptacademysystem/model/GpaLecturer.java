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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "gpa_lecturer", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GpaLecturer.findAll", query = "SELECT g FROM GpaLecturer g"),
    @NamedQuery(name = "GpaLecturer.findByGpalecid", query = "SELECT g FROM GpaLecturer g WHERE g.gpalecid = :gpalecid"),
    @NamedQuery(name = "GpaLecturer.findByGpamonth", query = "SELECT g FROM GpaLecturer g WHERE g.gpamonth = :gpamonth"),
    @NamedQuery(name = "GpaLecturer.findByGpasubject", query = "SELECT g FROM GpaLecturer g WHERE g.gpasubject = :gpasubject"),
    @NamedQuery(name = "GpaLecturer.findByGpastudentgroup", query = "SELECT g FROM GpaLecturer g WHERE g.gpastudentgroup = :gpastudentgroup"),
    @NamedQuery(name = "GpaLecturer.findByGpascore", query = "SELECT g FROM GpaLecturer g WHERE g.gpascore = :gpascore"),
    @NamedQuery(name = "GpaLecturer.findByCreateat", query = "SELECT g FROM GpaLecturer g WHERE g.createat = :createat"),
    @NamedQuery(name = "GpaLecturer.findByUpdateat", query = "SELECT g FROM GpaLecturer g WHERE g.updateat = :updateat"),
    @NamedQuery(name = "GpaLecturer.findByRemoveat", query = "SELECT g FROM GpaLecturer g WHERE g.removeat = :removeat")})
public class GpaLecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gpalecid", nullable = false)
    private Integer gpalecid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpamonth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date gpamonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpasubject", nullable = false)
    private int gpasubject;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpastudentgroup", nullable = false)
    private int gpastudentgroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpascore", nullable = false)
    private double gpascore;
    @Column(name = "createat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createat;
    @Column(name = "updateat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @JoinColumn(name = "lecturid", referencedColumnName = "lecturid", nullable = false)
    @ManyToOne(optional = false)
    private Lecturer lecturid;

    public GpaLecturer() {
    }

    public GpaLecturer(Integer gpalecid) {
        this.gpalecid = gpalecid;
    }

    public GpaLecturer(Integer gpalecid, Date gpamonth, int gpasubject, int gpastudentgroup, double gpascore, String removeat) {
        this.gpalecid = gpalecid;
        this.gpamonth = gpamonth;
        this.gpasubject = gpasubject;
        this.gpastudentgroup = gpastudentgroup;
        this.gpascore = gpascore;
        this.removeat = removeat;
    }

    public Integer getGpalecid() {
        return gpalecid;
    }

    public void setGpalecid(Integer gpalecid) {
        this.gpalecid = gpalecid;
    }

    public Date getGpamonth() {
        return gpamonth;
    }

    public void setGpamonth(Date gpamonth) {
        this.gpamonth = gpamonth;
    }

    public int getGpasubject() {
        return gpasubject;
    }

    public void setGpasubject(int gpasubject) {
        this.gpasubject = gpasubject;
    }

    public int getGpastudentgroup() {
        return gpastudentgroup;
    }

    public void setGpastudentgroup(int gpastudentgroup) {
        this.gpastudentgroup = gpastudentgroup;
    }

    public double getGpascore() {
        return gpascore;
    }

    public void setGpascore(double gpascore) {
        this.gpascore = gpascore;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }

    public Lecturer getLecturid() {
        return lecturid;
    }

    public void setLecturid(Lecturer lecturid) {
        this.lecturid = lecturid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gpalecid != null ? gpalecid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GpaLecturer)) {
            return false;
        }
        GpaLecturer other = (GpaLecturer) object;
        if ((this.gpalecid == null && other.gpalecid != null) || (this.gpalecid != null && !this.gpalecid.equals(other.gpalecid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.GpaLecturer[ gpalecid=" + gpalecid + " ]";
    }
    
}
