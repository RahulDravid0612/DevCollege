package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentRequest {

    private String student_id;
    private String course_id;
    private Date courseStart;
    private  String status;

}
