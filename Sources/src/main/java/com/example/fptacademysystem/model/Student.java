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
@Table(name = "student", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"mobphone"}),
    @UniqueConstraint(columnNames = {"idcard"}),
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"rollnum"}),
    @UniqueConstraint(columnNames = {"collegeemail"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByStuid", query = "SELECT s FROM Student s WHERE s.stuid = :stuid"),
    @NamedQuery(name = "Student.findByRollnum", query = "SELECT s FROM Student s WHERE s.rollnum = :rollnum"),
    @NamedQuery(name = "Student.findByFullnm", query = "SELECT s FROM Student s WHERE s.fullnm = :fullnm"),
    @NamedQuery(name = "Student.findByDob", query = "SELECT s FROM Student s WHERE s.dob = :dob"),
    @NamedQuery(name = "Student.findByGender", query = "SELECT s FROM Student s WHERE s.gender = :gender"),
    @NamedQuery(name = "Student.findByIdcard", query = "SELECT s FROM Student s WHERE s.idcard = :idcard"),
    @NamedQuery(name = "Student.findByDoi", query = "SELECT s FROM Student s WHERE s.doi = :doi"),
    @NamedQuery(name = "Student.findByPoi", query = "SELECT s FROM Student s WHERE s.poi = :poi"),
    @NamedQuery(name = "Student.findByMobphone", query = "SELECT s FROM Student s WHERE s.mobphone = :mobphone"),
    @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),
    @NamedQuery(name = "Student.findByCollegeemail", query = "SELECT s FROM Student s WHERE s.collegeemail = :collegeemail"),
    @NamedQuery(name = "Student.findByImg", query = "SELECT s FROM Student s WHERE s.img = :img"),
    @NamedQuery(name = "Student.findByMajor", query = "SELECT s FROM Student s WHERE s.major = :major"),
    @NamedQuery(name = "Student.findByStustatus", query = "SELECT s FROM Student s WHERE s.stustatus = :stustatus"),
    @NamedQuery(name = "Student.findByAddress", query = "SELECT s FROM Student s WHERE s.address = :address"),
    @NamedQuery(name = "Student.findByCreateat", query = "SELECT s FROM Student s WHERE s.createat = :createat"),
    @NamedQuery(name = "Student.findByUpdateat", query = "SELECT s FROM Student s WHERE s.updateat = :updateat"),
    @NamedQuery(name = "Student.findByRemoveat", query = "SELECT s FROM Student s WHERE s.removeat = :removeat")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stuid", nullable = false)
    private Integer stuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rollnum", nullable = false, length = 255)
    private String rollnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullnm", nullable = false, length = 255)
    private String fullnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "gender", nullable = false, length = 255)
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idcard", nullable = false, length = 20)
    private String idcard;
    @Column(name = "doi")
    @Temporal(TemporalType.DATE)
    private Date doi;
    @Size(max = 255)
    @Column(name = "poi", length = 255)
    private String poi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "mobphone", nullable = false, length = 12)
    private String mobphone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Size(max = 255)
    @Column(name = "collegeemail", length = 255)
    private String collegeemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "img", nullable = false, length = 1000)
    private String img;
    @Size(max = 255)
    @Column(name = "major", length = 255)
    private String major;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "stustatus", nullable = false, length = 30)
    private String stustatus;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stuid")
    private Collection<Parent> parentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stuid")
    private Collection<Result> resultCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stuid")
    private Collection<StudentClass> studentClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stuid")
    private Collection<StudentFeedback> studentFeedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stuid")
    private Collection<StudentAccount> studentAccountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stuid")
    private Collection<Attendance> attendanceCollection;

    public Student() {
    }

    public Student(Integer stuid) {
        this.stuid = stuid;
    }

    public Student(Integer stuid, String rollnum, String fullnm, Date dob, String gender, String idcard, String mobphone, String email, String img, String stustatus, String address, String removeat) {
        this.stuid = stuid;
        this.rollnum = rollnum;
        this.fullnm = fullnm;
        this.dob = dob;
        this.gender = gender;
        this.idcard = idcard;
        this.mobphone = mobphone;
        this.email = email;
        this.img = img;
        this.stustatus = stustatus;
        this.address = address;
        this.removeat = removeat;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getFullnm() {
        return fullnm;
    }

    public void setFullnm(String fullnm) {
        this.fullnm = fullnm;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getDoi() {
        return doi;
    }

    public void setDoi(Date doi) {
        this.doi = doi;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public String getMobphone() {
        return mobphone;
    }

    public void setMobphone(String mobphone) {
        this.mobphone = mobphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollegeemail() {
        return collegeemail;
    }

    public void setCollegeemail(String collegeemail) {
        this.collegeemail = collegeemail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStustatus() {
        return stustatus;
    }

    public void setStustatus(String stustatus) {
        this.stustatus = stustatus;
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

    @XmlTransient
    public Collection<Parent> getParentCollection() {
        return parentCollection;
    }

    public void setParentCollection(Collection<Parent> parentCollection) {
        this.parentCollection = parentCollection;
    }

    @XmlTransient
    public Collection<Result> getResultCollection() {
        return resultCollection;
    }

    public void setResultCollection(Collection<Result> resultCollection) {
        this.resultCollection = resultCollection;
    }

    @XmlTransient
    public Collection<StudentClass> getStudentClassCollection() {
        return studentClassCollection;
    }

    public void setStudentClassCollection(Collection<StudentClass> studentClassCollection) {
        this.studentClassCollection = studentClassCollection;
    }

    @XmlTransient
    public Collection<StudentFeedback> getStudentFeedbackCollection() {
        return studentFeedbackCollection;
    }

    public void setStudentFeedbackCollection(Collection<StudentFeedback> studentFeedbackCollection) {
        this.studentFeedbackCollection = studentFeedbackCollection;
    }

    @XmlTransient
    public Collection<StudentAccount> getStudentAccountCollection() {
        return studentAccountCollection;
    }

    public void setStudentAccountCollection(Collection<StudentAccount> studentAccountCollection) {
        this.studentAccountCollection = studentAccountCollection;
    }

    @XmlTransient
    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stuid != null ? stuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.stuid == null && other.stuid != null) || (this.stuid != null && !this.stuid.equals(other.stuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Student[ stuid=" + stuid + " ]";
    }
    
}
