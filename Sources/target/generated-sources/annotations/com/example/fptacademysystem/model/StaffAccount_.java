package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(StaffAccount.class)
public class StaffAccount_ { 

    public static volatile SingularAttribute<StaffAccount, String> pass;
    public static volatile SingularAttribute<StaffAccount, Date> updateat;
    public static volatile SingularAttribute<StaffAccount, String> removeat;
    public static volatile SingularAttribute<StaffAccount, Integer> staffaccid;
    public static volatile SingularAttribute<StaffAccount, String> rollnum;
    public static volatile SingularAttribute<StaffAccount, Date> createat;
    public static volatile SingularAttribute<StaffAccount, Staff> staffid;

}