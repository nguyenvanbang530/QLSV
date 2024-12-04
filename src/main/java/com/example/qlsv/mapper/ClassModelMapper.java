package com.example.qlsv.mapper;

import com.example.qlsv.entity.Class;
import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.ClassDTO;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.vo.StudentVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Class toEntity(ClassDTO classDTO){
        return modelMapper.map(classDTO, Class.class);
    }

    public StudentVO toVO(Student student){
        return modelMapper.map(student, StudentVO.class);
    }
}
