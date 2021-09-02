package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Lecturer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(GpaLecturer.class)
public class GpaLecturer_ { 

    public static volatile SingularAttribute<GpaLecturer, Date> gpamonth;
    public static volatile SingularAttribute<GpaLecturer, Double> gpascore;
    public static volatile SingularAttribute<GpaLecturer, Integer> gpalecid;
    public static volatile SingularAttribute<GpaLecturer, Date> updateat;
    public static volatile SingularAttribute<GpaLecturer, String> removeat;
    public static volatile SingularAttribute<GpaLecturer, Integer> gpastudentgroup;
    public static volatile SingularAttribute<GpaLecturer, Integer> gpasubject;
    public static volatile SingularAttribute<GpaLecturer, Date> createat;
    public static volatile SingularAttribute<GpaLecturer, Lecturer> lecturid;

}