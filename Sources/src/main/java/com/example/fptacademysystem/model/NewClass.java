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
@Table(name = "new_class", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewClass.findAll", query = "SELECT n FROM NewClass n"),
    @NamedQuery(name = "NewClass.findByStugroid", query = "SELECT n FROM NewClass n WHERE n.stugroid = :stugroid"),
    @NamedQuery(name = "NewClass.findByStugronm", query = "SELECT n FROM NewClass n WHERE n.stugronm = :stugronm"),
    @NamedQuery(name = "NewClass.findByOpeningdate", query = "SELECT n FROM NewClass n WHERE n.openingdate = :openingdate"),
    @NamedQuery(name = "NewClass.findBySession", query = "SELECT n FROM NewClass n WHERE n.session = :session"),
    @NamedQuery(name = "NewClass.findByShift", query = "SELECT n FROM NewClass n WHERE n.shift = :shift"),
    @NamedQuery(name = "NewClass.findByBranchcamid", query = "SELECT n FROM NewClass n WHERE n.branchcamid = :branchcamid"),
    @NamedQuery(name = "NewClass.findByCourid", query = "SELECT n FROM NewClass n WHERE n.courid = :courid"),
    @NamedQuery(name = "NewClass.findByCreateat", query = "SELECT n FROM NewClass n WHERE n.createat = :createat"),
    @NamedQuery(name = "NewClass.findByUpdateat", query = "SELECT n FROM NewClass n WHERE n.updateat = :updateat"),
    @NamedQuery(name = "NewClass.findByRemoveat", query = "SELECT n FROM NewClass n WHERE n.removeat = :removeat")})
public class NewClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "openingdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date openingdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "session", nullable = false, length = 10)
    private String session;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shift", nullable = false)
    private int shift;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchcamid", nullable = false)
    private int branchcamid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courid", nullable = false)
    private int courid;
    @Column(name = "createat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createat;
    @Column(name = "updateat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;

    public NewClass() {
    }

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public Date getOpeningdate() {
        return openingdate;
    }

    public void setOpeningdate(Date openingdate) {
        this.openingdate = openingdate;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(int branchcamid) {
        this.branchcamid = branchcamid;
    }

    public int getCourid() {
        return courid;
    }

    public void setCourid(int courid) {
        this.courid = courid;
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
    
}
