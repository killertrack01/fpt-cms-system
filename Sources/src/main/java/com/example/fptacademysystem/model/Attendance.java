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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "attendance", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a"),
    @NamedQuery(name = "Attendance.findByAttenid", query = "SELECT a FROM Attendance a WHERE a.attenid = :attenid"),
    @NamedQuery(name = "Attendance.findByPresent", query = "SELECT a FROM Attendance a WHERE a.present = :present"),
    @NamedQuery(name = "Attendance.findByNote", query = "SELECT a FROM Attendance a WHERE a.note = :note"),
    @NamedQuery(name = "Attendance.findByCreateat", query = "SELECT a FROM Attendance a WHERE a.createat = :createat"),
    @NamedQuery(name = "Attendance.findByUpdateat", query = "SELECT a FROM Attendance a WHERE a.updateat = :updateat"),
    @NamedQuery(name = "Attendance.findByRemoveat", query = "SELECT a FROM Attendance a WHERE a.removeat = :removeat")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "attenid", nullable = false)
    private Integer attenid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "present", nullable = false)
    private boolean present;
    @Size(max = 255)
    @Column(name = "note", length = 255)
    private String note;
    @Column(name = "createat")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createat;
    @Column(name = "updateat")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @JoinColumn(name = "stuid", referencedColumnName = "stuid", nullable = false)
    @ManyToOne(optional = false)
    private Student stuid;
    @JoinColumn(name = "timetableid", referencedColumnName = "timetableid", nullable = false)
    @ManyToOne(optional = false)
    private Timetable timetableid;

    public Attendance() {
    }

    public Attendance(Integer attenid) {
        this.attenid = attenid;
    }

    public Attendance(Integer attenid, boolean present, String removeat) {
        this.attenid = attenid;
        this.present = present;
        this.removeat = removeat;
    }

    public Integer getAttenid() {
        return attenid;
    }

    public void setAttenid(Integer attenid) {
        this.attenid = attenid;
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

    public Student getStuid() {
        return stuid;
    }

    public void setStuid(Student stuid) {
        this.stuid = stuid;
    }

    public Timetable getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(Timetable timetableid) {
        this.timetableid = timetableid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attenid != null ? attenid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.attenid == null && other.attenid != null) || (this.attenid != null && !this.attenid.equals(other.attenid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Attendance[ attenid=" + attenid + " ]";
    }
    
}
