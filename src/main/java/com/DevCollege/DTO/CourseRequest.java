package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CourseRequest {

    private String courseId;
    @NotNull(message = "courseName shouldn't be null")
    private String courseName;
    @NotNull(message = "description shouldn't be null")
    private String description;
    @Min(value = 1,message = "enter number value")
    private int noOfSlot;
    @Min(value = 1,message = "enter number value")
    private float courseFee;
    @Min(value = 1,message = "enter number value")
    private int courseDuration;
    private String courseTag;


}
