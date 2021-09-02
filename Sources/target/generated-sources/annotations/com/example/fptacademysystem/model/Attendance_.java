package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Student;
import com.example.fptacademysystem.model.Timetable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Attendance.class)
public class Attendance_ { 

    public static volatile SingularAttribute<Attendance, String> note;
    public static volatile SingularAttribute<Attendance, Student> stuid;
    public static volatile SingularAttribute<Attendance, Integer> attenid;
    public static volatile SingularAttribute<Attendance, Timetable> timetableid;
    public static volatile SingularAttribute<Attendance, Date> updateat;
    public static volatile SingularAttribute<Attendance, String> removeat;
    public static volatile SingularAttribute<Attendance, Boolean> present;
    public static volatile SingularAttribute<Attendance, Date> createat;

}