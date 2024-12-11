package com.example.qlsv.service;

import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.SearchStudentDTO;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.vo.StudentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
public interface StudentService {

     StudentVO createStudent(StudentDTO studentDTO);

     StudentVO updateStudent(String msv, StudentDTO studentDTO);

     List<Student> getStudent();

     StudentVO getStudenbyMsv(String msv);

     StudentVO deleteStudent(String msv);

     List<Student> findStudentsByHoten(String hoten);

     Page<Student> searchStudent(SearchStudentDTO searchStudentDTO);
}
