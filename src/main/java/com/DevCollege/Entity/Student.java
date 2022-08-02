package com.DevCollege.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GenericGenerator(name = "student_id",strategy = "com.DevCollege.Generator.StudentIdGenerator")
    @GeneratedValue(
            generator = "student_id"
    )
    @Column(name = "student_id")
    private String studentId;
    private String studentName;
    private String qualification;
    private String studentContact;
    private float wallet;

    @OneToMany(targetEntity = Enrolment.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private List<Enrolment> enrolments;

}
