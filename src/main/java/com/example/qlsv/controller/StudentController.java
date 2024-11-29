package com.example.qlsv.controller;


import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.ApiResponese;
import com.example.qlsv.model.vo.StudentVO;
import com.example.qlsv.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {


    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create")
    ApiResponese<StudentVO> createStudent(@RequestBody @Valid StudentDTO studentDTO) {

        ApiResponese<StudentVO> apiResponese = new ApiResponese<>();

        apiResponese.setResult(studentService.createStudent(studentDTO));
        return apiResponese;
    }

    @GetMapping(value = "/getall")
    List<Student> getStudent() {
        return studentService.getStudent();
    }

    @GetMapping(value = "/getbyid/{studentId}")
    ApiResponese<StudentVO> getStudentbyId(@PathVariable("studentId") String msv) {
        ApiResponese<StudentVO> apiResponse = new ApiResponese<>();
        StudentVO studentResponse = studentService.getStudenbyId(msv);
        apiResponse.setResult(studentResponse);
        return apiResponse;
    }

    @PostMapping(value = "/update")
    ApiResponese<StudentVO> updateStudent(@RequestBody @Valid StudentDTO studentDTO) {

        ApiResponese<StudentVO> apiResponese = new ApiResponese<>();

        StudentVO updatedStudent = studentService.updateStudent(studentDTO.getId(), studentDTO);
        apiResponese.setResult(updatedStudent);
        return apiResponese;
    }

    @PostMapping("/delete/{studentId}")
    ApiResponese<StudentVO> deleteStudent(@PathVariable String studentId) {
        ApiResponese<StudentVO> apiResponse = new ApiResponese<>();

        StudentVO deletedStudent = studentService.deleteStudent(studentId);
        apiResponse.setResult(deletedStudent);
        return apiResponse;
    }

    @GetMapping(value = "/search-name")
    public ApiResponese<List<Student>> searchStudentsByHoten(@RequestParam("hoten") String hoten) {
        ApiResponese<List<Student>> apiResponse = new ApiResponese<>();
        List<Student> studentList = studentService.findStudentsByHoten(hoten);
        apiResponse.setResult(studentList);
        return apiResponse;
    }

    @GetMapping(value = "/search-students")
    public ApiResponese<List<Student>> findByMultipleFields(@RequestParam(value = "msv", required = false) String msv,
                                                            @RequestParam(value = "hoten", required = false) String hoten,
                                                            @RequestParam(value = "email", required = false) String email) {
        ApiResponese<List<Student>> apiResponse = new ApiResponese<>();
        List<Student> studentList = studentService.findByMultipleFields(msv, hoten , email);
        apiResponse.setResult(studentList);
        return apiResponse;
    }
}

