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
@Table(name = "courses", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cournm"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findByCourid", query = "SELECT c FROM Courses c WHERE c.courid = :courid"),
    @NamedQuery(name = "Courses.findByCournm", query = "SELECT c FROM Courses c WHERE c.cournm = :cournm"),
    @NamedQuery(name = "Courses.findByCreateat", query = "SELECT c FROM Courses c WHERE c.createat = :createat"),
    @NamedQuery(name = "Courses.findByUpdateat", query = "SELECT c FROM Courses c WHERE c.updateat = :updateat"),
    @NamedQuery(name = "Courses.findByRemoveat", query = "SELECT c FROM Courses c WHERE c.removeat = :removeat")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "courid", nullable = false)
    private Integer courid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cournm", nullable = false, length = 255)
    private String cournm;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courid")
    private Collection<StudentGroup> studentGroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courid")
    private Collection<SubjectDetails> subjectDetailsCollection;
    @JoinColumn(name = "branchid", referencedColumnName = "branchid", nullable = false)
    @ManyToOne(optional = false)
    private Branch branchid;

    public Courses() {
    }

    public Courses(Integer courid) {
        this.courid = courid;
    }

    public Courses(Integer courid, String cournm, String removeat) {
        this.courid = courid;
        this.cournm = cournm;
        this.removeat = removeat;
    }

    public Integer getCourid() {
        return courid;
    }

    public void setCourid(Integer courid) {
        this.courid = courid;
    }

    public String getCournm() {
        return cournm;
    }

    public void setCournm(String cournm) {
        this.cournm = cournm;
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
    public Collection<StudentGroup> getStudentGroupCollection() {
        return studentGroupCollection;
    }

    public void setStudentGroupCollection(Collection<StudentGroup> studentGroupCollection) {
        this.studentGroupCollection = studentGroupCollection;
    }

    @XmlTransient
    public Collection<SubjectDetails> getSubjectDetailsCollection() {
        return subjectDetailsCollection;
    }

    public void setSubjectDetailsCollection(Collection<SubjectDetails> subjectDetailsCollection) {
        this.subjectDetailsCollection = subjectDetailsCollection;
    }

    public Branch getBranchid() {
        return branchid;
    }

    public void setBranchid(Branch branchid) {
        this.branchid = branchid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courid != null ? courid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.courid == null && other.courid != null) || (this.courid != null && !this.courid.equals(other.courid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Courses[ courid=" + courid + " ]";
    }
    
}
