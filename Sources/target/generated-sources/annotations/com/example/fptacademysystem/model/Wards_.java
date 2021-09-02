package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.City;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-03T22:40:52")
@StaticMetamodel(Wards.class)
public class Wards_ { 

    public static volatile SingularAttribute<Wards, String> wardnm;
    public static volatile SingularAttribute<Wards, Date> updateat;
    public static volatile SingularAttribute<Wards, String> removeat;
    public static volatile SingularAttribute<Wards, Integer> wardid;
    public static volatile SingularAttribute<Wards, City> cityid;
    public static volatile SingularAttribute<Wards, Date> createat;

}