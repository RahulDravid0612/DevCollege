package com.DevCollege.Controller;

import com.DevCollege.DTO.StudentRequest;
import com.DevCollege.DTO.StudentResponse;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.ServiceImpl.StudentServiceImpl;
import com.DevCollege.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServiceImpl studentService;



    @GetMapping("/getAll")
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    //add student details
    @PostMapping("/addStudent")
    public Map<String,String> saveStudent(@Valid @RequestBody StudentRequest studentRequest){
        Student student = modelMapper.map(studentRequest, Student.class);
        return studentService.addStudent(student);
    }

    //get student by student ID
    @GetMapping("/get/{studentId}")
    public ResponseEntity<Student> findByStudentId(@PathVariable String studentId) throws UserNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new UserNotFoundException("student ID not found with id :" + studentId));
        return ResponseEntity.ok(student);
    }

    //delete student by student ID
    @Modifying
    @Transactional
    @GetMapping("delete/{studentId}")
    public String deleteByStudentId(@PathVariable String studentId){
        return studentService.deleteStudent(studentId);
    }

    //Add wallet by student ID
    @PostMapping("/studentWallet/{studentId}")
    public Map<String,String> addStudentWallet(@RequestBody StudentRequest studentRequest,@PathVariable String studentId) throws UserNotFoundException {
        return studentService.addStudentWallet(studentRequest,studentId);
    }

    @GetMapping("/studentWallet/{studentId}")
    public StudentResponse getStudentWallet(@PathVariable String studentId) throws UserNotFoundException {
        return studentService.getWallet(studentId);
    }

    @PutMapping("/studentUpdate/{studentId}")
    public Map<String, String> updateStudentByStudentId(@RequestBody StudentRequest studentRequest, @PathVariable String studentId) throws UserNotFoundException {
        return studentService.updateStudent(studentRequest,studentId);

    }
}
