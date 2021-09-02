/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "subject_subjectdetails", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectSubjectdetails.findAll", query = "SELECT s FROM SubjectSubjectdetails s"),
    @NamedQuery(name = "SubjectSubjectdetails.findBySubjdetailsid", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.subjdetailsid = :subjdetailsid"),
    @NamedQuery(name = "SubjectSubjectdetails.findBySlots", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.slots = :slots"),
    @NamedQuery(name = "SubjectSubjectdetails.findByFee", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.fee = :fee"),
    @NamedQuery(name = "SubjectSubjectdetails.findBySubjid", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.subjid = :subjid"),
    @NamedQuery(name = "SubjectSubjectdetails.findBySemid", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.semid = :semid"),
    @NamedQuery(name = "SubjectSubjectdetails.findByCourid", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.courid = :courid"),
    @NamedQuery(name = "SubjectSubjectdetails.findByShortnm", query = "SELECT s FROM SubjectSubjectdetails s WHERE s.shortnm = :shortnm")})
public class SubjectSubjectdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjdetailsid", nullable = false)
    private int subjdetailsid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slots", nullable = false)
    private int slots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fee", nullable = false)
    private double fee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subjid", nullable = false)
    private int subjid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semid", nullable = false)
    private int semid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courid", nullable = false)
    private int courid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "shortnm", nullable = false, length = 255)
    private String shortnm;

    public SubjectSubjectdetails() {
    }

    public int getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(int subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getSubjid() {
        return subjid;
    }

    public void setSubjid(int subjid) {
        this.subjid = subjid;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public int getCourid() {
        return courid;
    }

    public void setCourid(int courid) {
        this.courid = courid;
    }

    public String getShortnm() {
        return shortnm;
    }

    public void setShortnm(String shortnm) {
        this.shortnm = shortnm;
    }
    
}
