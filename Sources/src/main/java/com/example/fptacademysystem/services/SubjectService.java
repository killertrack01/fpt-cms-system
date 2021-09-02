package com.example.fptacademysystem.services;

import java.util.List;

import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements ISubject {

    @Autowired
    SubjectRepository repository;
    
    @Override
    public List<Subject> Findall() {
        return repository.findall();
    }

    @Override
    public void create(Subject s) {
        repository.save(s);
    }
    
    @Override
    public Subject FindOne(int Id) {
        return repository.findById(Id).get();
    }
    
    @Override
    public void update(Subject s) {
        repository.save(s);
    }

    @Override
    public void delete(int Id) {
        repository.deleteid(Id);
    }
    
}
