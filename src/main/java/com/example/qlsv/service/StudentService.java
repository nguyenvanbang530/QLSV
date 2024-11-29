package com.example.qlsv.service;

import com.example.qlsv.entity.StudentEntity;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.response.StudentResponse;


import java.util.List;
public interface StudentService {

     StudentResponse createStudent(StudentDTO studentDTO);

     StudentResponse updateStudent(String studentid, StudentDTO studentDTO);

     List<StudentEntity> getStudent();

     StudentResponse getStudenbyId(String id);

     StudentResponse deleteStudent(String studentid);

     List<StudentEntity> findStudentsByHoten(String hoten);

}
