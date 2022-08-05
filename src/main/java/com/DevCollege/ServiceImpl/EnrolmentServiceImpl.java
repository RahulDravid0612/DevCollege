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

import java.util.*;

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

    public List<Enrolment> findAll(){
        return enrolmentRepository.findAll();
    }
    @Override
    public String addEnrolment(Enrolment enrolment) throws UserNotFoundException {
        Boolean flag=false;
        Student student = findByStudentId(enrolment.getStudent_id());
        Course course = findByCourseId(enrolment.getCourse_id());
        List<Enrolment> enrolmentList=findAll();
        for(Enrolment enrolment1:enrolmentList) {
            if (enrolment.getStudent_id().equals(enrolment1.getStudent_id())){
                if (enrolment.getCourse_id().equals(enrolment1.getCourse_id()))
                flag=true;
            }
        }

        if(flag==false) {
            if (course.getCourseId() != null) {
                int duration = course.getCourseDuration();
                cal.setTime(enrolment.getCourseStart());
                cal.add(Calendar.MINUTE, duration);
                Date year = cal.getTime();
                enrolment.setCourseEnd(year);
                if (course.getNoOfSlot() > 0) {
                    if (student.getWallet() >= course.getCourseFee()) {
                        student.setWallet(student.getWallet() - course.getCourseFee());
                        enrolment.setStatus("Allocated");
                        course.setNoOfSlot(course.getNoOfSlot() - 1);
                        enrolmentRepository.save(enrolment);
                        return "Successfully Enrolled for  " + student.getStudentName() + " in course "
                                + course.getCourseName() + " for enrolment id " + enrolment.getEnrolmentId();
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
        return "Student Already Enrolled to this course";
    }

    @Override
    public String checkAvailability(String courseId) throws UserNotFoundException {
        Course course=findByCourseId(courseId);
        if (course.getNoOfSlot()>0){
        return "course is available for enrolment";
        }
        return " course is not available for enrolment";
    }

    @Override
    public Enrolment findByEnrolmentId(String enrolmentId) throws UserNotFoundException {
        return enrolmentRepository.findById(enrolmentId).orElseThrow(() ->
                new UserNotFoundException("enrolment ID not found with id :" + enrolmentId));
    }

    @Override
    public String changeStatus(EnrolmentRequest enrolmentRequest,String enrolmentId) throws UserNotFoundException {
        Enrolment enrolment = findByEnrolmentId(enrolmentId);
        Student student = findByStudentId(enrolment.getStudent_id());
        Course course = findByCourseId(enrolment.getCourse_id());
        if (enrolment.getStatus().equals(enrolmentRequest.getStatus())){
            return "Status Already in "+enrolmentRequest.getStatus()+" Status ";
        }
        if (enrolment.getStatus().equals("Cancelled")) {
            enrolment.setStatus(enrolmentRequest.getStatus());
            course.setNoOfSlot(course.getNoOfSlot()+1);
            student.setWallet(student.getWallet() + course.getCourseFee());
            studentRepository.save(student);
            courseRepository.save(course);
            enrolmentRepository.save(enrolment);
            return "Successfully change the status to "
                    + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
        }
        if (enrolment.getStatus().equals("Allocated")||enrolment.getStatus().equals("Inprogress")) {
            enrolment.setStatus(enrolmentRequest.getStatus());
            course.setNoOfSlot(course.getNoOfSlot()-1);
            student.setWallet(student.getWallet() - course.getCourseFee());
            studentRepository.save(student);
            courseRepository.save(course);
            Date startDate = enrolment.getCourseStart();
            Date nowDate = new Date();
            long diff = nowDate.getTime() - startDate.getTime();
            if (diff <= 2) {
                student.setWallet(course.getCourseFee() + course.getCourseFee());
                enrolmentRepository.save(enrolment);
                return "Successfully change the status to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            } else if (diff == 1) {
                student.setWallet((float) (course.getCourseFee() + (course.getCourseFee() / 1.42)));
                enrolmentRepository.save(enrolment);
                return "Successfully change the status to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            } else if (diff < 1) {
                student.setWallet((course.getCourseFee() + (course.getCourseFee() / 2)));
                enrolmentRepository.save(enrolment);
                return "Successfully change the status to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            } else {
                enrolmentRepository.save(enrolment);
                return "Successfully change the status to "
                        + enrolmentRequest.getStatus() + " for enrol id " + enrolmentId;
            }
        }
        return "Time Out";
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

    @Override
    public Map<String, String> getCourseSuggestion(String studentId) throws UserNotFoundException {
       Student student=findByStudentId(studentId);
       String qualification=student.getQualification();
       List<Course> courseList=courseRepository.findAll();
       Map<String,String> courses=new HashMap<>();
        for (Course course:courseList) {
            String[] currentQualification = course.getCourseTag().split(",");
            if(Arrays.asList(currentQualification).contains(qualification)){
                courses.put(course.getCourseId(),course.getCourseName());
            }
        }
        return courses;
    }
}
