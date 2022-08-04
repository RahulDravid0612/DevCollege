package com.DevCollege.Service;

import com.DevCollege.DTO.EnrolmentRequest;
import com.DevCollege.DTO.EnrolmentResponse;
import com.DevCollege.Entity.Course;
import com.DevCollege.Entity.Enrolment;
import com.DevCollege.Entity.Student;
import com.DevCollege.exception.UserNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface EnrolmentService {

    public Course findByCourseId(String courseId) throws  UserNotFoundException;

    public Student findByStudentId(String studentId) throws UserNotFoundException;

    public String addEnrolment(Enrolment enrolment) throws UserNotFoundException;

    public String checkAvailability(String courseId) throws  UserNotFoundException;

    Enrolment findByEnrolmentId(String enrolmentId) throws UserNotFoundException;

    String changeStatus(EnrolmentRequest enrolmentRequest, String enrolmentId) throws UserNotFoundException;

    EnrolmentResponse getEnrolmentDetails(String enrolmentId) throws UserNotFoundException;

    List<?> getAllEnrolmentDetails() throws UserNotFoundException;

    List<?> getAllEnrolmentDetailsByStudentId(String studentId) throws UserNotFoundException;

    Map<String,String> getCourseSuggestion(String StudentId) throws  UserNotFoundException;
}
