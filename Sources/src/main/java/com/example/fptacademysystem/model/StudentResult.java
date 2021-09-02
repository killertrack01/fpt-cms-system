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
@Table(name = "student_result", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentResult.findAll", query = "SELECT s FROM StudentResult s"),
    @NamedQuery(name = "StudentResult.findByResultid", query = "SELECT s FROM StudentResult s WHERE s.resultid = :resultid"),
    @NamedQuery(name = "StudentResult.findByExamresults", query = "SELECT s FROM StudentResult s WHERE s.examresults = :examresults"),
    @NamedQuery(name = "StudentResult.findByExamday", query = "SELECT s FROM StudentResult s WHERE s.examday = :examday"),
    @NamedQuery(name = "StudentResult.findByExamtype", query = "SELECT s FROM StudentResult s WHERE s.examtype = :examtype"),
    @NamedQuery(name = "StudentResult.findByExamtime", query = "SELECT s FROM StudentResult s WHERE s.examtime = :examtime"),
    @NamedQuery(name = "StudentResult.findByBout", query = "SELECT s FROM StudentResult s WHERE s.bout = :bout"),
    @NamedQuery(name = "StudentResult.findBySubjnm", query = "SELECT s FROM StudentResult s WHERE s.subjnm = :subjnm"),
    @NamedQuery(name = "StudentResult.findByCass", query = "SELECT s FROM StudentResult s WHERE s.cass = :cass"),
    @NamedQuery(name = "StudentResult.findByStugronm", query = "SELECT s FROM StudentResult s WHERE s.stugronm = :stugronm"),
    @NamedQuery(name = "StudentResult.findByStuid", query = "SELECT s FROM StudentResult s WHERE s.stuid = :stuid")})
public class StudentResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "resultid", nullable = false)
    private int resultid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "examresults", nullable = false)
    private int examresults;
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
    @Size(min = 1, max = 255)
    @Column(name = "subjnm", nullable = false, length = 255)
    private String subjnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cass", nullable = false, length = 255)
    private String cass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stuid", nullable = false)
    private int stuid;

    public StudentResult() {
    }

    public int getResultid() {
        return resultid;
    }

    public void setResultid(int resultid) {
        this.resultid = resultid;
    }

    public int getExamresults() {
        return examresults;
    }

    public void setExamresults(int examresults) {
        this.examresults = examresults;
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

    public String getSubjnm() {
        return subjnm;
    }

    public void setSubjnm(String subjnm) {
        this.subjnm = subjnm;
    }

    public String getCass() {
        return cass;
    }

    public void setCass(String cass) {
        this.cass = cass;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }
    
}
