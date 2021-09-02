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
@Table(name = "student_feedback", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentFeedback.findAll", query = "SELECT s FROM StudentFeedback s"),
    @NamedQuery(name = "StudentFeedback.findByStufeedbackid", query = "SELECT s FROM StudentFeedback s WHERE s.stufeedbackid = :stufeedbackid"),
    @NamedQuery(name = "StudentFeedback.findByAns1", query = "SELECT s FROM StudentFeedback s WHERE s.ans1 = :ans1"),
    @NamedQuery(name = "StudentFeedback.findByAns2", query = "SELECT s FROM StudentFeedback s WHERE s.ans2 = :ans2"),
    @NamedQuery(name = "StudentFeedback.findByAns3", query = "SELECT s FROM StudentFeedback s WHERE s.ans3 = :ans3"),
    @NamedQuery(name = "StudentFeedback.findByAns4", query = "SELECT s FROM StudentFeedback s WHERE s.ans4 = :ans4"),
    @NamedQuery(name = "StudentFeedback.findByAns5", query = "SELECT s FROM StudentFeedback s WHERE s.ans5 = :ans5"),
    @NamedQuery(name = "StudentFeedback.findByNote", query = "SELECT s FROM StudentFeedback s WHERE s.note = :note"),
    @NamedQuery(name = "StudentFeedback.findByGpafeedback", query = "SELECT s FROM StudentFeedback s WHERE s.gpafeedback = :gpafeedback"),
    @NamedQuery(name = "StudentFeedback.findByCreatat", query = "SELECT s FROM StudentFeedback s WHERE s.creatat = :creatat"),
    @NamedQuery(name = "StudentFeedback.findByUpdateat", query = "SELECT s FROM StudentFeedback s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "StudentFeedback.findByRemoveat", query = "SELECT s FROM StudentFeedback s WHERE s.removeat = :removeat")})
public class StudentFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stufeedbackid", nullable = false)
    private Integer stufeedbackid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ans1", nullable = false, length = 1000)
    private String ans1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ans2", nullable = false, length = 1000)
    private String ans2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ans3", nullable = false, length = 1000)
    private String ans3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ans4", nullable = false, length = 1000)
    private String ans4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ans5", nullable = false, length = 1000)
    private String ans5;
    @Size(max = 1000)
    @Column(name = "note", length = 1000)
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpafeedback", nullable = false)
    private double gpafeedback;
    @Column(name = "creatat")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creatat;
    @Column(name = "updateat")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;
    @JoinColumn(name = "feedbackid", referencedColumnName = "feedbackid", nullable = false)
    @ManyToOne(optional = false)
    private Feedback feedbackid;
    @JoinColumn(name = "stuid", referencedColumnName = "stuid", nullable = false)
    @ManyToOne(optional = false)
    private Student stuid;

    public StudentFeedback() {
    }

    public StudentFeedback(Integer stufeedbackid) {
        this.stufeedbackid = stufeedbackid;
    }

    public StudentFeedback(Integer stufeedbackid, String ans1, String ans2, String ans3, String ans4, String ans5, double gpafeedback, String removeat) {
        this.stufeedbackid = stufeedbackid;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.gpafeedback = gpafeedback;
        this.removeat = removeat;
    }

    public Integer getStufeedbackid() {
        return stufeedbackid;
    }

    public void setStufeedbackid(Integer stufeedbackid) {
        this.stufeedbackid = stufeedbackid;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getAns5() {
        return ans5;
    }

    public void setAns5(String ans5) {
        this.ans5 = ans5;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getGpafeedback() {
        return gpafeedback;
    }

    public void setGpafeedback(double gpafeedback) {
        this.gpafeedback = gpafeedback;
    }

    public Date getCreatat() {
        return creatat;
    }

    public void setCreatat(Date creatat) {
        this.creatat = creatat;
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

    public Feedback getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Feedback feedbackid) {
        this.feedbackid = feedbackid;
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
        hash += (stufeedbackid != null ? stufeedbackid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentFeedback)) {
            return false;
        }
        StudentFeedback other = (StudentFeedback) object;
        if ((this.stufeedbackid == null && other.stufeedbackid != null) || (this.stufeedbackid != null && !this.stufeedbackid.equals(other.stufeedbackid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.StudentFeedback[ stufeedbackid=" + stufeedbackid + " ]";
    }
    
}
