package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.Staff;
import com.example.fptacademysystem.model.StaffAccount;
import com.example.fptacademysystem.repository.StaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffAccountService implements IStaffAccount {

    @Autowired
    StaffAccountRepository saRepo;

    @Override
    public void postCreate(StaffAccount staffAccount) {
        saRepo.save(staffAccount);
    }

    @Override
    public StaffAccount findAccountByStaffid(int staffid) {
        return saRepo.findAccountByStaffid(staffid);
    }

    @Override
    public StaffAccount findByStaffId(Staff staff) {
        return saRepo.findPStaffAccountByStaffId(staff);
    }

    @Override
    public void updateStaffRollnum(String rollnum, int staffid) {
        saRepo.updateStaffRollnum(rollnum, staffid);
    }

    @Override
    public StaffAccount checkStaffLogin(String rollnum, String pass) {
        return saRepo.checkStafftLogin(rollnum, pass);
    }


}
