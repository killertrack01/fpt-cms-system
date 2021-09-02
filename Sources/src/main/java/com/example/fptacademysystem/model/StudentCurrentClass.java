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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "student_current_class", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentCurrentClass.findAll", query = "SELECT s FROM StudentCurrentClass s"),
    @NamedQuery(name = "StudentCurrentClass.findBySccid", query = "SELECT s FROM StudentCurrentClass s WHERE s.sccid = :sccid"),
    @NamedQuery(name = "StudentCurrentClass.findByStudclassid", query = "SELECT s FROM StudentCurrentClass s WHERE s.studclassid = :studclassid"),
    @NamedQuery(name = "StudentCurrentClass.findByRegissubj", query = "SELECT s FROM StudentCurrentClass s WHERE s.regissubj = :regissubj"),
    @NamedQuery(name = "StudentCurrentClass.findByCass", query = "SELECT s FROM StudentCurrentClass s WHERE s.cass = :cass"),
    @NamedQuery(name = "StudentCurrentClass.findByRestudytime", query = "SELECT s FROM StudentCurrentClass s WHERE s.restudytime = :restudytime"),
    @NamedQuery(name = "StudentCurrentClass.findBySemstatus", query = "SELECT s FROM StudentCurrentClass s WHERE s.semstatus = :semstatus"),
    @NamedQuery(name = "StudentCurrentClass.findByStugroid", query = "SELECT s FROM StudentCurrentClass s WHERE s.stugroid = :stugroid"),
    @NamedQuery(name = "StudentCurrentClass.findByStuid", query = "SELECT s FROM StudentCurrentClass s WHERE s.stuid = :stuid"),
    @NamedQuery(name = "StudentCurrentClass.findByRollnum", query = "SELECT s FROM StudentCurrentClass s WHERE s.rollnum = :rollnum"),
    @NamedQuery(name = "StudentCurrentClass.findByFullnm", query = "SELECT s FROM StudentCurrentClass s WHERE s.fullnm = :fullnm"),
    @NamedQuery(name = "StudentCurrentClass.findByRemoveat", query = "SELECT s FROM StudentCurrentClass s WHERE s.removeat = :removeat")})
public class StudentCurrentClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sccid", nullable = false)
    private long sccid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "studclassid", nullable = false)
    private int studclassid;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stuid", nullable = false)
    private int stuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rollnum", nullable = false, length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullnm", nullable = false, length = 255)
    private String fullnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;

    public StudentCurrentClass() {
    }

    public long getSccid() {
        return sccid;
    }

    public void setSccid(long sccid) {
        this.sccid = sccid;
    }

    public int getStudclassid() {
        return studclassid;
    }

    public void setStudclassid(int studclassid) {
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

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getFullnm() {
        return fullnm;
    }

    public void setFullnm(String fullnm) {
        this.fullnm = fullnm;
    }

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }
    
}
