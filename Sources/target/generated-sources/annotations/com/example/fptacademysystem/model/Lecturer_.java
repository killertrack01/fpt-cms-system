package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.GpaLecturer;
import com.example.fptacademysystem.model.LecturerAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Lecturer.class)
public class Lecturer_ { 

    public static volatile SingularAttribute<Lecturer, String> img;
    public static volatile SingularAttribute<Lecturer, String> address;
    public static volatile SingularAttribute<Lecturer, String> gender;
    public static volatile SingularAttribute<Lecturer, String> contract;
    public static volatile SingularAttribute<Lecturer, Date> updateat;
    public static volatile SingularAttribute<Lecturer, String> poi;
    public static volatile CollectionAttribute<Lecturer, LecturerAccount> lecturerAccountCollection;
    public static volatile SingularAttribute<Lecturer, Date> createat;
    public static volatile SingularAttribute<Lecturer, String> companyemail;
    public static volatile CollectionAttribute<Lecturer, GpaLecturer> gpaLecturerCollection;
    public static volatile SingularAttribute<Lecturer, String> major;
    public static volatile SingularAttribute<Lecturer, String> phone;
    public static volatile SingularAttribute<Lecturer, String> lecturertype;
    public static volatile SingularAttribute<Lecturer, Date> dob;
    public static volatile SingularAttribute<Lecturer, String> idcard;
    public static volatile SingularAttribute<Lecturer, String> removeat;
    public static volatile SingularAttribute<Lecturer, String> rollnum;
    public static volatile SingularAttribute<Lecturer, String> email;
    public static volatile SingularAttribute<Lecturer, Integer> lecturid;
    public static volatile SingularAttribute<Lecturer, String> fullnm;
    public static volatile SingularAttribute<Lecturer, Date> doi;

}