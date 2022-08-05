package com.DevCollege.Service;

import com.DevCollege.DTO.*;
import com.DevCollege.Entity.Course;
import com.DevCollege.Entity.Student;
import com.DevCollege.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public List<StudentRequest> findStudent();

    StudentRequest findByStudent(String studentId) throws UserNotFoundException;

    public Map<String, String> addStudent(StudentRequest studentRequest);

    Course findByCourseId(String courseId) throws  UserNotFoundException;

    public String deleteStudent(String studentId) throws UserNotFoundException;

    public Student findByStudentId(String studentId) throws UserNotFoundException;

    public Map<String, String> addStudentWallet(StudentRequest1 studentRequest1, String studentId) throws UserNotFoundException;

    public StudentResponse getWallet(String studentId) throws UserNotFoundException;

    public Map<String,String> updateStudent(StudentRequestUpdate studentRequestUpdate, String studentId) throws UserNotFoundException;

}
