package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{

    @Query(value = "select * from staff where removeat = 'No'", nativeQuery = true)
    List<Staff> findAllStaff();

    @Query(value = "select * from staff where staffid = :staffid and removeat = 'No'", nativeQuery = true)
    Staff findByStaffid(@Param("staffid")int staffid);

    @Query(value = "select * from staff where staffid = (select MAX(staffid) from staff) and removeat = 'No'", nativeQuery = true)
    Staff findNewStaff();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update staff set rollnum = :rollnum where staffid = :staffid and removeat='No'", nativeQuery = true)
    void updateStaffRollnum(@Param("rollnum")String rollnum, @Param("staffid")int staffid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update staff set removeat = 'Yes' where staffid = :staffid", nativeQuery = true)
    void deleteStaff(@Param("staffid")int staffid);

}