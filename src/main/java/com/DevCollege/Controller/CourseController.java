package com.DevCollege.Controller;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Service.CourseService;
import com.DevCollege.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    //Get All course List
    @GetMapping("/getAll")
    public List<CourseRequest> findAllStudents() {
        return courseService.findCourse();
    }

    //Add course By course ID
    @PostMapping("/addCourse")
    public Map<String, String> saveCourse(@Valid @RequestBody CourseRequest courseRequest){
        Course course = modelMapper.map(courseRequest, Course.class);
        return courseService.addCourse(course);
    }


    //update course by course ID
    @PutMapping("/updateCourse/{courseId}")
    public Map<String, String> updateCourseByCourseId(@Valid @RequestBody CourseRequest courseRequest, @PathVariable String courseId) throws UserNotFoundException {
       return courseService.updateCourse(courseRequest,courseId);

    }

    //get course by course ID
    @GetMapping("/get/{courseId}")
    public CourseRequest findByCourseId(@PathVariable String courseId) throws UserNotFoundException {
        return courseService.findByCourse(courseId) ;
    }

    //delete course by course ID
    @DeleteMapping("deleteCourse/{courseId}")
    public String deleteByCourseId1(@PathVariable String courseId) throws UserNotFoundException {
        return courseService.deleteCourse(courseId);
    }

}

