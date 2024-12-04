package com.example.qlsv.service.impl;

import com.example.qlsv.model.dto.ClassDTO;
import com.example.qlsv.model.vo.ClassVO;
import com.example.qlsv.repository.ClassRepository;
import com.example.qlsv.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServicelmpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public ClassVO createClass(ClassDTO classDTO) {
        return null;
    }
}
