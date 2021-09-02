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
@Table(name = "find_studentgroup", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FindStudentgroup.findAll", query = "SELECT f FROM FindStudentgroup f"),
    @NamedQuery(name = "FindStudentgroup.findByStugroid", query = "SELECT f FROM FindStudentgroup f WHERE f.stugroid = :stugroid"),
    @NamedQuery(name = "FindStudentgroup.findByStugronm", query = "SELECT f FROM FindStudentgroup f WHERE f.stugronm = :stugronm"),
    @NamedQuery(name = "FindStudentgroup.findByOpeningdate", query = "SELECT f FROM FindStudentgroup f WHERE f.openingdate = :openingdate"),
    @NamedQuery(name = "FindStudentgroup.findBySession", query = "SELECT f FROM FindStudentgroup f WHERE f.session = :session"),
    @NamedQuery(name = "FindStudentgroup.findByShift", query = "SELECT f FROM FindStudentgroup f WHERE f.shift = :shift"),
    @NamedQuery(name = "FindStudentgroup.findByBranchcamid", query = "SELECT f FROM FindStudentgroup f WHERE f.branchcamid = :branchcamid"),
    @NamedQuery(name = "FindStudentgroup.findByCourid", query = "SELECT f FROM FindStudentgroup f WHERE f.courid = :courid"),
    @NamedQuery(name = "FindStudentgroup.findByCreateat", query = "SELECT f FROM FindStudentgroup f WHERE f.createat = :createat"),
    @NamedQuery(name = "FindStudentgroup.findByUpdateat", query = "SELECT f FROM FindStudentgroup f WHERE f.updateat = :updateat"),
    @NamedQuery(name = "FindStudentgroup.findByRemoveat", query = "SELECT f FROM FindStudentgroup f WHERE f.removeat = :removeat"),
    @NamedQuery(name = "FindStudentgroup.findByBranchcamnm", query = "SELECT f FROM FindStudentgroup f WHERE f.branchcamnm = :branchcamnm"),
    @NamedQuery(name = "FindStudentgroup.findByCournm", query = "SELECT f FROM FindStudentgroup f WHERE f.cournm = :cournm")})
public class FindStudentgroup implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "branchcamnm", nullable = false, length = 255)
    private String branchcamnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cournm", nullable = false, length = 255)
    private String cournm;

    public FindStudentgroup() {
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

    public String getBranchcamnm() {
        return branchcamnm;
    }

    public void setBranchcamnm(String branchcamnm) {
        this.branchcamnm = branchcamnm;
    }

    public String getCournm() {
        return cournm;
    }

    public void setCournm(String cournm) {
        this.cournm = cournm;
    }
    
}
