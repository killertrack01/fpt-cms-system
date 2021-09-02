package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.StudentFeedback;
import com.example.fptacademysystem.model.SubjectDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:37:00")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile CollectionAttribute<Feedback, StudentFeedback> studentFeedbackCollection;
    public static volatile SingularAttribute<Feedback, String> feedbackstatus;
    public static volatile SingularAttribute<Feedback, SubjectDetails> subjdetailsid;
    public static volatile SingularAttribute<Feedback, Integer> feedbackid;
    public static volatile SingularAttribute<Feedback, Date> updateat;
    public static volatile SingularAttribute<Feedback, Date> dateoffeedback;
    public static volatile SingularAttribute<Feedback, String> removeat;
    public static volatile SingularAttribute<Feedback, Integer> stugroid;
    public static volatile SingularAttribute<Feedback, Date> enddatefeedback;
    public static volatile SingularAttribute<Feedback, String> feedbacknm;
    public static volatile SingularAttribute<Feedback, Date> createat;
    public static volatile SingularAttribute<Feedback, Integer> lecturid;

}