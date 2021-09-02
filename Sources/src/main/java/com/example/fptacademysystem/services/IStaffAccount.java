package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.Staff;
import com.example.fptacademysystem.model.StaffAccount;

public interface IStaffAccount {
    StaffAccount findAccountByStaffid(int staffid);
    StaffAccount findByStaffId(Staff staff);
    void postCreate(StaffAccount staffAccount);
    void updateStaffRollnum(String rollnum, int staffid);
    StaffAccount checkStaffLogin(String rollnum, String pass);
}
