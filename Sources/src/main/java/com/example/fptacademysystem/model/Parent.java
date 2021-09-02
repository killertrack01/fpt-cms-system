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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "parent", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rollnum"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parent.findAll", query = "SELECT p FROM Parent p"),
    @NamedQuery(name = "Parent.findByParid", query = "SELECT p FROM Parent p WHERE p.parid = :parid"),
    @NamedQuery(name = "Parent.findByRollnum", query = "SELECT p FROM Parent p WHERE p.rollnum = :rollnum"),
    @NamedQuery(name = "Parent.findByParnm", query = "SELECT p FROM Parent p WHERE p.parnm = :parnm"),
    @NamedQuery(name = "Parent.findByParphone", query = "SELECT p FROM Parent p WHERE p.parphone = :parphone"),
    @NamedQuery(name = "Parent.findByParjob", query = "SELECT p FROM Parent p WHERE p.parjob = :parjob"),
    @NamedQuery(name = "Parent.findByPow", query = "SELECT p FROM Parent p WHERE p.pow = :pow"),
    @NamedQuery(name = "Parent.findByParemail", query = "SELECT p FROM Parent p WHERE p.paremail = :paremail"),
    @NamedQuery(name = "Parent.findByAddress", query = "SELECT p FROM Parent p WHERE p.address = :address"),
    @NamedQuery(name = "Parent.findByCreateat", query = "SELECT p FROM Parent p WHERE p.createat = :createat"),
    @NamedQuery(name = "Parent.findByUpdateat", query = "SELECT p FROM Parent p WHERE p.updateat = :updateat"),
    @NamedQuery(name = "Parent.findByRemoveat", query = "SELECT p FROM Parent p WHERE p.removeat = :removeat")})
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parid", nullable = false)
    private Integer parid;
    @Size(max = 255)
    @Column(name = "rollnum", length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "parnm", nullable = false, length = 255)
    private String parnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "parphone", nullable = false, length = 12)
    private String parphone;
    @Size(max = 255)
    @Column(name = "parjob", length = 255)
    private String parjob;
    @Size(max = 255)
    @Column(name = "pow", length = 255)
    private String pow;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "paremail", nullable = false, length = 255)
    private String paremail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false, length = 255)
    private String address;
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
    @JoinColumn(name = "stuid", referencedColumnName = "stuid", nullable = false)
    @ManyToOne(optional = false)
    private Student stuid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parid")
    private Collection<ParentAccount> parentAccountCollection;

    public Parent() {
    }

    public Parent(Integer parid) {
        this.parid = parid;
    }

    public Parent(Integer parid, String parnm, String parphone, String paremail, String address, String removeat) {
        this.parid = parid;
        this.parnm = parnm;
        this.parphone = parphone;
        this.paremail = paremail;
        this.address = address;
        this.removeat = removeat;
    }

    public Integer getParid() {
        return parid;
    }

    public void setParid(Integer parid) {
        this.parid = parid;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getParnm() {
        return parnm;
    }

    public void setParnm(String parnm) {
        this.parnm = parnm;
    }

    public String getParphone() {
        return parphone;
    }

    public void setParphone(String parphone) {
        this.parphone = parphone;
    }

    public String getParjob() {
        return parjob;
    }

    public void setParjob(String parjob) {
        this.parjob = parjob;
    }

    public String getPow() {
        return pow;
    }

    public void setPow(String pow) {
        this.pow = pow;
    }

    public String getParemail() {
        return paremail;
    }

    public void setParemail(String paremail) {
        this.paremail = paremail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Student getStuid() {
        return stuid;
    }

    public void setStuid(Student stuid) {
        this.stuid = stuid;
    }

    @XmlTransient
    public Collection<ParentAccount> getParentAccountCollection() {
        return parentAccountCollection;
    }

    public void setParentAccountCollection(Collection<ParentAccount> parentAccountCollection) {
        this.parentAccountCollection = parentAccountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parid != null ? parid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parent)) {
            return false;
        }
        Parent other = (Parent) object;
        if ((this.parid == null && other.parid != null) || (this.parid != null && !this.parid.equals(other.parid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Parent[ parid=" + parid + " ]";
    }
    
}
