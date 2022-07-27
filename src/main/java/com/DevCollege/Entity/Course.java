package com.DevCollege.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

}

