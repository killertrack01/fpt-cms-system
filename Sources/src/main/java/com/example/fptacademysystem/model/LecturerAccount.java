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
import javax.persistence.UniqueConstraint;
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
@Table(name = "lecturer_account", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rollnum"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LecturerAccount.findAll", query = "SELECT l FROM LecturerAccount l"),
    @NamedQuery(name = "LecturerAccount.findByTeaaccid", query = "SELECT l FROM LecturerAccount l WHERE l.teaaccid = :teaaccid"),
    @NamedQuery(name = "LecturerAccount.findByRollnum", query = "SELECT l FROM LecturerAccount l WHERE l.rollnum = :rollnum"),
    @NamedQuery(name = "LecturerAccount.findByPass", query = "SELECT l FROM LecturerAccount l WHERE l.pass = :pass"),
    @NamedQuery(name = "LecturerAccount.findByCreateat", query = "SELECT l FROM LecturerAccount l WHERE l.createat = :createat"),
    @NamedQuery(name = "LecturerAccount.findByUpdateat", query = "SELECT l FROM LecturerAccount l WHERE l.updateat = :updateat"),
    @NamedQuery(name = "LecturerAccount.findByRemoveat", query = "SELECT l FROM LecturerAccount l WHERE l.removeat = :removeat")})
public class LecturerAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "teaaccid", nullable = false)
    private Integer teaaccid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rollnum", nullable = false, length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
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
    @JoinColumn(name = "lecturid", referencedColumnName = "lecturid", nullable = false)
    @ManyToOne(optional = false)
    private Lecturer lecturid;

    public LecturerAccount() {
    }

    public LecturerAccount(Integer teaaccid) {
        this.teaaccid = teaaccid;
    }

    public LecturerAccount(Integer teaaccid, String rollnum, String pass, String removeat) {
        this.teaaccid = teaaccid;
        this.rollnum = rollnum;
        this.pass = pass;
        this.removeat = removeat;
    }

    public Integer getTeaaccid() {
        return teaaccid;
    }

    public void setTeaaccid(Integer teaaccid) {
        this.teaaccid = teaaccid;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
        hash += (teaaccid != null ? teaaccid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LecturerAccount)) {
            return false;
        }
        LecturerAccount other = (LecturerAccount) object;
        if ((this.teaaccid == null && other.teaaccid != null) || (this.teaaccid != null && !this.teaaccid.equals(other.teaaccid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.LecturerAccount[ teaaccid=" + teaaccid + " ]";
    }
    
}
