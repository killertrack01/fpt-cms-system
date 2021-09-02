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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "mindate_maxdate", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MindateMaxdate.findAll", query = "SELECT m FROM MindateMaxdate m"),
    @NamedQuery(name = "MindateMaxdate.findByMmid", query = "SELECT m FROM MindateMaxdate m WHERE m.mmid = :mmid"),
    @NamedQuery(name = "MindateMaxdate.findByOpeningdate", query = "SELECT m FROM MindateMaxdate m WHERE m.openingdate = :openingdate"),
    @NamedQuery(name = "MindateMaxdate.findByShift", query = "SELECT m FROM MindateMaxdate m WHERE m.shift = :shift"),
    @NamedQuery(name = "MindateMaxdate.findBySemid", query = "SELECT m FROM MindateMaxdate m WHERE m.semid = :semid"),
    @NamedQuery(name = "MindateMaxdate.findByStugroid", query = "SELECT m FROM MindateMaxdate m WHERE m.stugroid = :stugroid"),
    @NamedQuery(name = "MindateMaxdate.findByMindate", query = "SELECT m FROM MindateMaxdate m WHERE m.mindate = :mindate"),
    @NamedQuery(name = "MindateMaxdate.findByMaxdate", query = "SELECT m FROM MindateMaxdate m WHERE m.maxdate = :maxdate")})
public class MindateMaxdate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mmid", nullable = false)
    private long mmid;
    @Column(name = "openingdate")
    @Temporal(TemporalType.DATE)
    private Date openingdate;
    @Column(name = "shift")
    private Integer shift;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semid", nullable = false)
    private int semid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;
    @Column(name = "mindate")
    @Temporal(TemporalType.DATE)
    private Date mindate;
    @Column(name = "maxdate")
    @Temporal(TemporalType.DATE)
    private Date maxdate;

    public MindateMaxdate() {
    }

    public long getMmid() {
        return mmid;
    }

    public void setMmid(long mmid) {
        this.mmid = mmid;
    }

    public Date getOpeningdate() {
        return openingdate;
    }

    public void setOpeningdate(Date openingdate) {
        this.openingdate = openingdate;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }

    public Date getMindate() {
        return mindate;
    }

    public void setMindate(Date mindate) {
        this.mindate = mindate;
    }

    public Date getMaxdate() {
        return maxdate;
    }

    public void setMaxdate(Date maxdate) {
        this.maxdate = maxdate;
    }
    
}
