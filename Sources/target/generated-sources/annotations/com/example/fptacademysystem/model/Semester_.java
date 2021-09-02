package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.SubjectDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(Semester.class)
public class Semester_ { 

    public static volatile SingularAttribute<Semester, Integer> semid;
    public static volatile CollectionAttribute<Semester, SubjectDetails> subjectDetailsCollection;
    public static volatile SingularAttribute<Semester, Date> updateat;
    public static volatile SingularAttribute<Semester, String> removeat;
    public static volatile SingularAttribute<Semester, String> semnm;
    public static volatile SingularAttribute<Semester, Date> createat;

}