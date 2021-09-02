package com.example.fptacademysystem.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class LecturerDTO implements Serializable {
    private String lecturid;
    private String rollnum;
    private String fullnm;
    private String dob;
    private String idcard;
    private String gender;
    private String email;
    private String companyemail;
    private String poi;
    private String doi;
    private String phone;
    private MultipartFile file;
    private String contract;
    private String major;
    private String lecturertype;
    private String address;
    private String lecturerPass;

    public String getLecturerPass() {
        return lecturerPass;
    }

    public void setLecturerPass(String lecturerPass) {
        this.lecturerPass = lecturerPass;
    }
    
    public LecturerDTO(){}

    public String getLecturid() {
        return lecturid;
    }

    public void setLecturid(String lecturid) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

}
