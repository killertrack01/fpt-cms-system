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
@Table(name = "student_class", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentClass.findAll", query = "SELECT s FROM StudentClass s"),
    @NamedQuery(name = "StudentClass.findByStudclassid", query = "SELECT s FROM StudentClass s WHERE s.studclassid = :studclassid"),
    @NamedQuery(name = "StudentClass.findByRegissubj", query = "SELECT s FROM StudentClass s WHERE s.regissubj = :regissubj"),
    @NamedQuery(name = "StudentClass.findByCass", query = "SELECT s FROM StudentClass s WHERE s.cass = :cass"),
    @NamedQuery(name = "StudentClass.findByRestudytime", query = "SELECT s FROM StudentClass s WHERE s.restudytime = :restudytime"),
    @NamedQuery(name = "StudentClass.findBySemstatus", query = "SELECT s FROM StudentClass s WHERE s.semstatus = :semstatus"),
    @NamedQuery(name = "StudentClass.findByCreateat", query = "SELECT s FROM StudentClass s WHERE s.createat = :createat"),
    @NamedQuery(name = "StudentClass.findByUpdateat", query = "SELECT s FROM StudentClass s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "StudentClass.findByRemoveat", query = "SELECT s FROM StudentClass s WHERE s.removeat = :removeat")})
public class StudentClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "studclassid", nullable = false)
    private Integer studclassid;
    @Column(name = "regissubj")
    private Integer regissubj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cass", nullable = false, length = 255)
    private String cass;
    @Column(name = "restudytime")
    @Temporal(TemporalType.DATE)
    private Date restudytime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semstatus", nullable = false)
    private int semstatus;
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
    @JoinColumn(name = "stugroid", referencedColumnName = "stugroid", nullable = false)
    @ManyToOne(optional = false)
    private StudentGroup stugroid;

    public StudentClass() {
    }

    public StudentClass(Integer studclassid) {
        this.studclassid = studclassid;
    }

    public StudentClass(Integer studclassid, String cass, int semstatus, String removeat) {
        this.studclassid = studclassid;
        this.cass = cass;
        this.semstatus = semstatus;
        this.removeat = removeat;
    }

    public Integer getStudclassid() {
        return studclassid;
    }

    public void setStudclassid(Integer studclassid) {
        this.studclassid = studclassid;
    }

    public Integer getRegissubj() {
        return regissubj;
    }

    public void setRegissubj(Integer regissubj) {
        this.regissubj = regissubj;
    }

    public String getCass() {
        return cass;
    }

    public void setCass(String cass) {
        this.cass = cass;
    }

    public Date getRestudytime() {
        return restudytime;
    }

    public void setRestudytime(Date restudytime) {
        this.restudytime = restudytime;
    }

    public int getSemstatus() {
        return semstatus;
    }

    public void setSemstatus(int semstatus) {
        this.semstatus = semstatus;
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

    public StudentGroup getStugroid() {
        return stugroid;
    }

    public void setStugroid(StudentGroup stugroid) {
        this.stugroid = stugroid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studclassid != null ? studclassid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentClass)) {
            return false;
        }
        StudentClass other = (StudentClass) object;
        if ((this.studclassid == null && other.studclassid != null) || (this.studclassid != null && !this.studclassid.equals(other.studclassid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.StudentClass[ studclassid=" + studclassid + " ]";
    }
    
}
