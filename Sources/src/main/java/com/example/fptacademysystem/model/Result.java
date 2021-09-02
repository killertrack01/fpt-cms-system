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
@Table(name = "result", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Result.findAll", query = "SELECT r FROM Result r"),
    @NamedQuery(name = "Result.findByResultid", query = "SELECT r FROM Result r WHERE r.resultid = :resultid"),
    @NamedQuery(name = "Result.findByExamresults", query = "SELECT r FROM Result r WHERE r.examresults = :examresults"),
    @NamedQuery(name = "Result.findByCreateat", query = "SELECT r FROM Result r WHERE r.createat = :createat"),
    @NamedQuery(name = "Result.findByUpdateat", query = "SELECT r FROM Result r WHERE r.updateat = :updateat"),
    @NamedQuery(name = "Result.findByRemoveat", query = "SELECT r FROM Result r WHERE r.removeat = :removeat")})
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resultid", nullable = false)
    private Integer resultid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "examresults", nullable = false)
    private int examresults;
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
    @JoinColumn(name = "examid", referencedColumnName = "examid", nullable = false)
    @ManyToOne(optional = false)
    private Exam examid;
    @JoinColumn(name = "stuid", referencedColumnName = "stuid", nullable = false)
    @ManyToOne(optional = false)
    private Student stuid;

    public Result() {
    }

    public Result(Integer resultid) {
        this.resultid = resultid;
    }

    public Result(Integer resultid, int examresults, String removeat) {
        this.resultid = resultid;
        this.examresults = examresults;
        this.removeat = removeat;
    }

    public Integer getResultid() {
        return resultid;
    }

    public void setResultid(Integer resultid) {
        this.resultid = resultid;
    }

    public int getExamresults() {
        return examresults;
    }

    public void setExamresults(int examresults) {
        this.examresults = examresults;
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

    public Exam getExamid() {
        return examid;
    }

    public void setExamid(Exam examid) {
        this.examid = examid;
    }

    public Student getStuid() {
        return stuid;
    }

    public void setStuid(Student stuid) {
        this.stuid = stuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resultid != null ? resultid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Result)) {
            return false;
        }
        Result other = (Result) object;
        if ((this.resultid == null && other.resultid != null) || (this.resultid != null && !this.resultid.equals(other.resultid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Result[ resultid=" + resultid + " ]";
    }
    
}
