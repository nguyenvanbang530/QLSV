package com.example.qlsv.service;

import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.vo.StudentVO;


import java.util.List;
public interface StudentService {

     StudentVO createStudent(StudentDTO studentDTO);

     StudentVO updateStudent(String msv, StudentDTO studentDTO);

     List<Student> getStudent();

     StudentVO getStudenbyId(String id);

     StudentVO deleteStudent(String studentid);

     List<Student> findStudentsByHoten(String hoten);

     List<Student> findByMultipleFields(String msv, String hoten, String email);
}
