package com.DevCollege.Service;

import com.DevCollege.DTO.StudentRequest;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Map<String, String> addStudent(StudentRequest studentRequest){
        Student student=Student.build(studentRequest.getStudentId(), studentRequest.getStudentName(),
                studentRequest.getQualification(), studentRequest.getStudentContact(),0);
        studentRepository.save(student);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully Added Student details ", student.getStudentId());
        return msg;
    }

    public String deleteStudent(String studentId){
        studentRepository.deleteById(studentId);
        return  "Student deleted successfully";
    }

    public Student findByStudentId(String studentId) throws UserNotFoundException {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new UserNotFoundException("course ID not found with id :" + studentId));
    }

    public Map<String, String> addStudentWallet(StudentRequest studentRequest, String studentId) throws UserNotFoundException {
        Student student=findByStudentId(studentId);
        student.setWallet(studentRequest.getWallet());
        studentRepository.save(student);
        Map<String, String> msg = new HashMap<>();
        msg.put("Successfully updated Student wallet ", student.getStudentId());
        return msg;
    }
}
