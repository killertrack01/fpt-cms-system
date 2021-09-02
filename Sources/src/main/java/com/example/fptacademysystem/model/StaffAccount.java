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
@Table(name = "staff_account", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rollnum"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaffAccount.findAll", query = "SELECT s FROM StaffAccount s"),
    @NamedQuery(name = "StaffAccount.findByStaffaccid", query = "SELECT s FROM StaffAccount s WHERE s.staffaccid = :staffaccid"),
    @NamedQuery(name = "StaffAccount.findByRollnum", query = "SELECT s FROM StaffAccount s WHERE s.rollnum = :rollnum"),
    @NamedQuery(name = "StaffAccount.findByPass", query = "SELECT s FROM StaffAccount s WHERE s.pass = :pass"),
    @NamedQuery(name = "StaffAccount.findByCreateat", query = "SELECT s FROM StaffAccount s WHERE s.createat = :createat"),
    @NamedQuery(name = "StaffAccount.findByUpdateat", query = "SELECT s FROM StaffAccount s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "StaffAccount.findByRemoveat", query = "SELECT s FROM StaffAccount s WHERE s.removeat = :removeat")})
public class StaffAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staffaccid", nullable = false)
    private Integer staffaccid;
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
    @JoinColumn(name = "staffid", referencedColumnName = "staffid", nullable = false)
    @ManyToOne(optional = false)
    private Staff staffid;

    public StaffAccount() {
    }

    public StaffAccount(Integer staffaccid) {
        this.staffaccid = staffaccid;
    }

    public StaffAccount(Integer staffaccid, String rollnum, String pass, String removeat) {
        this.staffaccid = staffaccid;
        this.rollnum = rollnum;
        this.pass = pass;
        this.removeat = removeat;
    }

    public Integer getStaffaccid() {
        return staffaccid;
    }

    public void setStaffaccid(Integer staffaccid) {
        this.staffaccid = staffaccid;
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

    public Staff getStaffid() {
        return staffid;
    }

    public void setStaffid(Staff staffid) {
        this.staffid = staffid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffaccid != null ? staffaccid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffAccount)) {
            return false;
        }
        StaffAccount other = (StaffAccount) object;
        if ((this.staffaccid == null && other.staffaccid != null) || (this.staffaccid != null && !this.staffaccid.equals(other.staffaccid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.StaffAccount[ staffaccid=" + staffaccid + " ]";
    }
    
}
