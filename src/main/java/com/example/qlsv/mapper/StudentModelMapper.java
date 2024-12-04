package com.example.qlsv.mapper;

import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.vo.StudentVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Student toEntity(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, Student.class);
    }

    public StudentVO toVO(Student student){
        return modelMapper.map(student, StudentVO.class);
    }
    public void updateEntity(Student student, StudentDTO studentDTO) {
        if (studentDTO.getHoten() != null) {
            student.setHoten(studentDTO.getHoten());
        }
        if (studentDTO.getMsv() != null) {
            student.setMsv(studentDTO.getMsv());
        }
        if (studentDTO.getEmail() != null) {
            student.setEmail(studentDTO.getEmail());
        }
    }
}
