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
@Table(name = "render_studentgroup", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenderStudentgroup.findAll", query = "SELECT r FROM RenderStudentgroup r"),
    @NamedQuery(name = "RenderStudentgroup.findByStugroid", query = "SELECT r FROM RenderStudentgroup r WHERE r.stugroid = :stugroid"),
    @NamedQuery(name = "RenderStudentgroup.findByStugronm", query = "SELECT r FROM RenderStudentgroup r WHERE r.stugronm = :stugronm"),
    @NamedQuery(name = "RenderStudentgroup.findByOpeningdate", query = "SELECT r FROM RenderStudentgroup r WHERE r.openingdate = :openingdate"),
    @NamedQuery(name = "RenderStudentgroup.findBySession", query = "SELECT r FROM RenderStudentgroup r WHERE r.session = :session"),
    @NamedQuery(name = "RenderStudentgroup.findByShift", query = "SELECT r FROM RenderStudentgroup r WHERE r.shift = :shift"),
    @NamedQuery(name = "RenderStudentgroup.findByCourid", query = "SELECT r FROM RenderStudentgroup r WHERE r.courid = :courid"),
    @NamedQuery(name = "RenderStudentgroup.findByRemoveat", query = "SELECT r FROM RenderStudentgroup r WHERE r.removeat = :removeat"),
    @NamedQuery(name = "RenderStudentgroup.findByCournm", query = "SELECT r FROM RenderStudentgroup r WHERE r.cournm = :cournm"),
    @NamedQuery(name = "RenderStudentgroup.findByBranchid", query = "SELECT r FROM RenderStudentgroup r WHERE r.branchid = :branchid"),
    @NamedQuery(name = "RenderStudentgroup.findByCampusid", query = "SELECT r FROM RenderStudentgroup r WHERE r.campusid = :campusid"),
    @NamedQuery(name = "RenderStudentgroup.findByBranchcamid", query = "SELECT r FROM RenderStudentgroup r WHERE r.branchcamid = :branchcamid"),
    @NamedQuery(name = "RenderStudentgroup.findByBranchnm", query = "SELECT r FROM RenderStudentgroup r WHERE r.branchnm = :branchnm"),
    @NamedQuery(name = "RenderStudentgroup.findByCampusnm", query = "SELECT r FROM RenderStudentgroup r WHERE r.campusnm = :campusnm"),
    @NamedQuery(name = "RenderStudentgroup.findByBranchcamnm", query = "SELECT r FROM RenderStudentgroup r WHERE r.branchcamnm = :branchcamnm"),
    @NamedQuery(name = "RenderStudentgroup.findByAddress", query = "SELECT r FROM RenderStudentgroup r WHERE r.address = :address"),
    @NamedQuery(name = "RenderStudentgroup.findByCheckupde", query = "SELECT r FROM RenderStudentgroup r WHERE r.checkupde = :checkupde")})
public class RenderStudentgroup implements Serializable {

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
    @Column(name = "courid", nullable = false)
    private int courid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cournm", nullable = false, length = 255)
    private String cournm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchid", nullable = false)
    private int branchid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "campusid", nullable = false)
    private int campusid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchcamid", nullable = false)
    private int branchcamid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "branchnm", nullable = false, length = 255)
    private String branchnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "campusnm", nullable = false, length = 255)
    private String campusnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "branchcamnm", nullable = false, length = 255)
    private String branchcamnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false, length = 255)
    private String address;
    @Column(name = "checkupde")
    private BigInteger checkupde;

    public RenderStudentgroup() {
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

    public int getCourid() {
        return courid;
    }

    public void setCourid(int courid) {
        this.courid = courid;
    }

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }

    public String getCournm() {
        return cournm;
    }

    public void setCournm(String cournm) {
        this.cournm = cournm;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public int getCampusid() {
        return campusid;
    }

    public void setCampusid(int campusid) {
        this.campusid = campusid;
    }

    public int getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(int branchcamid) {
        this.branchcamid = branchcamid;
    }

    public String getBranchnm() {
        return branchnm;
    }

    public void setBranchnm(String branchnm) {
        this.branchnm = branchnm;
    }

    public String getCampusnm() {
        return campusnm;
    }

    public void setCampusnm(String campusnm) {
        this.campusnm = campusnm;
    }

    public String getBranchcamnm() {
        return branchcamnm;
    }

    public void setBranchcamnm(String branchcamnm) {
        this.branchcamnm = branchcamnm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getCheckupde() {
        return checkupde;
    }

    public void setCheckupde(BigInteger checkupde) {
        this.checkupde = checkupde;
    }
    
}
