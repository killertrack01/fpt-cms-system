package com.example.fptacademysystem.dto;import org.springframework.web.multipart.MultipartFile;import java.io.Serializable;public class StaffDTO implements Serializable {    private String staffid;    private String rollnum;    private String fullnm;    private String dob;    private String idcard;    private String gender;    private String email;    private String companyemail;    private String doi;    private String poi;    private String phone;    private MultipartFile img;    private String contract;    private String address;    private String depid;    private String roleid;    private String staffpass;    public String getStaffpass() {        return staffpass;    }    public void setStaffpass(String staffpass) {        this.staffpass = staffpass;    }    public StaffDTO(){}    public String getStaffid() {        return staffid;    }    public void setStaffid(String staffid) {        this.staffid = staffid;    }    public String getRollnum() {        return rollnum;    }    public void setRollnum(String rollnum) {        this.rollnum = rollnum;    }    public String getFullnm() {        return fullnm;    }    public void setFullnm(String fullnm) {        this.fullnm = fullnm;    }    public String getDob() {        return dob;    }    public void setDob(String dob) {        this.dob = dob;    }    public String getIdcard() {        return idcard;    }    public void setIdcard(String idcard) {        this.idcard = idcard;    }    public String getGender() {        return gender;    }    public void setGender(String gender) {        this.gender = gender;    }    public String getEmail() {        return email;    }    public void setEmail(String email) {        this.email = email;    }    public String getCompanyemail() {        return companyemail;    }    public void setCompanyemail(String companyemail) {        this.companyemail = companyemail;    }    public String getDoi() {        return doi;    }    public void setDoi(String doi) {        this.doi = doi;    }    public String getPoi() {        return poi;    }    public void setPoi(String poi) {        this.poi = poi;    }    public String getPhone() {        return phone;    }    public void setPhone(String phone) {        this.phone = phone;    }    public MultipartFile getImg() {        return img;    }    public void setImg(MultipartFile img) {        this.img = img;    }    public String getContract() {        return contract;    }    public void setContract(String contract) {        this.contract = contract;    }    public String getAddress() {        return address;    }    public void setAddress(String address) {        this.address = address;    }    public String getDepid() {        return depid;    }    public void setDepid(String depid) {        this.depid = depid;    }    public String getRoleid() {        return roleid;    }    public void setRoleid(String roleid) {        this.roleid = roleid;    }}