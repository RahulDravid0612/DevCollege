package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestUpdate {
    private String studentId;
    @NotNull(message = "studentName shouldn't be null")
    private String studentName;
    @NotNull(message = "qualification shouldn't be null")
    private String qualification;
    @NotNull(message = "studentContact shouldn't be null")
    @Pattern(regexp = "^\\d{10}$",message = "Contact number must be 10-digit numeric  ")
    private String studentContact;
    private float wallet;
}
