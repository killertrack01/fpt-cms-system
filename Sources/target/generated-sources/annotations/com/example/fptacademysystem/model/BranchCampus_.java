package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.model.Campus;
import com.example.fptacademysystem.model.StudentGroup;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(BranchCampus.class)
public class BranchCampus_ { 

    public static volatile SingularAttribute<BranchCampus, Branch> branchid;
    public static volatile SingularAttribute<BranchCampus, String> address;
    public static volatile SingularAttribute<BranchCampus, Campus> campusid;
    public static volatile SingularAttribute<BranchCampus, Integer> branchcamid;
    public static volatile SingularAttribute<BranchCampus, Date> updateat;
    public static volatile SingularAttribute<BranchCampus, String> removeat;
    public static volatile SingularAttribute<BranchCampus, String> branchcamnm;
    public static volatile CollectionAttribute<BranchCampus, StudentGroup> studentGroupCollection;
    public static volatile SingularAttribute<BranchCampus, Date> createat;

}