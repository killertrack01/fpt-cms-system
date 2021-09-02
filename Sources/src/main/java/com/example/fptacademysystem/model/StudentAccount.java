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
@Table(name = "student_account", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rollnum"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAccount.findAll", query = "SELECT s FROM StudentAccount s"),
    @NamedQuery(name = "StudentAccount.findByStuaccid", query = "SELECT s FROM StudentAccount s WHERE s.stuaccid = :stuaccid"),
    @NamedQuery(name = "StudentAccount.findByRollnum", query = "SELECT s FROM StudentAccount s WHERE s.rollnum = :rollnum"),
    @NamedQuery(name = "StudentAccount.findByPass", query = "SELECT s FROM StudentAccount s WHERE s.pass = :pass"),
    @NamedQuery(name = "StudentAccount.findByCreateat", query = "SELECT s FROM StudentAccount s WHERE s.createat = :createat"),
    @NamedQuery(name = "StudentAccount.findByUpdateat", query = "SELECT s FROM StudentAccount s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "StudentAccount.findByRemoveat", query = "SELECT s FROM StudentAccount s WHERE s.removeat = :removeat")})
public class StudentAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stuaccid", nullable = false)
    private Integer stuaccid;
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
    @JoinColumn(name = "stuid", referencedColumnName = "stuid", nullable = false)
    @ManyToOne(optional = false)
    private Student stuid;

    public StudentAccount() {
    }

    public StudentAccount(Integer stuaccid) {
        this.stuaccid = stuaccid;
    }

    public StudentAccount(Integer stuaccid, String rollnum, String pass, String removeat) {
        this.stuaccid = stuaccid;
        this.rollnum = rollnum;
        this.pass = pass;
        this.removeat = removeat;
    }

    public Integer getStuaccid() {
        return stuaccid;
    }

    public void setStuaccid(Integer stuaccid) {
        this.stuaccid = stuaccid;
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

    public Student getStuid() {
        return stuid;
    }

    public void setStuid(Student stuid) {
        this.stuid = stuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stuaccid != null ? stuaccid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAccount)) {
            return false;
        }
        StudentAccount other = (StudentAccount) object;
        if ((this.stuaccid == null && other.stuaccid != null) || (this.stuaccid != null && !this.stuaccid.equals(other.stuaccid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.StudentAccount[ stuaccid=" + stuaccid + " ]";
    }
    
}
