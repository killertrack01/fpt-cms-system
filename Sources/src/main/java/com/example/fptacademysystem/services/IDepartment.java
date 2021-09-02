package com.example.fptacademysystem.services;

import java.util.List;

import com.example.fptacademysystem.model.Department;

public interface IDepartment {
    
    List<Department> Findall();

    void create(Department depart);

    Department FindOne(int Id);

    void update(Department depart);

    void delete(int Id);
}
