package com.DevCollege.ServiceImpl;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Repository.EnrolmentRepository;
import com.DevCollege.Service.CourseService;
import com.DevCollege.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    @Override
    public Map<String, String> addCourse(Course course){
        courseRepository.save(course);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully Added Course details ", course.getCourseId());
       return msg;

    }
    @Override
    public String deleteCourse(String courseId){
        courseRepository.deleteById(courseId);
        return  "Course deleted successfully";
    }
    @Override
    public Course findByCourseId(String courseId) throws  UserNotFoundException{
        return courseRepository.findById(courseId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + courseId));
    }

    @Override
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

