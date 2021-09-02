package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Attendance;
import com.example.fptacademysystem.model.Parent;
import com.example.fptacademysystem.model.Result;
import com.example.fptacademysystem.model.StudentAccount;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.StudentFeedback;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, String> img;
    public static volatile SingularAttribute<Student, String> address;
    public static volatile SingularAttribute<Student, Integer> stuid;
    public static volatile SingularAttribute<Student, String> gender;
    public static volatile CollectionAttribute<Student, StudentClass> studentClassCollection;
    public static volatile SingularAttribute<Student, String> mobphone;
    public static volatile SingularAttribute<Student, Date> updateat;
    public static volatile SingularAttribute<Student, String> poi;
    public static volatile SingularAttribute<Student, String> stustatus;
    public static volatile SingularAttribute<Student, String> collegeemail;
    public static volatile SingularAttribute<Student, Date> createat;
    public static volatile CollectionAttribute<Student, Parent> parentCollection;
    public static volatile CollectionAttribute<Student, StudentFeedback> studentFeedbackCollection;
    public static volatile CollectionAttribute<Student, StudentAccount> studentAccountCollection;
    public static volatile SingularAttribute<Student, String> major;
    public static volatile CollectionAttribute<Student, Attendance> attendanceCollection;
    public static volatile SingularAttribute<Student, Date> dob;
    public static volatile SingularAttribute<Student, String> idcard;
    public static volatile CollectionAttribute<Student, Result> resultCollection;
    public static volatile SingularAttribute<Student, String> removeat;
    public static volatile SingularAttribute<Student, String> rollnum;
    public static volatile SingularAttribute<Student, String> email;
    public static volatile SingularAttribute<Student, String> fullnm;
    public static volatile SingularAttribute<Student, Date> doi;

}