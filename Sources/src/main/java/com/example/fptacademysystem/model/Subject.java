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
@Table(name = "subject", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"subjnm"}),
    @UniqueConstraint(columnNames = {"shortnm"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findBySubjid", query = "SELECT s FROM Subject s WHERE s.subjid = :subjid"),
    @NamedQuery(name = "Subject.findByShortnm", query = "SELECT s FROM Subject s WHERE s.shortnm = :shortnm"),
    @NamedQuery(name = "Subject.findBySubjnm", query = "SELECT s FROM Subject s WHERE s.subjnm = :subjnm"),
    @NamedQuery(name = "Subject.findByBranchid", query = "SELECT s FROM Subject s WHERE s.branchid = :branchid"),
    @NamedQuery(name = "Subject.findByCreateat", query = "SELECT s FROM Subject s WHERE s.createat = :createat"),
    @NamedQuery(name = "Subject.findByUpdateat", query = "SELECT s FROM Subject s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "Subject.findByRemoveat", query = "SELECT s FROM Subject s WHERE s.removeat = :removeat")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subjid", nullable = false)
    private Integer subjid;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjid")
    private Collection<SubjectDetails> subjectDetailsCollection;

    public Subject() {
    }

    public Subject(Integer subjid) {
        this.subjid = subjid;
    }

    public Subject(Integer subjid, String shortnm, String subjnm, int branchid, String removeat) {
        this.subjid = subjid;
        this.shortnm = shortnm;
        this.subjnm = subjnm;
        this.branchid = branchid;
        this.removeat = removeat;
    }

    public Integer getSubjid() {
        return subjid;
    }

    public void setSubjid(Integer subjid) {
        this.subjid = subjid;
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
        hash += (subjid != null ? subjid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.subjid == null && other.subjid != null) || (this.subjid != null && !this.subjid.equals(other.subjid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Subject[ subjid=" + subjid + " ]";
    }
    
}
