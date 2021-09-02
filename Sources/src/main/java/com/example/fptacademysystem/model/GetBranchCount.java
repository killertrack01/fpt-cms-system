/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duc Anh
 */
@Entity
@Table(name = "get_branch_count", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GetBranchCount.findAll", query = "SELECT g FROM GetBranchCount g"),
    @NamedQuery(name = "GetBranchCount.findByCourid", query = "SELECT g FROM GetBranchCount g WHERE g.courid = :courid"),
    @NamedQuery(name = "GetBranchCount.findByCournm", query = "SELECT g FROM GetBranchCount g WHERE g.cournm = :cournm"),
    @NamedQuery(name = "GetBranchCount.findByBranchname", query = "SELECT g FROM GetBranchCount g WHERE g.branchname = :branchname"),
    @NamedQuery(name = "GetBranchCount.findByFirstchar", query = "SELECT g FROM GetBranchCount g WHERE g.firstchar = :firstchar"),
    @NamedQuery(name = "GetBranchCount.findByBranchamount", query = "SELECT g FROM GetBranchCount g WHERE g.branchamount = :branchamount")})
public class GetBranchCount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "courid", nullable = false)
    private int courid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cournm", nullable = false, length = 255)
    private String cournm;
    @Size(max = 255)
    @Column(name = "branchname", length = 255)
    private String branchname;
    @Size(max = 1)
    @Column(name = "firstchar", length = 1)
    private String firstchar;
    @Column(name = "branchamount")
    private int branchamount;

    public GetBranchCount() {
    }

    public int getCourid() {
        return courid;
    }

    public void setCourid(int courid) {
        this.courid = courid;
    }

    public String getCournm() {
        return cournm;
    }

    public void setCournm(String cournm) {
        this.cournm = cournm;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getFirstchar() {
        return firstchar;
    }

    public void setFirstchar(String firstchar) {
        this.firstchar = firstchar;
    }

    public int getBranchamount() {
        return branchamount;
    }

    public void setBranchamount(int branchamount) {
        this.branchamount = branchamount;
    }
    
}
