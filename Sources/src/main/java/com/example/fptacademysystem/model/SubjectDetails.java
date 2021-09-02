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
@Table(name = "subject_details", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectDetails.findAll", query = "SELECT s FROM SubjectDetails s"),
    @NamedQuery(name = "SubjectDetails.findBySubjdetailsid", query = "SELECT s FROM SubjectDetails s WHERE s.subjdetailsid = :subjdetailsid"),
    @NamedQuery(name = "SubjectDetails.findBySlots", query = "SELECT s FROM SubjectDetails s WHERE s.slots = :slots"),
    @NamedQuery(name = "SubjectDetails.findByFee", query = "SELECT s FROM SubjectDetails s WHERE s.fee = :fee"),
    @NamedQuery(name = "SubjectDetails.findByCreateat", query = "SELECT s FROM SubjectDetails s WHERE s.createat = :createat"),
    @NamedQuery(name = "SubjectDetails.findByUpdateat", query = "SELECT s FROM SubjectDetails s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "SubjectDetails.findByRemoveat", query = "SELECT s FROM SubjectDetails s WHERE s.removeat = :removeat")})
public class SubjectDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subjdetailsid", nullable = false)
    private Integer subjdetailsid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slots", nullable = false)
    private int slots;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fee", nullable = false)
    private double fee;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjdetailsid")
    private Collection<Feedback> feedbackCollection;
    @JoinColumn(name = "courid", referencedColumnName = "courid", nullable = false)
    @ManyToOne(optional = false)
    private Courses courid;
    @JoinColumn(name = "semid", referencedColumnName = "semid", nullable = false)
    @ManyToOne(optional = false)
    private Semester semid;
    @JoinColumn(name = "subjid", referencedColumnName = "subjid", nullable = false)
    @ManyToOne(optional = false)
    private Subject subjid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjdetailsid")
    private Collection<Timetable> timetableCollection;

    public SubjectDetails() {
    }

    public SubjectDetails(Integer subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
    }

    public SubjectDetails(Integer subjdetailsid, int slots, double fee, String removeat) {
        this.subjdetailsid = subjdetailsid;
        this.slots = slots;
        this.fee = fee;
        this.removeat = removeat;
    }

    public Integer getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(Integer subjdetailsid) {
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
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    public Courses getCourid() {
        return courid;
    }

    public void setCourid(Courses courid) {
        this.courid = courid;
    }

    public Semester getSemid() {
        return semid;
    }

    public void setSemid(Semester semid) {
        this.semid = semid;
    }

    public Subject getSubjid() {
        return subjid;
    }

    public void setSubjid(Subject subjid) {
        this.subjid = subjid;
    }

    @XmlTransient
    public Collection<Timetable> getTimetableCollection() {
        return timetableCollection;
    }

    public void setTimetableCollection(Collection<Timetable> timetableCollection) {
        this.timetableCollection = timetableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjdetailsid != null ? subjdetailsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectDetails)) {
            return false;
        }
        SubjectDetails other = (SubjectDetails) object;
        if ((this.subjdetailsid == null && other.subjdetailsid != null) || (this.subjdetailsid != null && !this.subjdetailsid.equals(other.subjdetailsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.SubjectDetails[ subjdetailsid=" + subjdetailsid + " ]";
    }
    
}
