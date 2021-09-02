package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Exam;
import com.example.fptacademysystem.model.StudentClass;
import com.example.fptacademysystem.model.Timetable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(StudentGroup.class)
public class StudentGroup_ { 

    public static volatile CollectionAttribute<StudentGroup, StudentClass> studentClassCollection;
    public static volatile CollectionAttribute<StudentGroup, Timetable> timetableCollection;
    public static volatile SingularAttribute<StudentGroup, String> session;
    public static volatile SingularAttribute<StudentGroup, Courses> courid;
    public static volatile SingularAttribute<StudentGroup, Integer> shift;
    public static volatile SingularAttribute<StudentGroup, Date> updateat;
    public static volatile SingularAttribute<StudentGroup, Integer> stugroid;
    public static volatile SingularAttribute<StudentGroup, String> stugronm;
    public static volatile SingularAttribute<StudentGroup, Date> createat;
    public static volatile SingularAttribute<StudentGroup, Date> openingdate;
    public static volatile SingularAttribute<StudentGroup, String> removeat;
    public static volatile SingularAttribute<StudentGroup, BranchCampus> branchcamid;
    public static volatile CollectionAttribute<StudentGroup, Exam> examCollection;

}