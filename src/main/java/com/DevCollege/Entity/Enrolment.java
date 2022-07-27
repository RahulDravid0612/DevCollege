package com.DevCollege.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
public class Enrolment {
    @Id
    @GenericGenerator(name = "enrolment_id",strategy = "com.DevCollege.Generator.EnrolmentIdGenerator")
    @GeneratedValue(
            generator = "enrolment_id"
    )
    @Column(name = "enrolment_id")
    private String enrolmentId;
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "course_id")
    private String courseID;
    private LocalDateTime courseStart;
    private LocalDateTime courseEnd;
    private  String status;


}
