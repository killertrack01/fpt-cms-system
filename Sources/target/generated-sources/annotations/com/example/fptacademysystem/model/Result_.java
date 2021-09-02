package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, Integer> resultid;
    public static volatile SingularAttribute<Result, Student> stuid;
    public static volatile SingularAttribute<Result, Integer> examresults;
    public static volatile SingularAttribute<Result, Exam> examid;
    public static volatile SingularAttribute<Result, Date> updateat;
    public static volatile SingularAttribute<Result, String> removeat;
    public static volatile SingularAttribute<Result, Date> createat;

}