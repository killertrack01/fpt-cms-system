package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.StudentGroup;
import com.example.fptacademysystem.model.SubjectDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Courses.class)
public class Courses_ { 

    public static volatile SingularAttribute<Courses, Branch> branchid;
    public static volatile CollectionAttribute<Courses, SubjectDetails> subjectDetailsCollection;
    public static volatile SingularAttribute<Courses, Integer> courid;
    public static volatile SingularAttribute<Courses, Date> updateat;
    public static volatile SingularAttribute<Courses, String> removeat;
    public static volatile SingularAttribute<Courses, String> cournm;
    public static volatile CollectionAttribute<Courses, StudentGroup> studentGroupCollection;
    public static volatile SingularAttribute<Courses, Date> createat;

}