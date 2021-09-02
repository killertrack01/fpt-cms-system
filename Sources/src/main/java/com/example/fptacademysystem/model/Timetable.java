/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "timetable", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timetable.findAll", query = "SELECT t FROM Timetable t"),
    @NamedQuery(name = "Timetable.findByTimetableid", query = "SELECT t FROM Timetable t WHERE t.timetableid = :timetableid"),
    @NamedQuery(name = "Timetable.findBySubjnm", query = "SELECT t FROM Timetable t WHERE t.subjnm = :subjnm"),
    @NamedQuery(name = "Timetable.findBySubjdate", query = "SELECT t FROM Timetable t WHERE t.subjdate = :subjdate"),
    @NamedQuery(name = "Timetable.findBySlotofsubjdate", query = "SELECT t FROM Timetable t WHERE t.slotofsubjdate = :slotofsubjdate"),
    @NamedQuery(name = "Timetable.findByAttenteaid", query = "SELECT t FROM Timetable t WHERE t.attenteaid = :attenteaid"),
    @NamedQuery(name = "Timetable.findBySemid", query = "SELECT t FROM Timetable t WHERE t.semid = :semid"),
    @NamedQuery(name = "Timetable.findByAttenedit", query = "SELECT t FROM Timetable t WHERE t.attenedit = :attenedit"),
    @NamedQuery(name = "Timetable.findByNote", query = "SELECT t FROM Timetable t WHERE t.note = :note"),
    @NamedQuery(name = "Timetable.findByCreateat", query = "SELECT t FROM Timetable t WHERE t.createat = :createat"),
    @NamedQuery(name = "Timetable.findByUpdateat", query = "SELECT t FROM Timetable t WHERE t.updateat = :updateat"),
    @NamedQuery(name = "Timetable.findByRemoveat", query = "SELECT t FROM Timetable t WHERE t.removeat = :removeat")})
public class Timetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "timetableid", nullable = false)
    private Integer timetableid;
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
    @Size(min = 1, max = 1000)
    @Column(name = "note", nullable = false, length = 1000)
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
    @JoinColumn(name = "roomid", referencedColumnName = "roomid", nullable = false)
    @ManyToOne(optional = false)
    private Room roomid;
    @JoinColumn(name = "stugroid", referencedColumnName = "stugroid", nullable = false)
    @ManyToOne(optional = false)
    private StudentGroup stugroid;
    @JoinColumn(name = "subjdetailsid", referencedColumnName = "subjdetailsid", nullable = false)
    @ManyToOne(optional = false)
    private SubjectDetails subjdetailsid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timetableid")
    private Collection<Attendance> attendanceCollection;

    public Timetable() {
    }

    public Timetable(Integer timetableid) {
        this.timetableid = timetableid;
    }

    public Timetable(Integer timetableid, String subjnm, Date subjdate, String slotofsubjdate, int attenteaid, int semid, boolean attenedit, String note, String removeat) {
        this.timetableid = timetableid;
        this.subjnm = subjnm;
        this.subjdate = subjdate;
        this.slotofsubjdate = slotofsubjdate;
        this.attenteaid = attenteaid;
        this.semid = semid;
        this.attenedit = attenedit;
        this.note = note;
        this.removeat = removeat;
    }

    public Integer getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(Integer timetableid) {
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

    public Room getRoomid() {
        return roomid;
    }

    public void setRoomid(Room roomid) {
        this.roomid = roomid;
    }

    public StudentGroup getStugroid() {
        return stugroid;
    }

    public void setStugroid(StudentGroup stugroid) {
        this.stugroid = stugroid;
    }

    public SubjectDetails getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(SubjectDetails subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
    }

    @XmlTransient
    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timetableid != null ? timetableid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timetable)) {
            return false;
        }
        Timetable other = (Timetable) object;
        if ((this.timetableid == null && other.timetableid != null) || (this.timetableid != null && !this.timetableid.equals(other.timetableid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Timetable[ timetableid=" + timetableid + " ]";
    }
    
}
