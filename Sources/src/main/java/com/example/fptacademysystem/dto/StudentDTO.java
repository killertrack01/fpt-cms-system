package com.example.fptacademysystem.dto;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class StudentDTO implements Serializable {
    private int stuid;
    private int asmResultId;
    private int objResultId;
    private int asmResult;
    private int objResult;
    private String rollnum;
    private String fullnm;
    private String dob;
    private String gender;
    private String idcard;
    private String doi;
    private String poi;
    private String mobphone;
    private String email;
    private String collegeemail;
    private String major;
    private String stustatus;
    private String address;
    private int classid;
    private String studentPass;
    private MultipartFile img;
    private String studentavatarname;
    private String parnm;
    private String parrollnum;
    private String parphone;
    private String parjob;
    private String pow;
    private String paremail;
    private String paddress;
    private String parentPass;

    public StudentDTO() {
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public int getAsmResultId() {
        return this.asmResultId;
    }

    public void setAsmResultId(int asmResultId) {
        this.asmResultId = asmResultId;
    }

    public int getObjResultId() {
        return this.objResultId;
    }

    public void setObjResultId(int objResultId) {
        this.objResultId = objResultId;
    }

    public int getAsmResult() {
        return this.asmResult;
    }

    public void setAsmResult(int asmResult) {
        this.asmResult = asmResult;
    }

    public int getObjResult() {
        return this.objResult;
    }

    public void setObjResult(int objResult) {
        this.objResult = objResult;
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

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
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

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getStudentPass() {
        return studentPass;
    }

    public void setStudentPass(String studentPass) {
        this.studentPass = studentPass;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getStudentavatarname() {
        return studentavatarname;
    }

    public void setStudentavatarname(String studentavatarname) {
        this.studentavatarname = studentavatarname;
    }

    public String getParnm() {
        return parnm;
    }

    public void setParnm(String parnm) {
        this.parnm = parnm;
    }

    public String getParrollnum() {
        return parrollnum;
    }

    public void setParrollnum(String parrollnum) {
        this.parrollnum = parrollnum;
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

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getParentPass() {
        return parentPass;
    }

    public void setParentPass(String parentPass) {
        this.parentPass = parentPass;
    }

}
