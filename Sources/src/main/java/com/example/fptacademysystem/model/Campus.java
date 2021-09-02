/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "campus", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"campusnm"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campus.findAll", query = "SELECT c FROM Campus c"),
    @NamedQuery(name = "Campus.findByCampusid", query = "SELECT c FROM Campus c WHERE c.campusid = :campusid"),
    @NamedQuery(name = "Campus.findByCampusnm", query = "SELECT c FROM Campus c WHERE c.campusnm = :campusnm"),
    @NamedQuery(name = "Campus.findByCreateat", query = "SELECT c FROM Campus c WHERE c.createat = :createat"),
    @NamedQuery(name = "Campus.findByUpdateat", query = "SELECT c FROM Campus c WHERE c.updateat = :updateat"),
    @NamedQuery(name = "Campus.findByRemoveat", query = "SELECT c FROM Campus c WHERE c.removeat = :removeat")})
public class Campus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "campusid", nullable = false)
    private Integer campusid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "campusnm", nullable = false, length = 255)
    private String campusnm;
    @Column(name = "createat")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createat;
    @Column(name = "updateat")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campusid")
    private Collection<BranchCampus> branchCampusCollection;
    @OneToMany(mappedBy = "campusid")
    private Collection<Room> roomCollection;

    public Campus() {
    }

    public Campus(Integer campusid) {
        this.campusid = campusid;
    }

    public Campus(Integer campusid, String campusnm, String removeat) {
        this.campusid = campusid;
        this.campusnm = campusnm;
        this.removeat = removeat;
    }

    public Integer getCampusid() {
        return campusid;
    }

    public void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }

    public String getCampusnm() {
        return campusnm;
    }

    public void setCampusnm(String campusnm) {
        this.campusnm = campusnm;
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

    @XmlTransient
    public Collection<BranchCampus> getBranchCampusCollection() {
        return branchCampusCollection;
    }

    public void setBranchCampusCollection(Collection<BranchCampus> branchCampusCollection) {
        this.branchCampusCollection = branchCampusCollection;
    }

    @XmlTransient
    public Collection<Room> getRoomCollection() {
        return roomCollection;
    }

    public void setRoomCollection(Collection<Room> roomCollection) {
        this.roomCollection = roomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campusid != null ? campusid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campus)) {
            return false;
        }
        Campus other = (Campus) object;
        if ((this.campusid == null && other.campusid != null) || (this.campusid != null && !this.campusid.equals(other.campusid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Campus[ campusid=" + campusid + " ]";
    }
    
}
