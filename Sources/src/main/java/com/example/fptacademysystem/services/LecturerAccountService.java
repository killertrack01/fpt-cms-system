package com.example.fptacademysystem.services;


import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.LecturerAccount;
import com.example.fptacademysystem.repository.LecturerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerAccountService implements ILecturerAccount {

    @Autowired
    LecturerAccountRepository repo;

    @Override
    public void postCreate(LecturerAccount account) {
        repo.save(account);
    }

    @Override
    public LecturerAccount findAccount(int lecturid) {
        LecturerAccount account = repo.findAccount(lecturid);
        return account;
    }

    @Override
    public LecturerAccount checkLogin(String rollnum, String pass) {  
        return repo.checkLogin(rollnum, pass);
    }

    @Override
    public LecturerAccount findByLecturerId(Lecturer lecturer) {
        return repo.findPLecturerAccountByLecturerId(lecturer);
    }

}
