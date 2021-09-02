package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> rolenm;
    public static volatile SingularAttribute<Role, Integer> roleid;
    public static volatile SingularAttribute<Role, Date> updateat;
    public static volatile SingularAttribute<Role, String> removeat;
    public static volatile CollectionAttribute<Role, Staff> staffCollection;
    public static volatile SingularAttribute<Role, String> descriptions;
    public static volatile SingularAttribute<Role, Date> createat;

}