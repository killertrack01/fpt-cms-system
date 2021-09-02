package com.example.fptacademysystem.dto;

import java.io.Serializable;

public class BranchCampusDTO implements Serializable{
    private int branchcamid;
    private String branchcamnm;
    private String address;
    private int branchid;
    private String branchnm;
    private int campusid;
    private String campusnm;

    public BranchCampusDTO() {
        super();
    }
    public int getBranchcamid() {
        return branchcamid;
    }
    public void setBranchcamid(int branchcamid) {
        this.branchcamid = branchcamid;
    }
    public String getBranchcamnm() {
        return branchcamnm;
    }
    public void setBranchcamnm(String branchcamnm) {
        this.branchcamnm = branchcamnm;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getBranchid() {
        return branchid;
    }
    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }
    public int getCampusid() {
        return campusid;
    }
    public void setCampusid(int campusid) {
        this.campusid = campusid;
    }

    public String getBranchnm() {
        return this.branchnm;
    }

    public void setBranchnm(String branchnm) {
        this.branchnm = branchnm;
    }

    public String getCampusnm() {
        return this.campusnm;
    }

    public void setCampusnm(String campusnm) {
        this.campusnm = campusnm;
    }

}
