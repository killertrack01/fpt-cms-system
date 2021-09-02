package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Room;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Campus.class)
public class Campus_ { 

    public static volatile CollectionAttribute<Campus, BranchCampus> branchCampusCollection;
    public static volatile SingularAttribute<Campus, Integer> campusid;
    public static volatile CollectionAttribute<Campus, Room> roomCollection;
    public static volatile SingularAttribute<Campus, Date> updateat;
    public static volatile SingularAttribute<Campus, String> removeat;
    public static volatile SingularAttribute<Campus, String> campusnm;
    public static volatile SingularAttribute<Campus, Date> createat;

}