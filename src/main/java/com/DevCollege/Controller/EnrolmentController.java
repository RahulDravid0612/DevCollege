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

import java.util.List;

@RestController
@RequestMapping("/api/enrolment")
public class EnrolmentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    @Autowired
    private EnrolmentService enrolmentService;
    @PostMapping("/addEnrolment")
    public String saveEnrolment(@RequestBody EnrolmentRequest enrolmentRequest) throws UserNotFoundException {
        Enrolment enrolment = modelMapper.map(enrolmentRequest, Enrolment.class);
        return enrolmentService.addEnrolment(enrolment);
    }

    @GetMapping("/getAll")
    public List<?> findAllEnrolment() throws UserNotFoundException {
        return enrolmentService.getAllEnrolmentDetails();
    }

    @GetMapping("/getAllByStudentId/{studentId}")
    public  List<?> findAllEnrolmentByStudentId(@PathVariable String studentId) throws UserNotFoundException {
        return enrolmentService.getAllEnrolmentDetailsByStudentId(studentId);
    }

    @GetMapping("/get/{enrolmentId}")
    public EnrolmentResponse findByCourseId(@PathVariable String enrolmentId) throws UserNotFoundException {
        return enrolmentService.getEnrolmentDetails(enrolmentId);
    }

    @GetMapping("/getByStudent/{studentId}")
    public Enrolment findByStudentId(@PathVariable String studentId) throws UserNotFoundException {
        Enrolment enrolment = enrolmentRepository.getStudentByEmailIdNative(studentId);
        if(enrolment!=null){
            return enrolment;
        }else {
            throw new UserNotFoundException(" studentID not found with studentId:"+studentId);
        }
    }

    @GetMapping("/checkAvailability/{courseId}")
    public String availability(@PathVariable String courseId)  throws UserNotFoundException{
        return enrolmentService.checkAvailability(courseId);
    }

    @PostMapping("/changeStatus/{enrolmentId}")
    public String changeStatus(@RequestBody EnrolmentRequest enrolmentRequest,@PathVariable String enrolmentId) throws UserNotFoundException {
        return enrolmentService.changeStatus(enrolmentRequest,enrolmentId);
    }

}
