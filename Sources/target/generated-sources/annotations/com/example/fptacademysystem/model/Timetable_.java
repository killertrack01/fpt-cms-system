package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Attendance;
import com.example.fptacademysystem.model.Room;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.SubjectDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Timetable.class)
public class Timetable_ { 

    public static volatile SingularAttribute<Timetable, String> slotofsubjdate;
    public static volatile SingularAttribute<Timetable, String> note;
    public static volatile SingularAttribute<Timetable, Date> subjdate;
    public static volatile SingularAttribute<Timetable, Date> updateat;
    public static volatile SingularAttribute<Timetable, StudentGroup> stugroid;
    public static volatile SingularAttribute<Timetable, Date> createat;
    public static volatile SingularAttribute<Timetable, Room> roomid;
    public static volatile SingularAttribute<Timetable, Integer> semid;
    public static volatile SingularAttribute<Timetable, Boolean> attenedit;
    public static volatile SingularAttribute<Timetable, SubjectDetails> subjdetailsid;
    public static volatile CollectionAttribute<Timetable, Attendance> attendanceCollection;
    public static volatile SingularAttribute<Timetable, String> subjnm;
    public static volatile SingularAttribute<Timetable, Integer> timetableid;
    public static volatile SingularAttribute<Timetable, String> removeat;
    public static volatile SingularAttribute<Timetable, Integer> attenteaid;

}