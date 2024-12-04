package com.example.qlsv.service;

import com.example.qlsv.model.dto.ClassDTO;
import com.example.qlsv.model.vo.ClassVO;

public interface ClassService {

    ClassVO createClass(ClassDTO classDTO);
}
