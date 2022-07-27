package com.DevCollege.Controller;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody CourseRequest courseRequest){
       return new ResponseEntity<>(courseService.addCourse(courseRequest), HttpStatus.BAD_REQUEST);
//        CourseRequest cs=new CourseRequest();
//        Map<String, String> msg = new HashMap<>();
//        msg.put("Successfully Added Course details ", cs.getCourseId());
//        return ResponseEntity.ok().body(msg);
    }

@PutMapping("/updateCourse/{courseId}")
    public ResponseEntity<?> updateCourseByCourseId(@PathVariable String courseId){
        return ResponseEntity.ok(courseRepository.updateCourseByCourseId(courseId));
}
}

