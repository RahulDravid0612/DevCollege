package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentRequest {
    @NotNull(message = "student_id shouldn't be null")
    private String student_id;
    @NotNull(message = "course_id shouldn't be null")
    private String course_id;
    @NotNull(message = "courseStart Date is Required")
    @FutureOrPresent(message = "The Date must be today or in the future")
    private Date courseStart;
    private  String status;

}
