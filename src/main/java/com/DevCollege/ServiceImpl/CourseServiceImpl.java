package com.DevCollege.ServiceImpl;

import com.DevCollege.DTO.CourseRequest;
import com.DevCollege.Entity.Course;
import com.DevCollege.Entity.Enrolment;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Repository.EnrolmentRepository;
import com.DevCollege.Service.CourseService;
import com.DevCollege.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrolmentRepository enrolmentRepository;


    @Override
    public List<CourseRequest> findCourse() {
        List<Course> courseList= courseRepository.findAll();
        List<CourseRequest> courseRequestList = courseList.stream().
                map((course -> this.modelMapper.map(course, CourseRequest.class))).collect(Collectors.toList());
        return courseRequestList;
    }

    @Override
    public CourseRequest findByCourse(String courseId) throws UserNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + courseId));
        CourseRequest courseRequest = modelMapper.map(course, CourseRequest.class);

        return courseRequest;
    }
    @Override
    public Map<String, String> addCourse(CourseRequest courseRequest){
        Map<String, String> msg = new HashMap<>();
        Course course = modelMapper.map(courseRequest, Course.class);
        courseRepository.save(course);
        msg.put("Successfully Added Course details ", course.getCourseId());
       return msg;

    }
    @Override
    public String deleteCourse(String courseId) throws UserNotFoundException {
        findByCourseId(courseId);
        Enrolment enrolment = enrolmentRepository.checkAvailability(courseId);
        if(enrolment!=null) {
            if (enrolment.getStatus().equals("Allocated") || enrolment.getStatus().equals("Inprogress")) {
                return "User cannot delete a course " +
                        " and course is in progress or allocated status";
            }
            courseRepository.deleteById(courseId);
            return "Course deleted successfully";
        }
        courseRepository.deleteById(courseId);
        return "Course deleted successfully";
    }

    @Override
    public Course findByCourseId(String courseId) throws  UserNotFoundException{
        return courseRepository.findById(courseId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + courseId));
    }

    @Override
    public Map<String,String> updateCourse(CourseRequest courseRequest, String courseId) throws UserNotFoundException {
        Course course = findByCourseId(courseId);
        Enrolment enrolment = enrolmentRepository.checkAvailability(courseId);
        if (enrolment != null) {
            if (enrolment.getStatus().equals("Allocated")) {
                course.setCourseName(courseRequest.getCourseName());
                course.setDescription(courseRequest.getDescription());
                course.setNoOfSlot(courseRequest.getNoOfSlot());
                courseRepository.save(course);
                Map<String, String> msg = new HashMap<>();
                msg.put("Successfully updated Course ", course.getCourseId());
                return msg;
            }
        }else {
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
        return null;
    }
}

