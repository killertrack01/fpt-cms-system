package com.example.fptacademysystem.services;

import java.util.List;
import com.example.fptacademysystem.model.Subject;

public interface ISubject {

    List<Subject> Findall();
    void create(Subject s);
    Subject FindOne(int Id);
    void update(Subject s);
    void delete(int Id);
    
}
