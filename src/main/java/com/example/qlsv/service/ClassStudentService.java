package com.example.qlsv.service;

import com.example.qlsv.entity.ClassStudent;
import com.example.qlsv.model.dto.ClassStudentDTO;

import java.util.List;

public interface ClassStudentService {

     ClassStudent addStudentToClass(ClassStudentDTO classStudentDTO);

     List<Object[]> findStudentsByClassId(String classId);

     List<ClassStudent> getall();
}
