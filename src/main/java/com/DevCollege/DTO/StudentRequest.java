package com.DevCollege.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class StudentRequest {

    private String studentId;
    @NotNull(message = "description shouldn't be null")
    private String studentName;
    @NotNull(message = "description shouldn't be null")
    private String qualification;
    @NotNull(message = "description shouldn't be null")
    private String studentContact;
    @Pattern(regexp = "^\\d{10}$",message = "Contact number must be 10-digit numeric  ")
    private float wallet;

}
