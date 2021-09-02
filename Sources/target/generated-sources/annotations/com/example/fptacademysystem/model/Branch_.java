package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.model.Courses;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile SingularAttribute<Branch, Integer> branchid;
    public static volatile CollectionAttribute<Branch, BranchCampus> branchCampusCollection;
    public static volatile CollectionAttribute<Branch, Courses> coursesCollection;
    public static volatile SingularAttribute<Branch, Date> updateat;
    public static volatile SingularAttribute<Branch, String> removeat;
    public static volatile SingularAttribute<Branch, String> branchnm;
    public static volatile SingularAttribute<Branch, Date> createat;

}