package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(StudentAccount.class)
public class StudentAccount_ { 

    public static volatile SingularAttribute<StudentAccount, Student> stuid;
    public static volatile SingularAttribute<StudentAccount, String> pass;
    public static volatile SingularAttribute<StudentAccount, Date> updateat;
    public static volatile SingularAttribute<StudentAccount, String> removeat;
    public static volatile SingularAttribute<StudentAccount, String> rollnum;
    public static volatile SingularAttribute<StudentAccount, Integer> stuaccid;
    public static volatile SingularAttribute<StudentAccount, Date> createat;

}