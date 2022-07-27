package com.DevCollege.Service;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(CourseRequest courseRequest){
        Course course=Course.build("", courseRequest.getCourseName(),
                courseRequest.getDescription(), courseRequest.getNoOfSlot(),
                courseRequest.getCourseFee(), courseRequest.getCourseDuration(),
                courseRequest.getCourseTag());
        return courseRepository.save(course);
    }


}
//    Course course=courseService.addCourse(courseRequest);
//    Map<String, String> msg = new HashMap<>();
//        msg.put("Successfully Added Course details ", course.getCourseId());
//                return ResponseEntity.ok().body(msg)
