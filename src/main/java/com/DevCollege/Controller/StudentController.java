package com.DevCollege.Controller;

import com.DevCollege.DTO.StudentRequest;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.Service.StudentService;
import com.DevCollege.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    //add student details
    @PostMapping("/addStudent")
    public Map<String,String> saveStudent(@Valid @RequestBody StudentRequest studentRequest){
        return studentService.addStudent(studentRequest);

    }

    //get student by student ID
    @GetMapping("/get/{studentId}")
    public ResponseEntity<Student> findByCourseId(@PathVariable String studentId) throws UserNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new UserNotFoundException("student ID not found with id :" + studentId));
        return ResponseEntity.ok(student);
    }

    //delete student by student ID
    @Modifying
    @Transactional
    @GetMapping("delete/del/{studentId}")
    public String deleteByCourseId1(@PathVariable String studentId){
        return studentService.deleteStudent(studentId);
    }

    //Add wallet by student ID
    @PostMapping("/studentWallet/{studentId}")
    public Map<String,String> addStudentWallet(@RequestBody StudentRequest studentRequest,@PathVariable String studentId) throws UserNotFoundException {
        return studentService.addStudentWallet(studentRequest,studentId);


    }

}
