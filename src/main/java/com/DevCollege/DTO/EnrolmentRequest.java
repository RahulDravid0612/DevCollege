package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EnrolmentRequest {

    private String enrolmentId;
    private String studentId;
    private String courseID;
    private LocalDateTime courseStart;
    private LocalDateTime courseEnd;
    private  String status;

}
