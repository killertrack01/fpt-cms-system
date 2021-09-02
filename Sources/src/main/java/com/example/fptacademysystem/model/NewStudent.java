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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "new_student", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewStudent.findAll", query = "SELECT n FROM NewStudent n"),
    @NamedQuery(name = "NewStudent.findByStuid", query = "SELECT n FROM NewStudent n WHERE n.stuid = :stuid"),
    @NamedQuery(name = "NewStudent.findByRollnum", query = "SELECT n FROM NewStudent n WHERE n.rollnum = :rollnum"),
    @NamedQuery(name = "NewStudent.findByFullnm", query = "SELECT n FROM NewStudent n WHERE n.fullnm = :fullnm"),
    @NamedQuery(name = "NewStudent.findByDob", query = "SELECT n FROM NewStudent n WHERE n.dob = :dob"),
    @NamedQuery(name = "NewStudent.findByGender", query = "SELECT n FROM NewStudent n WHERE n.gender = :gender"),
    @NamedQuery(name = "NewStudent.findByIdcard", query = "SELECT n FROM NewStudent n WHERE n.idcard = :idcard"),
    @NamedQuery(name = "NewStudent.findByDoi", query = "SELECT n FROM NewStudent n WHERE n.doi = :doi"),
    @NamedQuery(name = "NewStudent.findByPoi", query = "SELECT n FROM NewStudent n WHERE n.poi = :poi"),
    @NamedQuery(name = "NewStudent.findByMobphone", query = "SELECT n FROM NewStudent n WHERE n.mobphone = :mobphone"),
    @NamedQuery(name = "NewStudent.findByEmail", query = "SELECT n FROM NewStudent n WHERE n.email = :email"),
    @NamedQuery(name = "NewStudent.findByCollegeemail", query = "SELECT n FROM NewStudent n WHERE n.collegeemail = :collegeemail"),
    @NamedQuery(name = "NewStudent.findByImg", query = "SELECT n FROM NewStudent n WHERE n.img = :img"),
    @NamedQuery(name = "NewStudent.findByMajor", query = "SELECT n FROM NewStudent n WHERE n.major = :major"),
    @NamedQuery(name = "NewStudent.findByStustatus", query = "SELECT n FROM NewStudent n WHERE n.stustatus = :stustatus"),
    @NamedQuery(name = "NewStudent.findByAddress", query = "SELECT n FROM NewStudent n WHERE n.address = :address"),
    @NamedQuery(name = "NewStudent.findByCreateat", query = "SELECT n FROM NewStudent n WHERE n.createat = :createat"),
    @NamedQuery(name = "NewStudent.findByUpdateat", query = "SELECT n FROM NewStudent n WHERE n.updateat = :updateat"),
    @NamedQuery(name = "NewStudent.findByRemoveat", query = "SELECT n FROM NewStudent n WHERE n.removeat = :removeat")})
public class NewStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "stuid", nullable = false)
    private int stuid;
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
    private Date createat;
    @Column(name = "updateat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "removeat", nullable = false, length = 5)
    private String removeat;

    public NewStudent() {
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
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
    
}
