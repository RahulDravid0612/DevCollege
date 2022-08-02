package com.DevCollege.ServiceImpl;

import com.DevCollege.DTO.EnrolmentRequest;
import com.DevCollege.DTO.EnrolmentResponse;
import com.DevCollege.Entity.Course;
import com.DevCollege.Entity.Enrolment;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Repository.EnrolmentRepository;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.Service.EnrolmentService;
import com.DevCollege.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EnrolmentServiceImpl implements EnrolmentService {

    Calendar cal=Calendar.getInstance();

    @Autowired
    private EnrolmentRepository enrolmentRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Student findByStudentId(String studentId) throws UserNotFoundException {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new UserNotFoundException("student ID not found with id :" + studentId));
    }
    @Override
    public Course findByCourseId(String courseId) throws  UserNotFoundException{
        return courseRepository.findById(courseId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + courseId));
    }
    
    @Override
    public String addEnrolment(Enrolment enrolment) throws UserNotFoundException {

        Student student = findByStudentId(enrolment.getStudent_id());
        Course course = findByCourseId(enrolment.getCourse_id());
        if (course.getCourseId()!=null) {
            int duration = course.getCourseDuration();
            cal.setTime(enrolment.getCourseStart());
            cal.add(Calendar.MINUTE, duration);
            float c=course.getCourseFee();
            float s=student.getWallet();
            Date year = cal.getTime();
            enrolment.setCourseEnd(year);
            if (course.getNoOfSlot() >= 0) {
                    if (student.getWallet() >= course.getCourseFee()) {
                        student.setWallet(student.getWallet() - course.getCourseFee());
                        enrolment.setStatus("Allocated");
                        course.setNoOfSlot(course.getNoOfSlot() - 1);
                        enrolmentRepository.save(enrolment);
                        String str = "Successfully Enrolled for  " + student.getStudentName() + " in course "
                                + course.getCourseName() + " for enrolment id " + enrolment.getEnrolmentId();
                        return str;
                    } else {
                        return "your Wallet Amount is Running Low";
                    }
                } else {
                    return "course is full";
                }
            } else {
                return null;
            }
    }

    @Override
    public String checkAvailability(String courseId) throws UserNotFoundException {
        Enrolment enrolment1=enrolmentCourseId(courseId);
        Course course=findByCourseId(enrolment1.getCourse_id());
        if (course.getNoOfSlot()>0){
        if (enrolment1.getCourse_id().equals("Cancelled")||enrolment1.getCourse_id().equals("Completed")){
                return "course is available for enrolment";
            }
        return "course is available for enrolment";
        }
        return " course is not available for enrolment";
    }


    @Override
    public Enrolment enrolmentCourseId(String courseId) throws UserNotFoundException {
        Enrolment enrolment = enrolmentRepository.checkAvailability(courseId);
        if(enrolment!=null){
            return enrolment;
        }else {
            throw new UserNotFoundException(" courseId not found :"+courseId);
        }
    }

    @Override
    public Enrolment findByEnrolmentId(String enrolmentId) throws UserNotFoundException {
        return enrolmentRepository.findById(enrolmentId).orElseThrow(() ->
                new UserNotFoundException("enrolment ID not found with id :" + enrolmentId));
    }

    @Override
    public String changeStatus(EnrolmentRequest enrolmentRequest,String enrolmentId) throws UserNotFoundException {
        Enrolment enrolment = findByEnrolmentId(enrolmentId);
        Student student=findByStudentId(enrolment.getStudent_id());
        Course course=findByCourseId(enrolment.getCourse_id());
        if (enrolment.getStatus().equals("Allocated")) {
            enrolment.setStatus(enrolmentRequest.getStatus());
            Date startDate=enrolment.getCourseStart();
            Date nowDate=new Date();
            long diff = nowDate.getTime() - startDate.getTime();
            if(diff<=2){
                student.setWallet(course.getCourseFee()+course.getCourseFee());
                enrolmentRepository.save(enrolment);
                return "Successfully change the status from " + enrolment.getStatus() + " to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            } else if(diff==1) {
                student.setWallet((float) (course.getCourseFee()+(course.getCourseFee()/1.42)));
                return "Successfully change the status from " + enrolment.getStatus() + " to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            } else if (diff<1) {
                student.setWallet((course.getCourseFee()+(course.getCourseFee()/2)));
                return "Successfully change the status from " + enrolment.getStatus() + " to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            }

        }
        return "Status is Already Cancelled";
    }

    @Override
    public EnrolmentResponse getEnrolmentDetails(String enrolmentId) throws UserNotFoundException {
        Enrolment enrolment=findByEnrolmentId(enrolmentId);
        Student student=findByStudentId(enrolment.getStudent_id());
        Course course=findByCourseId(enrolment.getCourse_id());
        EnrolmentResponse enrolmentResponse=new EnrolmentResponse();
        enrolmentResponse.setEnrolmentId(enrolment.getEnrolmentId());
        enrolmentResponse.setStudent_id(enrolment.getStudent_id());
        enrolmentResponse.setStudentName(student.getStudentName());
        enrolmentResponse.setCourse_id(enrolment.getCourse_id());
        enrolmentResponse.setCourseName(course.getCourseName());
        enrolmentResponse.setCourseStart(enrolment.getCourseStart());
        enrolmentResponse.setCourseEnd(enrolment.getCourseEnd());
        enrolmentResponse.setStatus(enrolment.getStatus());
        enrolmentResponse.setCourseLink("http://localhost:8080/api/course/get/"+enrolment.getCourse_id());
        enrolmentResponse.setStudentLink("http://localhost:8080/api/enrolment/getByStudent/"+enrolment.getStudent_id());


        return enrolmentResponse;
    }

    @Override
    public List<?> getAllEnrolmentDetails() throws UserNotFoundException {
        List<Enrolment> enrolments = enrolmentRepository.findAll();
        List<EnrolmentResponse> enrolmentResponseList=new ArrayList<>();
        for(Enrolment enrolment:enrolments) {
            EnrolmentResponse enrolmentResponse = getEnrolmentDetails(enrolment.getEnrolmentId());
            enrolmentResponseList.add(enrolmentResponse);
        }
        return enrolmentResponseList;
    }

    @Override
    public List<?> getAllEnrolmentDetailsByStudentId(String studentId) throws UserNotFoundException {
        findByStudentId(studentId);
        List<Enrolment> enrolments = enrolmentRepository.findAll();
        List<EnrolmentResponse> enrolmentResponseList=new ArrayList<>();
        for (Enrolment enrolment:enrolments) {
            if (studentId.equals(enrolment.getStudent_id())){
                EnrolmentResponse enrolmentResponse = getEnrolmentDetails(enrolment.getEnrolmentId());
                enrolmentResponseList.add(enrolmentResponse);
            }
        }
        return enrolmentResponseList;
    }

}
