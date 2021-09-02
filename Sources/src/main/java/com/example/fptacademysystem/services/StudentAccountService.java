
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.StudentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentAccountService implements IStudentAccount{

    @Autowired
    StudentAccountRepository repository;
    
    @Override
    public StudentAccount findByStudentId(Student student) {
        return repository.findPStudentAccountByStudentId(student);
    }

    @Override
    public void SaveStudentAccount(StudentAccount account) {
        repository.save(account);
    }

    @Override
    public void deleteByStudentId(Student st) {
        repository.deleteStudentAccountByStudentid(st);
    }

    @Override
    public StudentAccount checkStudentLogin(String rollnum, String pass) {
        return repository.checkStudentLogin(rollnum, pass);
    }

}
