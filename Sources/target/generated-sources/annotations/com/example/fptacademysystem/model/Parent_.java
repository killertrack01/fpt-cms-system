package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.ParentAccount;
import com.example.fptacademysystem.model.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Parent.class)
public class Parent_ { 

    public static volatile SingularAttribute<Parent, String> address;
    public static volatile SingularAttribute<Parent, Integer> parid;
    public static volatile SingularAttribute<Parent, Student> stuid;
    public static volatile SingularAttribute<Parent, Date> updateat;
    public static volatile SingularAttribute<Parent, String> parphone;
    public static volatile SingularAttribute<Parent, Date> createat;
    public static volatile SingularAttribute<Parent, String> paremail;
    public static volatile CollectionAttribute<Parent, ParentAccount> parentAccountCollection;
    public static volatile SingularAttribute<Parent, String> parjob;
    public static volatile SingularAttribute<Parent, String> parnm;
    public static volatile SingularAttribute<Parent, String> pow;
    public static volatile SingularAttribute<Parent, String> removeat;
    public static volatile SingularAttribute<Parent, String> rollnum;

}