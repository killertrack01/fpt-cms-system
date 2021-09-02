package com.example.fptacademysystem.services;


import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.LecturerAccount;

public interface ILecturerAccount {

    void postCreate(LecturerAccount account);
    LecturerAccount findAccount(int lecturid);
    LecturerAccount checkLogin(String rollnum, String pass);
    LecturerAccount findByLecturerId(Lecturer lecturer);
}
