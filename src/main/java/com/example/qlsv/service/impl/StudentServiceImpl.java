package com.example.qlsv.service.impl;

import com.example.qlsv.entity.StudentEntity;
import com.example.qlsv.exception.AppException;
import com.example.qlsv.exception.ErrorCode;
import com.example.qlsv.mapper.StudentMapper;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.response.StudentResponse;
import com.example.qlsv.repository.StudentRepository;
import com.example.qlsv.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentResponse createStudent(StudentDTO studentDTO){

        if (studentRepository.existsByhoten(studentDTO.getHoten())){
            throw new AppException(ErrorCode.STUDENT_EXISTED);
        }

        if (studentRepository.existsByMsv(studentDTO.getMsv())){
            throw new AppException(ErrorCode.MSV_EXISTED);
        }

        if (studentRepository.existsByEmail(studentDTO.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        StudentEntity studentEntity = studentMapper.toStudent(studentDTO);

        return studentMapper.toStudentResponse(studentRepository.save(studentEntity));
    }
    @Override
    @Transactional
    public StudentResponse updateStudent(String studentId, StudentDTO studentDTO){

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new AppException(ErrorCode.NO_STUDENT_FOUND));

        boolean duplicateMsvExists = studentRepository.existsByMsvAndIdNot(studentDTO.getMsv(), studentId);
        if (duplicateMsvExists) {
            throw new AppException(ErrorCode.DUPLICATE_STUDENT_MSV);
        }

        boolean duplicatehotenExists = studentRepository.existsByhotenAndIdNot(studentDTO.getHoten(), studentId);
        if (duplicatehotenExists) {
            throw new AppException(ErrorCode.DUPLICATE_STUDENT_HO_TEN);
        }

        boolean duplicateemailExists = studentRepository.existsByemailAndIdNot(studentDTO.getEmail(), studentId);
        if (duplicateemailExists) {
            throw new AppException(ErrorCode.DUPLICATE_STUDENT_EMAIL);
        }

        studentMapper.updateStudent(studentEntity,studentDTO);

        return studentMapper.toStudentResponse(studentRepository.save(studentEntity));

    }
    @Override
    public List<StudentEntity> getStudent(){
        return studentRepository.findAll();
    }
    @Override
    public StudentResponse getStudenbyId(String id){
        return studentMapper.toStudentResponse(studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NO_STUDENT_FOUND)));
    }
    @Override
    public StudentResponse deleteStudent(String studentId){

        Optional<StudentEntity> studentOptional  = studentRepository.findById(studentId);

        if (studentOptional .isPresent()){
            StudentEntity studentEntity = studentOptional.get();
            if (studentEntity.getStatus() == 0){
                studentRepository.deleteById(studentId);
            }else {
                throw new AppException(ErrorCode.CANNOT_DELETE_STUDENT_WHIT_STATUS_IS_1);
            }
        }else {
            throw new AppException(ErrorCode.NO_STUDENT_FOUND);
        }
        return null;
    }

    @Override
    public List<StudentEntity> findStudentsByHoten(String hoten) {

        List<StudentEntity> studentResponses = studentRepository.findByHotenContainingIgnoreCase(hoten);

        if (studentResponses.isEmpty()){
            throw new AppException(ErrorCode.NO_STUDENT_FOUND);
        }

        return studentResponses;
    }
}
