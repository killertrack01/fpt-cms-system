/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.model;

import java.io.Serializable;
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
@Table(name = "render_location", catalog = "fptacademysys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenderLocation.findAll", query = "SELECT r FROM RenderLocation r"),
    @NamedQuery(name = "RenderLocation.findBySlid", query = "SELECT r FROM RenderLocation r WHERE r.slid = :slid"),
    @NamedQuery(name = "RenderLocation.findByBranchid", query = "SELECT r FROM RenderLocation r WHERE r.branchid = :branchid"),
    @NamedQuery(name = "RenderLocation.findByBranchnm", query = "SELECT r FROM RenderLocation r WHERE r.branchnm = :branchnm"),
    @NamedQuery(name = "RenderLocation.findByStugronm", query = "SELECT r FROM RenderLocation r WHERE r.stugronm = :stugronm"),
    @NamedQuery(name = "RenderLocation.findBySemid", query = "SELECT r FROM RenderLocation r WHERE r.semid = :semid"),
    @NamedQuery(name = "RenderLocation.findByStudytime", query = "SELECT r FROM RenderLocation r WHERE r.studytime = :studytime"),
    @NamedQuery(name = "RenderLocation.findByCoursesnm", query = "SELECT r FROM RenderLocation r WHERE r.coursesnm = :coursesnm"),
    @NamedQuery(name = "RenderLocation.findByMaintea", query = "SELECT r FROM RenderLocation r WHERE r.maintea = :maintea"),
    @NamedQuery(name = "RenderLocation.findByBranchcamid", query = "SELECT r FROM RenderLocation r WHERE r.branchcamid = :branchcamid"),
    @NamedQuery(name = "RenderLocation.findByRoomid", query = "SELECT r FROM RenderLocation r WHERE r.roomid = :roomid"),
    @NamedQuery(name = "RenderLocation.findByMaxsem", query = "SELECT r FROM RenderLocation r WHERE r.maxsem = :maxsem")})
public class RenderLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "slid", nullable = false)
    private long slid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchid", nullable = false)
    private int branchid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "branchnm", nullable = false, length = 255)
    private String branchnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stugronm", nullable = false, length = 255)
    private String stugronm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semid", nullable = false)
    private int semid;
    @Size(max = 24)
    @Column(name = "studytime", length = 24)
    private String studytime;
    @Size(max = 255)
    @Column(name = "coursesnm", length = 255)
    private String coursesnm;
    @Size(max = 255)
    @Column(name = "maintea", length = 255)
    private String maintea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchcamid", nullable = false)
    private int branchcamid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roomid", nullable = false)
    private int roomid;
    @Column(name = "maxsem")
    private Integer maxsem;

    public RenderLocation() {
    }

    public long getSlid() {
        return slid;
    }

    public void setSlid(long slid) {
        this.slid = slid;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getBranchnm() {
        return branchnm;
    }

    public void setBranchnm(String branchnm) {
        this.branchnm = branchnm;
    }

    public String getStugronm() {
        return stugronm;
    }

    public void setStugronm(String stugronm) {
        this.stugronm = stugronm;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public String getStudytime() {
        return studytime;
    }

    public void setStudytime(String studytime) {
        this.studytime = studytime;
    }

    public String getCoursesnm() {
        return coursesnm;
    }

    public void setCoursesnm(String coursesnm) {
        this.coursesnm = coursesnm;
    }

    public String getMaintea() {
        return maintea;
    }

    public void setMaintea(String maintea) {
        this.maintea = maintea;
    }

    public int getBranchcamid() {
        return branchcamid;
    }

    public void setBranchcamid(int branchcamid) {
        this.branchcamid = branchcamid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public Integer getMaxsem() {
        return maxsem;
    }

    public void setMaxsem(Integer maxsem) {
        this.maxsem = maxsem;
    }
    
}
