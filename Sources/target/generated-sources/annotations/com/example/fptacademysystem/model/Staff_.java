package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Department;
import com.example.fptacademysystem.model.Role;
import com.example.fptacademysystem.model.StaffAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Staff.class)
public class Staff_ { 

    public static volatile SingularAttribute<Staff, String> img;
    public static volatile SingularAttribute<Staff, String> address;
    public static volatile SingularAttribute<Staff, String> gender;
    public static volatile SingularAttribute<Staff, Role> roleid;
    public static volatile SingularAttribute<Staff, String> contract;
    public static volatile SingularAttribute<Staff, Date> updateat;
    public static volatile SingularAttribute<Staff, String> poi;
    public static volatile SingularAttribute<Staff, Date> createat;
    public static volatile CollectionAttribute<Staff, StaffAccount> staffAccountCollection;
    public static volatile SingularAttribute<Staff, String> companyemail;
    public static volatile SingularAttribute<Staff, String> phone;
    public static volatile SingularAttribute<Staff, Date> dob;
    public static volatile SingularAttribute<Staff, String> idcard;
    public static volatile SingularAttribute<Staff, String> removeat;
    public static volatile SingularAttribute<Staff, Department> depid;
    public static volatile SingularAttribute<Staff, String> rollnum;
    public static volatile SingularAttribute<Staff, Integer> staffid;
    public static volatile SingularAttribute<Staff, String> email;
    public static volatile SingularAttribute<Staff, String> fullnm;
    public static volatile SingularAttribute<Staff, Date> doi;

}