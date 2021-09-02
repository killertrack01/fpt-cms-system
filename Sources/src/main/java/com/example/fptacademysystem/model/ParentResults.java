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
@Table(name = "parent_results", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentResults.findAll", query = "SELECT p FROM ParentResults p"),
    @NamedQuery(name = "ParentResults.findByResultid", query = "SELECT p FROM ParentResults p WHERE p.resultid = :resultid"),
    @NamedQuery(name = "ParentResults.findByFullnm", query = "SELECT p FROM ParentResults p WHERE p.fullnm = :fullnm"),
    @NamedQuery(name = "ParentResults.findBySubjnm", query = "SELECT p FROM ParentResults p WHERE p.subjnm = :subjnm"),
    @NamedQuery(name = "ParentResults.findByBout", query = "SELECT p FROM ParentResults p WHERE p.bout = :bout"),
    @NamedQuery(name = "ParentResults.findByExamday", query = "SELECT p FROM ParentResults p WHERE p.examday = :examday"),
    @NamedQuery(name = "ParentResults.findByExamtype", query = "SELECT p FROM ParentResults p WHERE p.examtype = :examtype"),
    @NamedQuery(name = "ParentResults.findByExamresults", query = "SELECT p FROM ParentResults p WHERE p.examresults = :examresults"),
    @NamedQuery(name = "ParentResults.findByCass", query = "SELECT p FROM ParentResults p WHERE p.cass = :cass"),
    @NamedQuery(name = "ParentResults.findByStugronm", query = "SELECT p FROM ParentResults p WHERE p.stugronm = :stugronm"),
    @NamedQuery(name = "ParentResults.findByParid", query = "SELECT p FROM ParentResults p WHERE p.parid = :parid")})
public class ParentResults implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "resultid", nullable = false)
    private int resultid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullnm", nullable = false, length = 255)
    private String fullnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subjnm", nullable = false, length = 255)
    private String subjnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bout", nullable = false)
    private int bout;
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
    @Column(name = "examresults", nullable = false)
    private int examresults;
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
    @Column(name = "parid", nullable = false)
    private int parid;

    public ParentResults() {
    }

    public int getResultid() {
        return resultid;
    }

    public void setResultid(int resultid) {
        this.resultid = resultid;
    }

    public String getFullnm() {
        return fullnm;
    }

    public void setFullnm(String fullnm) {
        this.fullnm = fullnm;
    }

    public String getSubjnm() {
        return subjnm;
    }

    public void setSubjnm(String subjnm) {
        this.subjnm = subjnm;
    }

    public int getBout() {
        return bout;
    }

    public void setBout(int bout) {
        this.bout = bout;
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

    public int getExamresults() {
        return examresults;
    }

    public void setExamresults(int examresults) {
        this.examresults = examresults;
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

    public int getParid() {
        return parid;
    }

    public void setParid(int parid) {
        this.parid = parid;
    }
    
}
