/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "search_subject", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SearchSubject.findAll", query = "SELECT s FROM SearchSubject s"),
    @NamedQuery(name = "SearchSubject.findBySsid", query = "SELECT s FROM SearchSubject s WHERE s.ssid = :ssid"),
    @NamedQuery(name = "SearchSubject.findByStudytime", query = "SELECT s FROM SearchSubject s WHERE s.studytime = :studytime"),
    @NamedQuery(name = "SearchSubject.findBySubjid", query = "SELECT s FROM SearchSubject s WHERE s.subjid = :subjid"),
    @NamedQuery(name = "SearchSubject.findByShortnm", query = "SELECT s FROM SearchSubject s WHERE s.shortnm = :shortnm"),
    @NamedQuery(name = "SearchSubject.findBySubjectname", query = "SELECT s FROM SearchSubject s WHERE s.subjectname = :subjectname"),
    @NamedQuery(name = "SearchSubject.findBySlots", query = "SELECT s FROM SearchSubject s WHERE s.slots = :slots"),
    @NamedQuery(name = "SearchSubject.findBySemname", query = "SELECT s FROM SearchSubject s WHERE s.semname = :semname"),
    @NamedQuery(name = "SearchSubject.findByCoursename", query = "SELECT s FROM SearchSubject s WHERE s.coursename = :coursename"),
    @NamedQuery(name = "SearchSubject.findByTeaname", query = "SELECT s FROM SearchSubject s WHERE s.teaname = :teaname"),
    @NamedQuery(name = "SearchSubject.findByStugronm", query = "SELECT s FROM SearchSubject s WHERE s.stugronm = :stugronm"),
    @NamedQuery(name = "SearchSubject.findByStugroid", query = "SELECT s FROM SearchSubject s WHERE s.stugroid = :stugroid")})
public class SearchSubject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ssid", nullable = false)
    private long ssid;
    @Size(max = 24)
    @Column(name = "studytime", length = 24)
    private String studytime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjid", nullable = false)
    private int subjid;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "slots", nullable = false)
    private int slots;
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

    public SearchSubject() {
    }

    public long getSsid() {
        return ssid;
    }

    public void setSsid(long ssid) {
        this.ssid = ssid;
    }

    public String getStudytime() {
        return studytime;
    }

    public void setStudytime(String studytime) {
        this.studytime = studytime;
    }

    public int getSubjid() {
        return subjid;
    }

    public void setSubjid(int subjid) {
        this.subjid = subjid;
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

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
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
