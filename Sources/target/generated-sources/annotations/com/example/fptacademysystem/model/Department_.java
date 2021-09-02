package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, Date> updateat;
    public static volatile SingularAttribute<Department, String> removeat;
    public static volatile SingularAttribute<Department, Integer> depid;
    public static volatile CollectionAttribute<Department, Staff> staffCollection;
    public static volatile SingularAttribute<Department, String> descriptions;
    public static volatile SingularAttribute<Department, String> depnm;
    public static volatile SingularAttribute<Department, Date> createat;

}