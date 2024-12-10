package com.example.qlsv.service.impl;

import com.example.qlsv.entity.ClassEntity;
import com.example.qlsv.entity.ClassStudent;
import com.example.qlsv.entity.Student;
import com.example.qlsv.model.dto.ClassStudentDTO;
import com.example.qlsv.repository.ClassRepository;
import com.example.qlsv.repository.ClassStudentRepository;
import com.example.qlsv.repository.StudentRepository;
import com.example.qlsv.service.ClassStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassStudentServiceImpl implements ClassStudentService {

    @Autowired
    private ClassStudentRepository classStudentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public ClassStudent addStudentToClass(ClassStudentDTO classStudentDTO) {

        ClassEntity classEntity = classRepository.findById(classStudentDTO.getClassId()).orElse(null);

        if (classEntity ==  null){
            throw new RuntimeException("không tìm thấy lớp học");
        }

        Student student = studentRepository.findById(classStudentDTO.getStudentId()).orElse(null);

        if (student ==  null){
            throw new RuntimeException("không tìm thấy sv");
        }

        if (classStudentRepository.existsByClassIdAndStudentId(classStudentDTO.getClassId(), classStudentDTO.getStudentId())){
            throw new RuntimeException("Sinh viên đã tồn tại trong lớp");
        }

        ModelMapper mapper = new ModelMapper();

        ClassStudent classStudent = mapper.map(classStudentDTO,ClassStudent.class);
        classStudent.setId(UUID.randomUUID().toString());
        classStudentRepository.save(classStudent);
        return classStudent;
    }

    @Override
    public List<Object[]> findStudentsByClassId(String classId) {
        return classStudentRepository.findStudentsByClassId(classId);
    }

    @Override
    public List<ClassStudent> getall() {
        return classStudentRepository.findAll();
    }


}
