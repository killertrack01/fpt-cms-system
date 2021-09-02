package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Parent;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(ParentAccount.class)
public class ParentAccount_ { 

    public static volatile SingularAttribute<ParentAccount, Parent> parid;
    public static volatile SingularAttribute<ParentAccount, String> pass;
    public static volatile SingularAttribute<ParentAccount, Integer> paraccid;
    public static volatile SingularAttribute<ParentAccount, Date> updateat;
    public static volatile SingularAttribute<ParentAccount, String> removeat;
    public static volatile SingularAttribute<ParentAccount, String> rollnum;
    public static volatile SingularAttribute<ParentAccount, Date> createat;

}