package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Lecturer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(LecturerAccount.class)
public class LecturerAccount_ { 

    public static volatile SingularAttribute<LecturerAccount, Integer> teaaccid;
    public static volatile SingularAttribute<LecturerAccount, String> pass;
    public static volatile SingularAttribute<LecturerAccount, Date> updateat;
    public static volatile SingularAttribute<LecturerAccount, String> removeat;
    public static volatile SingularAttribute<LecturerAccount, String> rollnum;
    public static volatile SingularAttribute<LecturerAccount, Date> createat;
    public static volatile SingularAttribute<LecturerAccount, Lecturer> lecturid;

}