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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "parent_account", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rollnum"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentAccount.findAll", query = "SELECT p FROM ParentAccount p"),
    @NamedQuery(name = "ParentAccount.findByParaccid", query = "SELECT p FROM ParentAccount p WHERE p.paraccid = :paraccid"),
    @NamedQuery(name = "ParentAccount.findByRollnum", query = "SELECT p FROM ParentAccount p WHERE p.rollnum = :rollnum"),
    @NamedQuery(name = "ParentAccount.findByPass", query = "SELECT p FROM ParentAccount p WHERE p.pass = :pass"),
    @NamedQuery(name = "ParentAccount.findByCreateat", query = "SELECT p FROM ParentAccount p WHERE p.createat = :createat"),
    @NamedQuery(name = "ParentAccount.findByUpdateat", query = "SELECT p FROM ParentAccount p WHERE p.updateat = :updateat"),
    @NamedQuery(name = "ParentAccount.findByRemoveat", query = "SELECT p FROM ParentAccount p WHERE p.removeat = :removeat")})
public class ParentAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paraccid", nullable = false)
    private Integer paraccid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rollnum", nullable = false, length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
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
    @JoinColumn(name = "parid", referencedColumnName = "parid", nullable = false)
    @ManyToOne(optional = false)
    private Parent parid;

    public ParentAccount() {
    }

    public ParentAccount(Integer paraccid) {
        this.paraccid = paraccid;
    }

    public ParentAccount(Integer paraccid, String rollnum, String pass, String removeat) {
        this.paraccid = paraccid;
        this.rollnum = rollnum;
        this.pass = pass;
        this.removeat = removeat;
    }

    public Integer getParaccid() {
        return paraccid;
    }

    public void setParaccid(Integer paraccid) {
        this.paraccid = paraccid;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public Parent getParid() {
        return parid;
    }

    public void setParid(Parent parid) {
        this.parid = parid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paraccid != null ? paraccid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentAccount)) {
            return false;
        }
        ParentAccount other = (ParentAccount) object;
        if ((this.paraccid == null && other.paraccid != null) || (this.paraccid != null && !this.paraccid.equals(other.paraccid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.ParentAccount[ paraccid=" + paraccid + " ]";
    }
    
}
