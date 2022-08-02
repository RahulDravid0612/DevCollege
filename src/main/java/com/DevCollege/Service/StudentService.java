package com.DevCollege.Service;

import com.DevCollege.DTO.StudentRequest;
import com.DevCollege.DTO.StudentResponse;
import com.DevCollege.Entity.Student;
import com.DevCollege.exception.UserNotFoundException;

import java.util.Map;

public interface StudentService {

    public Map<String, String> addStudent(Student student);

    public String deleteStudent(String studentId);

    public Student findByStudentId(String studentId) throws UserNotFoundException;

    public Map<String, String> addStudentWallet(StudentRequest studentRequest, String studentId) throws UserNotFoundException;

    public StudentResponse getWallet(String studentId) throws UserNotFoundException;

    public Map<String,String> updateStudent(StudentRequest studentRequest, String studentId) throws UserNotFoundException;

}
