package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String courseId;
    @NotNull(message = "courseName shouldn't be null")
    private String courseName;
    @NotNull(message = "description shouldn't be null")
    private String description;
    @Min(value = 1)
    private int noOfSlot;
    @Min(value = 0)
    private float courseFee;
    @Positive(message = "courseDuration shouldn't be null")
    private int courseDuration;
    @NotNull(message = "courseTag shouldn't be null")
    private String courseTag;



































































































































































































}
