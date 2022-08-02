package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EnrolmentResponse {

    private String enrolmentId;
    private String student_id;
    private String studentName;
    private String course_id;
    private String courseName;
    private Date courseStart;
    private Date courseEnd;
    private  String status;
    private String courseLink;
    private String studentLink;
}
