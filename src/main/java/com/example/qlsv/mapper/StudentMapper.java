package com.example.qlsv.mapper;

import com.example.qlsv.entity.StudentEntity;
import com.example.qlsv.model.dto.StudentDTO;

import com.example.qlsv.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentEntity toStudent(StudentDTO studentDTO);

    StudentResponse toStudentResponse(StudentEntity student);
    void updateStudent(@MappingTarget StudentEntity studentEntity, StudentDTO studentDTO);
}
