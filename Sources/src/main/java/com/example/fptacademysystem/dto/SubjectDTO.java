
package com.example.fptacademysystem.dto;

import java.io.Serializable;

public class SubjectDTO implements Serializable{
    private int subjid;
    private String shortnm;
    private String subjnm;
    private int branchid;
    private String brandnm;
    private String removeat;

    public SubjectDTO() {
        super();
    }

    public SubjectDTO(int subjid, String shortnm, String subjnm, int branchid, String removeat) {
        super();
        this.subjid = subjid;
        this.shortnm = shortnm;
        this.subjnm = subjnm;
        this.branchid = branchid;
        this.removeat = removeat;
    }

    public int getSubjid() {
        return subjid;
    }

    public void setSubjid(int subjid) {
        this.subjid = subjid;
    }

    public String getShortnm() {
        return shortnm;
    }

    public void setShortnm(String shortnm) {
        this.shortnm = shortnm;
    }

    public String getSubjnm() {
        return subjnm;
    }

    public void setSubjnm(String subjnm) {
        this.subjnm = subjnm;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getBrandnm() {
        return brandnm;
    }

    public void setBrandnm(String brandnm) {
        this.brandnm = brandnm;
    }

    public String getRemoveat() {
        return removeat;
    }

    public void setRemoveat(String removeat) {
        this.removeat = removeat;
    }
    
}
