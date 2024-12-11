package com.example.qlsv.controller;


import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.SearchStudentDTO;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.ApiResponese;
import com.example.qlsv.model.vo.StudentVO;
import com.example.qlsv.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {


    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create")
    ApiResponese<StudentVO> createStudent(@RequestBody @Valid StudentDTO studentDTO) {

        return ApiResponese.<StudentVO>builder()
                .result(studentService.createStudent(studentDTO))
                .build();
    }

    @GetMapping(value = "/getall")
    List<Student> getStudent() {
        return studentService.getStudent();
    }

    @GetMapping(value = "/getbymsv/{studentmsv}")
    ApiResponese<StudentVO> getStudentbyId(@PathVariable("studentmsv") String msv) {
        return ApiResponese.<StudentVO>builder()
                .result(studentService.getStudenbyMsv(msv))
                .build();
    }

    @PostMapping(value = "/update")
    ApiResponese<StudentVO> updateStudent(@RequestBody @Valid StudentDTO studentDTO) {

        return ApiResponese.<StudentVO>builder()
                .result(studentService.updateStudent(studentDTO.getMsv(),studentDTO))
                .build();
    }

    @PostMapping("/delete/{studentmsv}")
    ApiResponese<StudentVO> deleteStudent(@PathVariable String studentmsv) {

        return ApiResponese.<StudentVO>builder()
                .result(studentService.deleteStudent(studentmsv))
                .build();
    }

    @GetMapping(value = "/search-name")
    ApiResponese<List<Student>> searchStudentsByHoten(@RequestParam("hoten") String hoten) {

        List<Student> studentList = studentService.findStudentsByHoten(hoten);

        return ApiResponese.<List<Student>>builder()
                .result(studentList)
                .build();
    }

    @GetMapping("/search")
    public Page<Student> searchStudents(SearchStudentDTO searchStudentDTO) {
        return studentService.searchStudent(searchStudentDTO);
    }
}