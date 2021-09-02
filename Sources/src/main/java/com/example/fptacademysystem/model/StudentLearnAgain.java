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
@Table(name = "student_learn_again", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentLearnAgain.findAll", query = "SELECT s FROM StudentLearnAgain s"),
    @NamedQuery(name = "StudentLearnAgain.findBySlaid", query = "SELECT s FROM StudentLearnAgain s WHERE s.slaid = :slaid"),
    @NamedQuery(name = "StudentLearnAgain.findByStudclassid", query = "SELECT s FROM StudentLearnAgain s WHERE s.studclassid = :studclassid"),
    @NamedQuery(name = "StudentLearnAgain.findByRegissubj", query = "SELECT s FROM StudentLearnAgain s WHERE s.regissubj = :regissubj"),
    @NamedQuery(name = "StudentLearnAgain.findByCass", query = "SELECT s FROM StudentLearnAgain s WHERE s.cass = :cass"),
    @NamedQuery(name = "StudentLearnAgain.findByRestudytime", query = "SELECT s FROM StudentLearnAgain s WHERE s.restudytime = :restudytime"),
    @NamedQuery(name = "StudentLearnAgain.findBySemstatus", query = "SELECT s FROM StudentLearnAgain s WHERE s.semstatus = :semstatus"),
    @NamedQuery(name = "StudentLearnAgain.findByStugroid", query = "SELECT s FROM StudentLearnAgain s WHERE s.stugroid = :stugroid"),
    @NamedQuery(name = "StudentLearnAgain.findByStuid", query = "SELECT s FROM StudentLearnAgain s WHERE s.stuid = :stuid"),
    @NamedQuery(name = "StudentLearnAgain.findByRollnum", query = "SELECT s FROM StudentLearnAgain s WHERE s.rollnum = :rollnum"),
    @NamedQuery(name = "StudentLearnAgain.findByFullnm", query = "SELECT s FROM StudentLearnAgain s WHERE s.fullnm = :fullnm"),
    @NamedQuery(name = "StudentLearnAgain.findByRemoveat", query = "SELECT s FROM StudentLearnAgain s WHERE s.removeat = :removeat")})
public class StudentLearnAgain implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "slaid", nullable = false)
    private long slaid;
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

    public StudentLearnAgain() {
    }

    public long getSlaid() {
        return slaid;
    }

    public void setSlaid(long slaid) {
        this.slaid = slaid;
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
