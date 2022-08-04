package com.DevCollege.ServiceImpl;

import com.DevCollege.DTO.*;
import com.DevCollege.Entity.Course;
import com.DevCollege.Entity.Enrolment;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.CourseRepository;
import com.DevCollege.Repository.EnrolmentRepository;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.Service.EnrolmentService;
import com.DevCollege.Service.StudentService;
import com.DevCollege.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrolmentService enrolmentService;

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<StudentRequest> findStudent() {
        List<Student> studentList= studentRepository.findAll();
        List<StudentRequest> studentRequestList = studentList.stream().
                map((student -> this.modelMapper.map(student, StudentRequest.class))).collect(Collectors.toList());
        return studentRequestList;
    }

    @Override
    public StudentRequest findByStudent(String studentId) throws UserNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new UserNotFoundException("student ID not found with id :" + studentId));
        StudentRequest studentRequest = modelMapper.map(student, StudentRequest.class);
        return studentRequest;
    }

    @Override
    public Map<String, String> addStudent(Student student){
        studentRepository.save(student);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully Added Student details ", student.getStudentId());
        return msg;
    }

    public Course findByCourseId(String courseId) throws  UserNotFoundException{
        return courseRepository.findById(courseId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + courseId));
    }
    @Override
    public String deleteStudent(String studentId) throws UserNotFoundException {
        findByStudentId(studentId);
        Boolean flag=true;
        float sum=0;
        List<Enrolment> enrolments = enrolmentRepository.findAll();
        for (Enrolment enrolment1 : enrolments){
            System.out.println(studentId);
            System.out.println(enrolment1.getStudent_id());
            if (studentId.equals(enrolment1.getStudent_id())) {
                String courseId = enrolment1.getCourse_id();
                Course course = findByCourseId(courseId);
                Float courseFee = course.getCourseFee();
                 sum += courseFee;
                 flag=false;
            }
        }
        if(flag==false){
            studentRepository.deleteById(studentId);
            return  "Successfully deleted Student details with  " +studentId+
                    "  And amount  "+ sum/2 + "  will be refunded in original payment method within 24 hours.";
        }else {
            studentRepository.deleteById(studentId);
            return "Student not enrolled to Any Course and Successfully deleted Student details";
        }
    }

    @Override
    public Map<String,String> updateStudent(StudentRequestUpdate studentRequestUpdate, String studentId) throws UserNotFoundException {
        Student student=findByStudentId(studentId);
        student.setStudentName(studentRequestUpdate.getStudentName());
        student.setQualification(studentRequestUpdate.getQualification());
        student.setStudentContact(studentRequestUpdate.getStudentContact());
        studentRepository.save(student);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully updated Course ", student.getStudentId());
        return msg;
    }

    @Override
    public Student findByStudentId(String studentId) throws UserNotFoundException {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new UserNotFoundException("student ID not found with id :" + studentId));
    }

    @Override
    public Map<String, String> addStudentWallet(StudentRequest1 studentRequest1, String studentId) throws UserNotFoundException {
        Student student=findByStudentId(studentId);
        float amt=studentRequest1.getWallet();
        float wallet= student.getWallet();
        float add=amt+wallet;
        student.setWallet(add);
        studentRepository.save(student);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully updated Student wallet ", student.getStudentId());
        return msg;
    }

    @Override
    public StudentResponse getWallet(String studentId) throws UserNotFoundException {
        Student student=findByStudentId(studentId);
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setAmount(student.getWallet());
        return studentResponse;
    }
}
