package com.example.qlsv.controller;


import com.example.qlsv.entity.StudentEntity;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.request.ApiResponese;
import com.example.qlsv.model.response.StudentResponse;
import com.example.qlsv.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @PostMapping(value = "/create")
    ApiResponese<StudentResponse> createStudent(@RequestBody @Valid StudentDTO studentDTO) {

        ApiResponese<StudentResponse> apiResponese = new ApiResponese<>();

        apiResponese.setResult(studentService.createStudent(studentDTO));
        return apiResponese;
    }

    @GetMapping(value = "/getall")
    List<StudentEntity> getStudent() {
        return studentService.getStudent();
    }

    @GetMapping(value = "/getbyid/{studentId}")
    ApiResponese<StudentResponse> getStudentbyId(@PathVariable("studentId") String studentId) {
        ApiResponese<StudentResponse> apiResponse = new ApiResponese<>();
        StudentResponse studentResponse = studentService.getStudenbyId(studentId);
        apiResponse.setResult(studentResponse);
        return apiResponse;
    }

    @PutMapping(value = "/update/{studentId}")
    ApiResponese<StudentResponse> updateStudent(@PathVariable String studentId, @RequestBody @Valid StudentDTO studentDTO) {

        ApiResponese<StudentResponse> apiResponese = new ApiResponese<>();

        StudentResponse updatedStudent = studentService.updateStudent(studentId, studentDTO);
        apiResponese.setResult(updatedStudent);
        return apiResponese;
    }

    @DeleteMapping("/delete/{studentId}")
    ApiResponese<StudentResponse> deleteStudent(@PathVariable String studentId) {
        ApiResponese<StudentResponse> apiResponse = new ApiResponese<>();

        StudentResponse deletedStudent = studentService.deleteStudent(studentId);
        apiResponse.setResult(deletedStudent);
        return apiResponse;
    }

    @GetMapping(value = "/search-name")
    public ApiResponese<List<StudentEntity>> searchStudentsByHoten(@RequestParam("hoten") String hoten) {
        ApiResponese<List<StudentEntity>> apiResponse = new ApiResponese<>();
        List<StudentEntity> studentList = studentService.findStudentsByHoten(hoten);
        apiResponse.setResult(studentList);
        return apiResponse;
    }
}

