package com.DevCollege.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Enrolment {
    @Id
    @GenericGenerator(name = "enrolment_id",strategy = "com.DevCollege.Generator.EnrolmentIdGenerator")
    @GeneratedValue(
            generator = "enrolment_id"
    )
    @Column(name = "enrolment_id")
    private String enrolmentId;
    private Date courseStart;
    private Date courseEnd;
    private  String status;
    private String student_id;
    private String course_id;



}
