
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.*;

public interface IStudentAccount {
    StudentAccount findByStudentId(Student student);
    void SaveStudentAccount(StudentAccount account);
    void deleteByStudentId(Student st);
    StudentAccount checkStudentLogin(String rollnum, String pass);
}
