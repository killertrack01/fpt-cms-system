package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Courses;
import com.example.fptacademysystem.model.Feedback;
import com.example.fptacademysystem.model.Semester;
import com.example.fptacademysystem.model.Subject;
import com.example.fptacademysystem.model.Timetable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(SubjectDetails.class)
public class SubjectDetails_ { 

    public static volatile SingularAttribute<SubjectDetails, Integer> slots;
    public static volatile SingularAttribute<SubjectDetails, Semester> semid;
    public static volatile SingularAttribute<SubjectDetails, Integer> subjdetailsid;
    public static volatile CollectionAttribute<SubjectDetails, Timetable> timetableCollection;
    public static volatile SingularAttribute<SubjectDetails, Courses> courid;
    public static volatile SingularAttribute<SubjectDetails, Double> fee;
    public static volatile CollectionAttribute<SubjectDetails, Feedback> feedbackCollection;
    public static volatile SingularAttribute<SubjectDetails, Date> updateat;
    public static volatile SingularAttribute<SubjectDetails, String> removeat;
    public static volatile SingularAttribute<SubjectDetails, Subject> subjid;
    public static volatile SingularAttribute<SubjectDetails, Date> createat;

}