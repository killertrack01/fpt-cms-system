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
@Table(name = "exam", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByExamid", query = "SELECT e FROM Exam e WHERE e.examid = :examid"),
    @NamedQuery(name = "Exam.findByExamcodelogin", query = "SELECT e FROM Exam e WHERE e.examcodelogin = :examcodelogin"),
    @NamedQuery(name = "Exam.findByExamday", query = "SELECT e FROM Exam e WHERE e.examday = :examday"),
    @NamedQuery(name = "Exam.findByExamtype", query = "SELECT e FROM Exam e WHERE e.examtype = :examtype"),
    @NamedQuery(name = "Exam.findByExamtime", query = "SELECT e FROM Exam e WHERE e.examtime = :examtime"),
    @NamedQuery(name = "Exam.findByBout", query = "SELECT e FROM Exam e WHERE e.bout = :bout"),
    @NamedQuery(name = "Exam.findBySubjdetailsid", query = "SELECT e FROM Exam e WHERE e.subjdetailsid = :subjdetailsid"),
    @NamedQuery(name = "Exam.findByCreateat", query = "SELECT e FROM Exam e WHERE e.createat = :createat"),
    @NamedQuery(name = "Exam.findByUpdateat", query = "SELECT e FROM Exam e WHERE e.updateat = :updateat"),
    @NamedQuery(name = "Exam.findByRemoveat", query = "SELECT e FROM Exam e WHERE e.removeat = :removeat")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "examid", nullable = false)
    private Integer examid;
    @Size(max = 10)
    @Column(name = "examcodelogin", length = 10)
    private String examcodelogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "examday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date examday;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "examtype", nullable = false, length = 50)
    private String examtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "examtime", nullable = false, length = 50)
    private String examtime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bout", nullable = false)
    private int bout;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdetailsid", nullable = false)
    private int subjdetailsid;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examid")
    private Collection<Result> resultCollection;
    @JoinColumn(name = "roomid", referencedColumnName = "roomid", nullable = false)
    @ManyToOne(optional = false)
    private Room roomid;
    @JoinColumn(name = "stugroid", referencedColumnName = "stugroid", nullable = false)
    @ManyToOne(optional = false)
    private StudentGroup stugroid;

    public Exam() {
    }

    public Exam(Integer examid) {
        this.examid = examid;
    }

    public Exam(Integer examid, Date examday, String examtype, String examtime, int bout, int subjdetailsid, String removeat) {
        this.examid = examid;
        this.examday = examday;
        this.examtype = examtype;
        this.examtime = examtime;
        this.bout = bout;
        this.subjdetailsid = subjdetailsid;
        this.removeat = removeat;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public String getExamcodelogin() {
        return examcodelogin;
    }

    public void setExamcodelogin(String examcodelogin) {
        this.examcodelogin = examcodelogin;
    }

    public Date getExamday() {
        return examday;
    }

    public void setExamday(Date examday) {
        this.examday = examday;
    }

    public String getExamtype() {
        return examtype;
    }

    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }

    public String getExamtime() {
        return examtime;
    }

    public void setExamtime(String examtime) {
        this.examtime = examtime;
    }

    public int getBout() {
        return bout;
    }

    public void setBout(int bout) {
        this.bout = bout;
    }

    public int getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(int subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
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
    public Collection<Result> getResultCollection() {
        return resultCollection;
    }

    public void setResultCollection(Collection<Result> resultCollection) {
        this.resultCollection = resultCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examid != null ? examid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.examid == null && other.examid != null) || (this.examid != null && !this.examid.equals(other.examid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Exam[ examid=" + examid + " ]";
    }
    
}
