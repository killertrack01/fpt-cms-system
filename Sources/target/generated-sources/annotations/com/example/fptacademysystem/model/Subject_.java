package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.SubjectDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Subject.class)
public class Subject_ { 

    public static volatile SingularAttribute<Subject, Integer> branchid;
    public static volatile SingularAttribute<Subject, String> subjnm;
    public static volatile CollectionAttribute<Subject, SubjectDetails> subjectDetailsCollection;
    public static volatile SingularAttribute<Subject, Integer> subjid;
    public static volatile SingularAttribute<Subject, Date> updateat;
    public static volatile SingularAttribute<Subject, String> removeat;
    public static volatile SingularAttribute<Subject, String> shortnm;
    public static volatile SingularAttribute<Subject, Date> createat;

}