package com.DevCollege.ServiceImpl;

import com.DevCollege.DTO.StudentRequest;
import com.DevCollege.DTO.StudentResponse;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.Service.StudentService;
import com.DevCollege.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Map<String, String> addStudent(Student student){
        studentRepository.save(student);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully Added Student details ", student.getStudentId());
        return msg;
    }

    @Override
    public String deleteStudent(String studentId){
        studentRepository.deleteById(studentId);
        return  "Student deleted successfully";
    }

    @Override
    public Map<String,String> updateStudent(StudentRequest studentRequest, String studentId) throws UserNotFoundException {
        Student student=findByStudentId(studentId);
        student.setStudentName(studentRequest.getStudentName());
        student.setQualification(studentRequest.getQualification());
        student.setStudentContact(studentRequest.getStudentContact());
        student.setWallet(studentRequest.getWallet());
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
    public Map<String, String> addStudentWallet(StudentRequest studentRequest, String studentId) throws UserNotFoundException {
        Student student=findByStudentId(studentId);
        float amt=studentRequest.getWallet();
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
