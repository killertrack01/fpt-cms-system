package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Province;
import com.example.fptacademysystem.model.Wards;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-03T22:40:52")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> citynm;
    public static volatile SingularAttribute<City, Date> updateat;
    public static volatile SingularAttribute<City, String> removeat;
    public static volatile SingularAttribute<City, Integer> cityid;
    public static volatile CollectionAttribute<City, Wards> wardsCollection;
    public static volatile SingularAttribute<City, Date> createat;
    public static volatile SingularAttribute<City, Province> proviid;

}