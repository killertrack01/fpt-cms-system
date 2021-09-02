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
@Table(name = "semester", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"semnm"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semester.findAll", query = "SELECT s FROM Semester s"),
    @NamedQuery(name = "Semester.findBySemid", query = "SELECT s FROM Semester s WHERE s.semid = :semid"),
    @NamedQuery(name = "Semester.findBySemnm", query = "SELECT s FROM Semester s WHERE s.semnm = :semnm"),
    @NamedQuery(name = "Semester.findByCreateat", query = "SELECT s FROM Semester s WHERE s.createat = :createat"),
    @NamedQuery(name = "Semester.findByUpdateat", query = "SELECT s FROM Semester s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "Semester.findByRemoveat", query = "SELECT s FROM Semester s WHERE s.removeat = :removeat")})
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "semid", nullable = false)
    private Integer semid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "semnm", nullable = false, length = 255)
    private String semnm;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semid")
    private Collection<SubjectDetails> subjectDetailsCollection;

    public Semester() {
    }

    public Semester(Integer semid) {
        this.semid = semid;
    }

    public Semester(Integer semid, String semnm, String removeat) {
        this.semid = semid;
        this.semnm = semnm;
        this.removeat = removeat;
    }

    public Integer getSemid() {
        return semid;
    }

    public void setSemid(Integer semid) {
        this.semid = semid;
    }

    public String getSemnm() {
        return semnm;
    }

    public void setSemnm(String semnm) {
        this.semnm = semnm;
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
    public Collection<SubjectDetails> getSubjectDetailsCollection() {
        return subjectDetailsCollection;
    }

    public void setSubjectDetailsCollection(Collection<SubjectDetails> subjectDetailsCollection) {
        this.subjectDetailsCollection = subjectDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (semid != null ? semid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semester)) {
            return false;
        }
        Semester other = (Semester) object;
        if ((this.semid == null && other.semid != null) || (this.semid != null && !this.semid.equals(other.semid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Semester[ semid=" + semid + " ]";
    }
    
}
