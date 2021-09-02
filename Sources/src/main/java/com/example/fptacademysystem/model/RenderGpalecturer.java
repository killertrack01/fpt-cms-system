/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "render_gpalecturer", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenderGpalecturer.findAll", query = "SELECT r FROM RenderGpalecturer r"),
    @NamedQuery(name = "RenderGpalecturer.findByGpalecid", query = "SELECT r FROM RenderGpalecturer r WHERE r.gpalecid = :gpalecid"),
    @NamedQuery(name = "RenderGpalecturer.findByGpascore", query = "SELECT r FROM RenderGpalecturer r WHERE r.gpascore = :gpascore"),
    @NamedQuery(name = "RenderGpalecturer.findBySubjnm", query = "SELECT r FROM RenderGpalecturer r WHERE r.subjnm = :subjnm"),
    @NamedQuery(name = "RenderGpalecturer.findByStugronm", query = "SELECT r FROM RenderGpalecturer r WHERE r.stugronm = :stugronm"),
    @NamedQuery(name = "RenderGpalecturer.findByFullnm", query = "SELECT r FROM RenderGpalecturer r WHERE r.fullnm = :fullnm"),
    @NamedQuery(name = "RenderGpalecturer.findByGpamonth", query = "SELECT r FROM RenderGpalecturer r WHERE r.gpamonth = :gpamonth"),
    @NamedQuery(name = "RenderGpalecturer.findByGpayear", query = "SELECT r FROM RenderGpalecturer r WHERE r.gpayear = :gpayear"),
    @NamedQuery(name = "RenderGpalecturer.findByLecturid", query = "SELECT r FROM RenderGpalecturer r WHERE r.lecturid = :lecturid"),
    @NamedQuery(name = "RenderGpalecturer.findByDatefeedback", query = "SELECT r FROM RenderGpalecturer r WHERE r.datefeedback = :datefeedback"),
    @NamedQuery(name = "RenderGpalecturer.findByMaxmonth", query = "SELECT r FROM RenderGpalecturer r WHERE r.maxmonth = :maxmonth")})
public class RenderGpalecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpalecid", nullable = false)
    private int gpalecid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpascore", nullable = false)
    private double gpascore;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subjnm", nullable = false, length = 255)
    private String subjnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullnm", nullable = false, length = 255)
    private String fullnm;
    @Column(name = "gpamonth")
    private Integer gpamonth;
    @Column(name = "gpayear")
    private Integer gpayear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lecturid", nullable = false)
    private int lecturid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datefeedback", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datefeedback;
    @Column(name = "maxmonth")
    private BigInteger maxmonth;

    public RenderGpalecturer() {
    }

    public int getGpalecid() {
        return gpalecid;
    }

    public void setGpalecid(int gpalecid) {
        this.gpalecid = gpalecid;
    }

    public double getGpascore() {
        return gpascore;
    }

    public void setGpascore(double gpascore) {
        this.gpascore = gpascore;
    }

    public String getSubjnm() {
        return subjnm;
    }

    public void setSubjnm(String subjnm) {
        this.subjnm = subjnm;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public String getFullnm() {
        return fullnm;
    }

    public void setFullnm(String fullnm) {
        this.fullnm = fullnm;
    }

    public Integer getGpamonth() {
        return gpamonth;
    }

    public void setGpamonth(Integer gpamonth) {
        this.gpamonth = gpamonth;
    }

    public Integer getGpayear() {
        return gpayear;
    }

    public void setGpayear(Integer gpayear) {
        this.gpayear = gpayear;
    }

    public int getLecturid() {
        return lecturid;
    }

    public void setLecturid(int lecturid) {
        this.lecturid = lecturid;
    }

    public Date getDatefeedback() {
        return datefeedback;
    }

    public void setDatefeedback(Date datefeedback) {
        this.datefeedback = datefeedback;
    }

    public BigInteger getMaxmonth() {
        return maxmonth;
    }

    public void setMaxmonth(BigInteger maxmonth) {
        this.maxmonth = maxmonth;
    }
    
}
