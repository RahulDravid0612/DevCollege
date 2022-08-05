package com.DevCollege.Service;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Entity.Enrolment;
import com.DevCollege.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public List<CourseRequest> findCourse();

    CourseRequest findByCourse(String courseId) throws UserNotFoundException;

    public Map<String, String> addCourse(CourseRequest courseRequest);

    public String deleteCourse(String courseId) throws UserNotFoundException;

    public Course findByCourseId(String courseId) throws UserNotFoundException;

    public Map<String,String> updateCourse(CourseRequest courseRequest, String courseId) throws UserNotFoundException;


}
