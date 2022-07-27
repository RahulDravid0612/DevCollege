package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CourseRequest {

    private String courseId;
    @NotNull(message = "courseName shouldn't be null")
    private String courseName;
    @NotNull(message = "description shouldn't be null")
    private String description;
    @Min(value = 1)
    @Max(value = 1000)
    private int noOfSlot;
    @NotNull(message = "noOfSlot cannot be null")
    @Min(value = 1)
    @Max(value = 1000)
    private float courseFee;
    @Min(value = 1)
    @Max(value = 1000)
    private int courseDuration;
    private String courseTag;


}
