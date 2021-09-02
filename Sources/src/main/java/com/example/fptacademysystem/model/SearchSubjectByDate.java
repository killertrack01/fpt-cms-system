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
@Table(name = "search_subject_by_date", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SearchSubjectByDate.findAll", query = "SELECT s FROM SearchSubjectByDate s"),
    @NamedQuery(name = "SearchSubjectByDate.findBySsbdid", query = "SELECT s FROM SearchSubjectByDate s WHERE s.ssbdid = :ssbdid"),
    @NamedQuery(name = "SearchSubjectByDate.findBySubjid", query = "SELECT s FROM SearchSubjectByDate s WHERE s.subjid = :subjid"),
    @NamedQuery(name = "SearchSubjectByDate.findBySubjdate", query = "SELECT s FROM SearchSubjectByDate s WHERE s.subjdate = :subjdate"),
    @NamedQuery(name = "SearchSubjectByDate.findByShortnm", query = "SELECT s FROM SearchSubjectByDate s WHERE s.shortnm = :shortnm"),
    @NamedQuery(name = "SearchSubjectByDate.findBySubjectname", query = "SELECT s FROM SearchSubjectByDate s WHERE s.subjectname = :subjectname"),
    @NamedQuery(name = "SearchSubjectByDate.findBySemname", query = "SELECT s FROM SearchSubjectByDate s WHERE s.semname = :semname"),
    @NamedQuery(name = "SearchSubjectByDate.findByCoursename", query = "SELECT s FROM SearchSubjectByDate s WHERE s.coursename = :coursename"),
    @NamedQuery(name = "SearchSubjectByDate.findByTeaname", query = "SELECT s FROM SearchSubjectByDate s WHERE s.teaname = :teaname"),
    @NamedQuery(name = "SearchSubjectByDate.findByStugronm", query = "SELECT s FROM SearchSubjectByDate s WHERE s.stugronm = :stugronm"),
    @NamedQuery(name = "SearchSubjectByDate.findByStugroid", query = "SELECT s FROM SearchSubjectByDate s WHERE s.stugroid = :stugroid")})
public class SearchSubjectByDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ssbdid", nullable = false)
    private long ssbdid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjid", nullable = false)
    private int subjid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date subjdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "shortnm", nullable = false, length = 255)
    private String shortnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subjectname", nullable = false, length = 255)
    private String subjectname;
    @Size(max = 20)
    @Column(name = "semname", length = 20)
    private String semname;
    @Size(max = 255)
    @Column(name = "coursename", length = 255)
    private String coursename;
    @Size(max = 255)
    @Column(name = "teaname", length = 255)
    private String teaname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;

    public SearchSubjectByDate() {
    }

    public long getSsbdid() {
        return ssbdid;
    }

    public void setSsbdid(long ssbdid) {
        this.ssbdid = ssbdid;
    }

    public int getSubjid() {
        return subjid;
    }

    public void setSubjid(int subjid) {
        this.subjid = subjid;
    }

    public Date getSubjdate() {
        return subjdate;
    }

    public void setSubjdate(Date subjdate) {
        this.subjdate = subjdate;
    }

    public String getShortnm() {
        return shortnm;
    }

    public void setShortnm(String shortnm) {
        this.shortnm = shortnm;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getSemname() {
        return semname;
    }

    public void setSemname(String semname) {
        this.semname = semname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }
    
}
