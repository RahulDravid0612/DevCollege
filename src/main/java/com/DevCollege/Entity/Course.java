package com.DevCollege.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Course {

    @Id
    @GenericGenerator(name = "course_id",strategy = "com.DevCollege.Generator.CourseIdGenerator")
    @GeneratedValue(
            generator = "course_id"
    )
    @Column(name = "course_id")
    private String courseId;
    private String courseName;
    private String description;
    private int noOfSlot;
    private float courseFee;
    private int courseDuration;
    private String courseTag;

    @OneToMany(targetEntity = Enrolment.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
   private List<Enrolment> enrolments;

}

