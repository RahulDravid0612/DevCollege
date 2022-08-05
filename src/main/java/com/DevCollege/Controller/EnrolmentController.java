package com.DevCollege.Controller;

import com.DevCollege.DTO.EnrolmentRequest;
import com.DevCollege.DTO.EnrolmentResponse;
import com.DevCollege.Entity.Enrolment;
import com.DevCollege.Repository.EnrolmentRepository;
import com.DevCollege.Service.EnrolmentService;
import com.DevCollege.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrolment")
public class EnrolmentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    @Autowired
    private EnrolmentService enrolmentService;

    //Add Enrolment
    @PostMapping("/add")
    public String saveEnrolment(@Valid @RequestBody EnrolmentRequest enrolmentRequest) throws UserNotFoundException {
        return enrolmentService.addEnrolment(enrolmentRequest);
    }

    //

    @GetMapping("/getAll")
    public List<?> findAllEnrolment() throws UserNotFoundException {
        return enrolmentService.getAllEnrolmentDetails();
    }

    @GetMapping("/getStudent/{studentId}")
    public  List<?> findAllEnrolmentByStudentId(@PathVariable String studentId) throws UserNotFoundException {
        return enrolmentService.getAllEnrolmentDetailsByStudentId(studentId);
    }

    @GetMapping("/get/{enrolmentId}")
    public EnrolmentResponse findByCourseId(@PathVariable String enrolmentId) throws UserNotFoundException {
        return enrolmentService.getEnrolmentDetails(enrolmentId);
    }


    @GetMapping("/availability/{courseId}")
    public String availability(@PathVariable String courseId)  throws UserNotFoundException{
        return enrolmentService.checkAvailability(courseId);
    }

    @PostMapping("/status/{enrolmentId}")
    public String changeStatus(@RequestBody EnrolmentRequest enrolmentRequest,@PathVariable String enrolmentId) throws UserNotFoundException {
        return enrolmentService.changeStatus(enrolmentRequest,enrolmentId);
    }

    @GetMapping("/suggestion/{studentId}")
    public Map<String, String> courseSuggestion(@PathVariable String studentId) throws UserNotFoundException {
        return enrolmentService.getCourseSuggestion(studentId);
    }
}
