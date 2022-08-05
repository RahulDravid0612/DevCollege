package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String studentId;
    @NotNull(message = "studentName shouldn't be null")
    private String studentName;
    @NotNull(message = "qualification shouldn't be null")
    private String qualification;
    @NotNull(message = "description shouldn't be null")
    @Pattern(regexp = "^\\d{10}$",message = "Contact number must be 10-digit numeric  ")
    private String studentContact;
    @Positive
    @Min(value = 1)
    private float wallet;

}
