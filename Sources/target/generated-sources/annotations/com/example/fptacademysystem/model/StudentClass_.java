package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.StudentGroup;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(StudentClass.class)
public class StudentClass_ { 

    public static volatile SingularAttribute<StudentClass, String> cass;
    public static volatile SingularAttribute<StudentClass, Integer> regissubj;
    public static volatile SingularAttribute<StudentClass, Integer> studclassid;
    public static volatile SingularAttribute<StudentClass, Integer> semstatus;
    public static volatile SingularAttribute<StudentClass, Student> stuid;
    public static volatile SingularAttribute<StudentClass, Date> restudytime;
    public static volatile SingularAttribute<StudentClass, Date> updateat;
    public static volatile SingularAttribute<StudentClass, String> removeat;
    public static volatile SingularAttribute<StudentClass, StudentGroup> stugroid;
    public static volatile SingularAttribute<StudentClass, Date> createat;

}