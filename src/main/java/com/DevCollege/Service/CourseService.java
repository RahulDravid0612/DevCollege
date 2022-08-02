package com.DevCollege.Service;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.exception.UserNotFoundException;

import java.util.Map;

public interface CourseService {
    public Map<String, String> addCourse(Course course);

    public String deleteCourse(String courseId);

    public Course findByCourseId(String courseId) throws UserNotFoundException;

    public Map<String,String> updateCourse(CourseRequest courseRequest, String courseId) throws UserNotFoundException;


}
