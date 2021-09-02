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
import javax.persistence.UniqueConstraint;
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
@Table(name = "student_group", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"stugronm"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentGroup.findAll", query = "SELECT s FROM StudentGroup s"),
    @NamedQuery(name = "StudentGroup.findByStugroid", query = "SELECT s FROM StudentGroup s WHERE s.stugroid = :stugroid"),
    @NamedQuery(name = "StudentGroup.findByStugronm", query = "SELECT s FROM StudentGroup s WHERE s.stugronm = :stugronm"),
    @NamedQuery(name = "StudentGroup.findByOpeningdate", query = "SELECT s FROM StudentGroup s WHERE s.openingdate = :openingdate"),
    @NamedQuery(name = "StudentGroup.findBySession", query = "SELECT s FROM StudentGroup s WHERE s.session = :session"),
    @NamedQuery(name = "StudentGroup.findByShift", query = "SELECT s FROM StudentGroup s WHERE s.shift = :shift"),
    @NamedQuery(name = "StudentGroup.findByCreateat", query = "SELECT s FROM StudentGroup s WHERE s.createat = :createat"),
    @NamedQuery(name = "StudentGroup.findByUpdateat", query = "SELECT s FROM StudentGroup s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "StudentGroup.findByRemoveat", query = "SELECT s FROM StudentGroup s WHERE s.removeat = :removeat")})
public class StudentGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stugroid", nullable = false)
    private Integer stugroid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "openingdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date openingdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "session", nullable = false, length = 10)
    private String session;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shift", nullable = false)
    private int shift;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stugroid")
    private Collection<StudentClass> studentClassCollection;
    @JoinColumn(name = "courid", referencedColumnName = "courid", nullable = false)
    @ManyToOne(optional = false)
    private Courses courid;
    @JoinColumn(name = "branchcamid", referencedColumnName = "branchcamid", nullable = false)
    @ManyToOne(optional = false)
    private BranchCampus branchcamid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stugroid")
    private Collection<Timetable> timetableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stugroid")
    private Collection<Exam> examCollection;

    public StudentGroup() {
    }

    public StudentGroup(Integer stugroid) {
        this.stugroid = stugroid;
    }

    public StudentGroup(Integer stugroid, String stugronm, Date openingdate, String session, int shift, String removeat) {
        this.stugroid = stugroid;
        this.stugronm = stugronm;
        this.openingdate = openingdate;
        this.session = session;
        this.shift = shift;
        this.removeat = removeat;
    }

    public Integer getStugroid() {
        return stugroid;
    }

    public void setStugroid(Integer stugroid) {
        this.stugroid = stugroid;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public Date getOpeningdate() {
        return openingdate;
    }

    public void setOpeningdate(Date openingdate) {
        this.openingdate = openingdate;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
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

    @XmlTransient
    public Collection<StudentClass> getStudentClassCollection() {
        return studentClassCollection;
    }

    public void setStudentClassCollection(Collection<StudentClass> studentClassCollection) {
        this.studentClassCollection = studentClassCollection;
    }

    public Courses getCourid() {
        return courid;
    }

    public void setCourid(Courses courid) {
        this.courid = courid;
    }

    public BranchCampus getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(BranchCampus branchcamid) {
        this.branchcamid = branchcamid;
    }

    @XmlTransient
    public Collection<Timetable> getTimetableCollection() {
        return timetableCollection;
    }

    public void setTimetableCollection(Collection<Timetable> timetableCollection) {
        this.timetableCollection = timetableCollection;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stugroid != null ? stugroid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentGroup)) {
            return false;
        }
        StudentGroup other = (StudentGroup) object;
        if ((this.stugroid == null && other.stugroid != null) || (this.stugroid != null && !this.stugroid.equals(other.stugroid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.StudentGroup[ stugroid=" + stugroid + " ]";
    }
    
}
