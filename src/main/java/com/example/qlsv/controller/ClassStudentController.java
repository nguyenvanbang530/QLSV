package com.example.qlsv.controller;

import com.example.qlsv.entity.ClassStudent;
import com.example.qlsv.model.dto.ClassStudentDTO;
import com.example.qlsv.service.ClassStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/class-student")
public class ClassStudentController {

    private ClassStudentService classStudentService;

    @Autowired
    public ClassStudentController(ClassStudentService classStudentService) {
        this.classStudentService = classStudentService;
    }

    @PostMapping(value = "/create")
    public ClassStudent createStudentToClass(@RequestBody @Valid ClassStudentDTO classStudentDTO){
        return classStudentService.addStudentToClass(classStudentDTO);
    }

    @GetMapping(value = "/getStudentsByClass/{classId}")
    public List<Object[]> getStudentsByClassId(@PathVariable String classId){
        return classStudentService.findStudentsByClassId(classId);
    }

    @GetMapping(value = "/getall")
    public List<ClassStudent> getall(){
        return classStudentService.getall();
    }

    @PostMapping(value = "/update")
    public ClassStudent update(){
        return null;
    }
}
