
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.*;
import com.example.fptacademysystem.repository.ParentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParentAccountService implements IParentAccount{
    
    @Autowired
    ParentAccountRepository repository;

    @Override
    public ParentAccount findByParentId(Parent parent) {
        return repository.findParentAccountByStudentId(parent);
    }

    @Override
    public void SaveParentAccount(ParentAccount account) {
       repository.save(account);
    }

    @Override
    public void deleteByParentId(Parent pa) {
        repository.deleteParentAccountByParentid(pa);
    }

    @Override
    public ParentAccount checkpaLogin(String rollnum, String pass) {
        return repository.checkLogin(rollnum, pass);
    }
    
}
