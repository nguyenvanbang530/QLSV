package com.example.qlsv.service.impl;

import com.example.qlsv.entity.ClassEntity;
import com.example.qlsv.model.dto.ClassDTO;
import com.example.qlsv.repository.ClassRepository;
import com.example.qlsv.service.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClassServicelmpl implements ClassService {

    @Autowired
    ClassRepository classRepository;



    @Override
    @Transactional
    public ClassEntity createClass(ClassDTO classDTO) {

        ModelMapper mapper = new ModelMapper();
        ClassEntity classEntity = mapper.map(classDTO, ClassEntity.class);
        classEntity.setId(UUID.randomUUID().toString());
        classRepository.save(classEntity);
        return  classEntity;
    }



    @Override
    @Transactional
    public ClassEntity updateClass(ClassDTO classDTO) {

        ModelMapper mapper = new ModelMapper();
        Optional<ClassEntity> classDTO1 = classRepository.findById(classDTO.getId());
        if (classDTO1.isEmpty()){
            throw new RuntimeException("id khong ton tai");
        }
        ClassEntity classEntity = mapper.map(classDTO, ClassEntity.class);
        classRepository.save(classEntity);
        return classEntity;
    }

    @Override
    public List<ClassEntity> getallClass() {
        return classRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> getClassAndStudents() {

        List<Object[]> results  = classRepository.getClassAndStudents();
        List<Map<String,Object>> resultList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("classId", result[0]);
            resultMap.put("className", result[1]);
            resultMap.put("studentId", result[2]);
            resultMap.put("studentMsv", result[3]);
            resultMap.put("studentName", result[4]);
            resultList.add(resultMap);
        }

        return resultList;
    }

    @Override
    public ClassEntity deleteClass(String id) {

        Optional<ClassEntity> classOptional = classRepository.findById(id);
        if (classOptional.isPresent()) {
            ClassEntity classEntity = classOptional.get();
            if (classEntity.getStatus() == 0) {
                classRepository.delete(classEntity);
            } else {
                throw new RuntimeException("không thể xóa lớp có status là 1");
            }
        } else{
            throw new RuntimeException("Không tìm thấy id của class");
        }
        return null;
    }

}
