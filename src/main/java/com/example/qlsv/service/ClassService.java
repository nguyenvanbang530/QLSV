package com.example.qlsv.service;

import com.example.qlsv.entity.ClassEntity;
import com.example.qlsv.entity.ClassStudent;
import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.ClassDTO;
import com.example.qlsv.model.dto.ClassStudentDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface ClassService {

    ClassEntity createClass(ClassDTO classDTO);

    ClassEntity updateClass(ClassDTO classDTO);

    List<ClassEntity> getallClass();

    List<Map<String, Object>> getClassAndStudents();

    ClassEntity deleteClass(String id);

}
