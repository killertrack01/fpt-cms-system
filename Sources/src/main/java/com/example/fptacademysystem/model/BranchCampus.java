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
@Table(name = "branch_campus", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchCampus.findAll", query = "SELECT b FROM BranchCampus b"),
    @NamedQuery(name = "BranchCampus.findByBranchcamid", query = "SELECT b FROM BranchCampus b WHERE b.branchcamid = :branchcamid"),
    @NamedQuery(name = "BranchCampus.findByBranchcamnm", query = "SELECT b FROM BranchCampus b WHERE b.branchcamnm = :branchcamnm"),
    @NamedQuery(name = "BranchCampus.findByAddress", query = "SELECT b FROM BranchCampus b WHERE b.address = :address"),
    @NamedQuery(name = "BranchCampus.findByCreateat", query = "SELECT b FROM BranchCampus b WHERE b.createat = :createat"),
    @NamedQuery(name = "BranchCampus.findByUpdateat", query = "SELECT b FROM BranchCampus b WHERE b.updateat = :updateat"),
    @NamedQuery(name = "BranchCampus.findByRemoveat", query = "SELECT b FROM BranchCampus b WHERE b.removeat = :removeat")})
public class BranchCampus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "branchcamid", nullable = false)
    private Integer branchcamid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "branchcamnm", nullable = false, length = 255)
    private String branchcamnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false, length = 255)
    private String address;
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
    @JoinColumn(name = "branchid", referencedColumnName = "branchid", nullable = false)
    @ManyToOne(optional = false)
    private Branch branchid;
    @JoinColumn(name = "campusid", referencedColumnName = "campusid", nullable = false)
    @ManyToOne(optional = false)
    private Campus campusid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branchcamid")
    private Collection<StudentGroup> studentGroupCollection;

    public BranchCampus() {
    }

    public BranchCampus(Integer branchcamid) {
        this.branchcamid = branchcamid;
    }

    public BranchCampus(Integer branchcamid, String branchcamnm, String address, String removeat) {
        this.branchcamid = branchcamid;
        this.branchcamnm = branchcamnm;
        this.address = address;
        this.removeat = removeat;
    }

    public Integer getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(Integer branchcamid) {
        this.branchcamid = branchcamid;
    }

    public String getBranchcamnm() {
        return branchcamnm;
    }

    public void setBranchcamnm(String branchcamnm) {
        this.branchcamnm = branchcamnm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Branch getBranchid() {
        return branchid;
    }

    public void setBranchid(Branch branchid) {
        this.branchid = branchid;
    }

    public Campus getCampusid() {
        return campusid;
    }

    public void setCampusid(Campus campusid) {
        this.campusid = campusid;
    }

    @XmlTransient
    public Collection<StudentGroup> getStudentGroupCollection() {
        return studentGroupCollection;
    }

    public void setStudentGroupCollection(Collection<StudentGroup> studentGroupCollection) {
        this.studentGroupCollection = studentGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (branchcamid != null ? branchcamid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchCampus)) {
            return false;
        }
        BranchCampus other = (BranchCampus) object;
        if ((this.branchcamid == null && other.branchcamid != null) || (this.branchcamid != null && !this.branchcamid.equals(other.branchcamid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.BranchCampus[ branchcamid=" + branchcamid + " ]";
    }
    
}
