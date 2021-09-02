package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Result;
import com.example.fptacademysystem.model.Room;
import com.example.fptacademysystem.model.StudentGroup;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Exam.class)
public class Exam_ { 

    public static volatile SingularAttribute<Exam, Integer> bout;
    public static volatile SingularAttribute<Exam, String> examtype;
    public static volatile SingularAttribute<Exam, Date> updateat;
    public static volatile SingularAttribute<Exam, StudentGroup> stugroid;
    public static volatile SingularAttribute<Exam, Date> createat;
    public static volatile SingularAttribute<Exam, Room> roomid;
    public static volatile SingularAttribute<Exam, String> examtime;
    public static volatile SingularAttribute<Exam, Integer> subjdetailsid;
    public static volatile SingularAttribute<Exam, String> examcodelogin;
    public static volatile SingularAttribute<Exam, Integer> examid;
    public static volatile CollectionAttribute<Exam, Result> resultCollection;
    public static volatile SingularAttribute<Exam, Date> examday;
    public static volatile SingularAttribute<Exam, String> removeat;

}