package com.example.fptacademysystem.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-16T22:25:22")
@StaticMetamodel(RenderAttendanceReport.class)
public class RenderAttendanceReport_ { 

    public static volatile SingularAttribute<RenderAttendanceReport, Integer> semid;
    public static volatile SingularAttribute<RenderAttendanceReport, Integer> stuid;
    public static volatile SingularAttribute<RenderAttendanceReport, Date> subjdate;
    public static volatile SingularAttribute<RenderAttendanceReport, Integer> subjid;
    public static volatile SingularAttribute<RenderAttendanceReport, Integer> stugroid;
    public static volatile SingularAttribute<RenderAttendanceReport, Boolean> present;
    public static volatile SingularAttribute<RenderAttendanceReport, String> rollnum;
    public static volatile SingularAttribute<RenderAttendanceReport, String> fullnm;

}