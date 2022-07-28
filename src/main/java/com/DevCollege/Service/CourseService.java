package com.DevCollege.Service;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Repository.EnrolmentRepository;
import com.DevCollege.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    public Map<String, String> addCourse(CourseRequest courseRequest){
        Course course=Course.build(courseRequest.getCourseId(), courseRequest.getCourseName(),
                courseRequest.getDescription(),courseRequest.getNoOfSlot(),
                courseRequest.getCourseFee(), courseRequest.getCourseDuration(),
                courseRequest.getCourseTag());
        courseRepository.save(course);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully Added Course details ", course.getCourseId());
       return msg;

    }
    public String deleteCourse(String courseId){
        courseRepository.deleteById(courseId);
        return  "Course deleted successfully";
    }

    public Course findByCourseId(String courseId) throws  UserNotFoundException{
        return courseRepository.findById(courseId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + courseId));
    }

    public Map<String,String> updateCourse(CourseRequest courseRequest, String courseId) throws UserNotFoundException {
        Course course=findByCourseId(courseId);
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        course.setNoOfSlot(courseRequest.getNoOfSlot());
        course.setCourseFee(courseRequest.getCourseFee());
        course.setCourseDuration(courseRequest.getCourseDuration());
        course.setCourseTag(courseRequest.getCourseTag());
        courseRepository.save(course);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully updated Course ", course.getCourseId());
        return msg;
    }
}

