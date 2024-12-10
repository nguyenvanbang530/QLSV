package com.example.qlsv.controller;

import com.example.qlsv.entity.ClassEntity;
import com.example.qlsv.model.dto.ClassDTO;
import com.example.qlsv.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {


    private ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping(value = "/create")
    ClassEntity createClass(@RequestBody @Valid ClassDTO classDTO){
        return classService.createClass(classDTO);
    }

    @PostMapping(value = "/update")
    ClassEntity updateClass(@RequestBody @Valid ClassDTO classDTO){
        return classService.updateClass(classDTO);
    }

    @PostMapping(value = "/delete/{classid}")
    ClassEntity deleteClass(@PathVariable String classid){
        return classService.deleteClass(classid);
    }

    @GetMapping("/getall")
    List<ClassEntity> getallClass(){
        return classService.getallClass();
    }

    @GetMapping(value = "/getallClass-Student")
    public List<Map<String,Object>> getClassAndStudents(){
        return classService.getClassAndStudents();
    }

}
