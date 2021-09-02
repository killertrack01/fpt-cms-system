package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.City;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-03T22:40:52")
@StaticMetamodel(Province.class)
public class Province_ { 

    public static volatile CollectionAttribute<Province, City> cityCollection;
    public static volatile SingularAttribute<Province, Date> updateat;
    public static volatile SingularAttribute<Province, String> removeat;
    public static volatile SingularAttribute<Province, String> provinm;
    public static volatile SingularAttribute<Province, Integer> proviid;
    public static volatile SingularAttribute<Province, Date> createat;

}