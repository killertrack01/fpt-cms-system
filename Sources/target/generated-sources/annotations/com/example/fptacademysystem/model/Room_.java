package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Campus;
import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.Timetable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, String> roomnm;
    public static volatile CollectionAttribute<Room, Timetable> timetableCollection;
    public static volatile SingularAttribute<Room, Campus> campusid;
    public static volatile SingularAttribute<Room, Date> updateat;
    public static volatile SingularAttribute<Room, String> removeat;
    public static volatile SingularAttribute<Room, Integer> roomid;
    public static volatile SingularAttribute<Room, Date> createat;
    public static volatile CollectionAttribute<Room, Exam> examCollection;

}