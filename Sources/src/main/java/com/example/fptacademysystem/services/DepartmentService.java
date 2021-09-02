package com.example.fptacademysystem.services;

import java.util.List;

import com.example.fptacademysystem.model.Department;
import com.example.fptacademysystem.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartment {

    @Autowired
    DepartmentRepository repository;
    
    @Override
    public List<Department> Findall() {
        return repository.findall();
    }

    @Override
    public void create(Department depart) {
        repository.save(depart);
    }

    @Override
    public Department FindOne(int id) {
       return repository.findById(id).get();
    }

    @Override
    public void update(Department depart) {
        repository.save(depart);
    }

    @Override
    public void delete(int id) {
        repository.deleteid(id);
    }
}
