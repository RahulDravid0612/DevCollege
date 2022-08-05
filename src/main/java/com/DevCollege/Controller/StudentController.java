package com.DevCollege.Controller;

import com.DevCollege.DTO.StudentRequest;
import com.DevCollege.DTO.StudentRequest1;
import com.DevCollege.DTO.StudentRequestUpdate;
import com.DevCollege.DTO.StudentResponse;
import com.DevCollege.Entity.Student;
import com.DevCollege.Repository.StudentRepository;
import com.DevCollege.ServiceImpl.StudentServiceImpl;
import com.DevCollege.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
    public List<StudentRequest> findAllStudents() {
        return studentService.findStudent();
    }

    //add student details
    @PostMapping("/addStudent")
    public Map<String,String> saveStudent(@Valid @RequestBody StudentRequest studentRequest){
        return studentService.addStudent(studentRequest);
    }

    //get student by student ID
    @GetMapping("/get/{studentId}")
    public StudentRequest findByStudentId(@PathVariable String studentId) throws UserNotFoundException {
        return studentService.findByStudent(studentId);
    }

    //delete student by student ID
    @Modifying
    @Transactional
    @DeleteMapping("deleteStudent/{studentId}")
    public String deleteByStudentId(@PathVariable String studentId) throws UserNotFoundException {
        return studentService.deleteStudent(studentId);
    }

    //Add wallet by student ID
    @PostMapping("/studentWallet/{studentId}")
    public Map<String,String> addStudentWallet(@Valid @RequestBody StudentRequest1 studentRequest1, @PathVariable String studentId) throws UserNotFoundException {
        return studentService.addStudentWallet(studentRequest1,studentId);
    }

    @GetMapping("/studentWallet/{studentId}")
    public StudentResponse getStudentWallet(@PathVariable String studentId) throws UserNotFoundException {
        return studentService.getWallet(studentId);
    }

    @PutMapping("/updateStudent/{studentId}")
    public Map<String, String> updateStudentByStudentId(@Valid @RequestBody StudentRequestUpdate studentRequestUpdate, @PathVariable String studentId) throws UserNotFoundException {
        return studentService.updateStudent(studentRequestUpdate,studentId);

    }
}
