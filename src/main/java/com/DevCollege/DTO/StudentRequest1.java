package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest1 {

    @Positive
    @Min(value = 1)
    private float wallet;
}
