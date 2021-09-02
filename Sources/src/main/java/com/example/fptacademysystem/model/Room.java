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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "room", catalog = "fptacademysys", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"roomnm"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByRoomid", query = "SELECT r FROM Room r WHERE r.roomid = :roomid"),
    @NamedQuery(name = "Room.findByRoomnm", query = "SELECT r FROM Room r WHERE r.roomnm = :roomnm"),
    @NamedQuery(name = "Room.findByCreateat", query = "SELECT r FROM Room r WHERE r.createat = :createat"),
    @NamedQuery(name = "Room.findByUpdateat", query = "SELECT r FROM Room r WHERE r.updateat = :updateat"),
    @NamedQuery(name = "Room.findByRemoveat", query = "SELECT r FROM Room r WHERE r.removeat = :removeat")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomid", nullable = false)
    private Integer roomid;
    @Size(max = 255)
    @Column(name = "roomnm", length = 255)
    private String roomnm;
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
    @JoinColumn(name = "campusid", referencedColumnName = "campusid")
    @ManyToOne
    private Campus campusid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomid")
    private Collection<Timetable> timetableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomid")
    private Collection<Exam> examCollection;

    public Room() {
    }

    public Room(Integer roomid) {
        this.roomid = roomid;
    }

    public Room(Integer roomid, String removeat) {
        this.roomid = roomid;
        this.removeat = removeat;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getRoomnm() {
        return roomnm;
    }

    public void setRoomnm(String roomnm) {
        this.roomnm = roomnm;
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

    public Campus getCampusid() {
        return campusid;
    }

    public void setCampusid(Campus campusid) {
        this.campusid = campusid;
    }

    @XmlTransient
    public Collection<Timetable> getTimetableCollection() {
        return timetableCollection;
    }

    public void setTimetableCollection(Collection<Timetable> timetableCollection) {
        this.timetableCollection = timetableCollection;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomid != null ? roomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomid == null && other.roomid != null) || (this.roomid != null && !this.roomid.equals(other.roomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.fptacademysystem.model.Room[ roomid=" + roomid + " ]";
    }
    
}
