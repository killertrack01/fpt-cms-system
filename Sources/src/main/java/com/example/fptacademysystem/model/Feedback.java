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
@Table(name = "feedback", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFeedbackid", query = "SELECT f FROM Feedback f WHERE f.feedbackid = :feedbackid"),
    @NamedQuery(name = "Feedback.findByFeedbacknm", query = "SELECT f FROM Feedback f WHERE f.feedbacknm = :feedbacknm"),
    @NamedQuery(name = "Feedback.findByDateoffeedback", query = "SELECT f FROM Feedback f WHERE f.dateoffeedback = :dateoffeedback"),
    @NamedQuery(name = "Feedback.findByEnddatefeedback", query = "SELECT f FROM Feedback f WHERE f.enddatefeedback = :enddatefeedback"),
    @NamedQuery(name = "Feedback.findByStugroid", query = "SELECT f FROM Feedback f WHERE f.stugroid = :stugroid"),
    @NamedQuery(name = "Feedback.findByLecturid", query = "SELECT f FROM Feedback f WHERE f.lecturid = :lecturid"),
    @NamedQuery(name = "Feedback.findByFeedbackstatus", query = "SELECT f FROM Feedback f WHERE f.feedbackstatus = :feedbackstatus"),
    @NamedQuery(name = "Feedback.findByCreateat", query = "SELECT f FROM Feedback f WHERE f.createat = :createat"),
    @NamedQuery(name = "Feedback.findByUpdateat", query = "SELECT f FROM Feedback f WHERE f.updateat = :updateat"),
    @NamedQuery(name = "Feedback.findByRemoveat", query = "SELECT f FROM Feedback f WHERE f.removeat = :removeat")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedbackid", nullable = false)
    private Integer feedbackid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "feedbacknm", nullable = false, length = 255)
    private String feedbacknm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateoffeedback", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateoffeedback;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enddatefeedback", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date enddatefeedback;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stugroid", nullable = false)
    private int stugroid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lecturid", nullable = false)
    private int lecturid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "feedbackstatus", nullable = false, length = 255)
    private String feedbackstatus;
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
    @JoinColumn(name = "subjdetailsid", referencedColumnName = "subjdetailsid", nullable = false)
    @ManyToOne(optional = false)
    private SubjectDetails subjdetailsid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feedbackid")
    private Collection<StudentFeedback> studentFeedbackCollection;

    public Feedback() {
    }

    public Feedback(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Feedback(Integer feedbackid, String feedbacknm, Date dateoffeedback, Date enddatefeedback, int stugroid, int lecturid, String feedbackstatus, String removeat) {
        this.feedbackid = feedbackid;
        this.feedbacknm = feedbacknm;
        this.dateoffeedback = dateoffeedback;
        this.enddatefeedback = enddatefeedback;
        this.stugroid = stugroid;
        this.lecturid = lecturid;
        this.feedbackstatus = feedbackstatus;
        this.removeat = removeat;
    }

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getFeedbacknm() {
        return feedbacknm;
    }

    public void setFeedbacknm(String feedbacknm) {
        this.feedbacknm = feedbacknm;
    }

    public Date getDateoffeedback() {
        return dateoffeedback;
    }

    public void setDateoffeedback(Date dateoffeedback) {
        this.dateoffeedback = dateoffeedback;
    }

    public Date getEnddatefeedback() {
        return enddatefeedback;
    }

    public void setEnddatefeedback(Date enddatefeedback) {
        this.enddatefeedback = enddatefeedback;
    }

    public int getStugroid() {
        return stugroid;
    }

    public void setStugroid(int stugroid) {
        this.stugroid = stugroid;
    }

    public int getLecturid() {
        return lecturid;
    }

    public void setLecturid(int lecturid) {
        this.lecturid = lecturid;
    }

    public String getFeedbackstatus() {
        return feedbackstatus;
    }

    public void setFeedbackstatus(String feedbackstatus) {
        this.feedbackstatus = feedbackstatus;
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

    public SubjectDetails getSubjdetailsid() {
        return subjdetailsid;
    }

    public void setSubjdetailsid(SubjectDetails subjdetailsid) {
        this.subjdetailsid = subjdetailsid;
    }

    @XmlTransient
    public Collection<StudentFeedback> getStudentFeedbackCollection() {
        return studentFeedbackCollection;
    }

    public void setStudentFeedbackCollection(Collection<StudentFeedback> studentFeedbackCollection) {
        this.studentFeedbackCollection = studentFeedbackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackid != null ? feedbackid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackid == null && other.feedbackid != null) || (this.feedbackid != null && !this.feedbackid.equals(other.feedbackid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Feedback[ feedbackid=" + feedbackid + " ]";
    }
    
}
