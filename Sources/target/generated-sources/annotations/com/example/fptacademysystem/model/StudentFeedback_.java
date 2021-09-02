package com.example.fptacademysystem.model;

import com.example.fptacademysystem.model.Feedback;
import com.example.fptacademysystem.model.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-26T02:36:59")
@StaticMetamodel(StudentFeedback.class)
public class StudentFeedback_ { 

    public static volatile SingularAttribute<StudentFeedback, String> ans3;
    public static volatile SingularAttribute<StudentFeedback, String> ans2;
    public static volatile SingularAttribute<StudentFeedback, String> note;
    public static volatile SingularAttribute<StudentFeedback, String> ans5;
    public static volatile SingularAttribute<StudentFeedback, String> ans4;
    public static volatile SingularAttribute<StudentFeedback, Student> stuid;
    public static volatile SingularAttribute<StudentFeedback, String> ans1;
    public static volatile SingularAttribute<StudentFeedback, Date> updateat;
    public static volatile SingularAttribute<StudentFeedback, Feedback> feedbackid;
    public static volatile SingularAttribute<StudentFeedback, Date> creatat;
    public static volatile SingularAttribute<StudentFeedback, Double> gpafeedback;
    public static volatile SingularAttribute<StudentFeedback, String> removeat;
    public static volatile SingularAttribute<StudentFeedback, Integer> stufeedbackid;

}