package com.DevCollege.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentResponse {

    private String enrolmentId;
    private String student_id;
    private String studentName;
    private String course_id;
    private String courseName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date courseStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date courseEnd;
    private  String status;
    private String courseLink;
    private String studentLink;
}
