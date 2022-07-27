package com.DevCollege.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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

}
