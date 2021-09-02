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
@Table(name = "lecturer", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"companyemail"}),
    @UniqueConstraint(columnNames = {"idcard"}),
    @UniqueConstraint(columnNames = {"phone"}),
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"rollnum"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l"),
    @NamedQuery(name = "Lecturer.findByLecturid", query = "SELECT l FROM Lecturer l WHERE l.lecturid = :lecturid"),
    @NamedQuery(name = "Lecturer.findByRollnum", query = "SELECT l FROM Lecturer l WHERE l.rollnum = :rollnum"),
    @NamedQuery(name = "Lecturer.findByFullnm", query = "SELECT l FROM Lecturer l WHERE l.fullnm = :fullnm"),
    @NamedQuery(name = "Lecturer.findByDob", query = "SELECT l FROM Lecturer l WHERE l.dob = :dob"),
    @NamedQuery(name = "Lecturer.findByIdcard", query = "SELECT l FROM Lecturer l WHERE l.idcard = :idcard"),
    @NamedQuery(name = "Lecturer.findByGender", query = "SELECT l FROM Lecturer l WHERE l.gender = :gender"),
    @NamedQuery(name = "Lecturer.findByEmail", query = "SELECT l FROM Lecturer l WHERE l.email = :email"),
    @NamedQuery(name = "Lecturer.findByCompanyemail", query = "SELECT l FROM Lecturer l WHERE l.companyemail = :companyemail"),
    @NamedQuery(name = "Lecturer.findByPoi", query = "SELECT l FROM Lecturer l WHERE l.poi = :poi"),
    @NamedQuery(name = "Lecturer.findByDoi", query = "SELECT l FROM Lecturer l WHERE l.doi = :doi"),
    @NamedQuery(name = "Lecturer.findByPhone", query = "SELECT l FROM Lecturer l WHERE l.phone = :phone"),
    @NamedQuery(name = "Lecturer.findByImg", query = "SELECT l FROM Lecturer l WHERE l.img = :img"),
    @NamedQuery(name = "Lecturer.findByContract", query = "SELECT l FROM Lecturer l WHERE l.contract = :contract"),
    @NamedQuery(name = "Lecturer.findByMajor", query = "SELECT l FROM Lecturer l WHERE l.major = :major"),
    @NamedQuery(name = "Lecturer.findByLecturertype", query = "SELECT l FROM Lecturer l WHERE l.lecturertype = :lecturertype"),
    @NamedQuery(name = "Lecturer.findByAddress", query = "SELECT l FROM Lecturer l WHERE l.address = :address"),
    @NamedQuery(name = "Lecturer.findByCreateat", query = "SELECT l FROM Lecturer l WHERE l.createat = :createat"),
    @NamedQuery(name = "Lecturer.findByUpdateat", query = "SELECT l FROM Lecturer l WHERE l.updateat = :updateat"),
    @NamedQuery(name = "Lecturer.findByRemoveat", query = "SELECT l FROM Lecturer l WHERE l.removeat = :removeat")})
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lecturid", nullable = false)
    private Integer lecturid;
    @Size(max = 255)
    @Column(name = "rollnum", length = 255)
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
    @Size(min = 1, max = 20)
    @Column(name = "idcard", nullable = false, length = 20)
    private String idcard;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Size(max = 255)
    @Column(name = "companyemail", length = 255)
    private String companyemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "poi", nullable = false, length = 255)
    private String poi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doi", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date doi;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;
    @Size(max = 1000)
    @Column(name = "img", length = 1000)
    private String img;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contract", nullable = false, length = 255)
    private String contract;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "major", nullable = false, length = 255)
    private String major;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lecturertype", nullable = false, length = 255)
    private String lecturertype;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturid")
    private Collection<GpaLecturer> gpaLecturerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturid")
    private Collection<LecturerAccount> lecturerAccountCollection;

    public Lecturer() {
    }

    public Lecturer(Integer lecturid) {
        this.lecturid = lecturid;
    }

    public Lecturer(Integer lecturid, String fullnm, Date dob, String idcard, String gender, String email, String poi, Date doi, String phone, String contract, String major, String lecturertype, String address, String removeat) {
        this.lecturid = lecturid;
        this.fullnm = fullnm;
        this.dob = dob;
        this.idcard = idcard;
        this.gender = gender;
        this.email = email;
        this.poi = poi;
        this.doi = doi;
        this.phone = phone;
        this.contract = contract;
        this.major = major;
        this.lecturertype = lecturertype;
        this.address = address;
        this.removeat = removeat;
    }

    public Integer getLecturid() {
        return lecturid;
    }

    public void setLecturid(Integer lecturid) {
        this.lecturid = lecturid;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public Date getDoi() {
        return doi;
    }

    public void setDoi(Date doi) {
        this.doi = doi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLecturertype() {
        return lecturertype;
    }

    public void setLecturertype(String lecturertype) {
        this.lecturertype = lecturertype;
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
    public Collection<GpaLecturer> getGpaLecturerCollection() {
        return gpaLecturerCollection;
    }

    public void setGpaLecturerCollection(Collection<GpaLecturer> gpaLecturerCollection) {
        this.gpaLecturerCollection = gpaLecturerCollection;
    }

    @XmlTransient
    public Collection<LecturerAccount> getLecturerAccountCollection() {
        return lecturerAccountCollection;
    }

    public void setLecturerAccountCollection(Collection<LecturerAccount> lecturerAccountCollection) {
        this.lecturerAccountCollection = lecturerAccountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lecturid != null ? lecturid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lecturer)) {
            return false;
        }
        Lecturer other = (Lecturer) object;
        if ((this.lecturid == null && other.lecturid != null) || (this.lecturid != null && !this.lecturid.equals(other.lecturid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Lecturer[ lecturid=" + lecturid + " ]";
    }
    
}
