package com.example.qlsv.service.impl;

import com.example.qlsv.entity.Student;
import com.example.qlsv.exception.AppException;
import com.example.qlsv.exception.ErrorCode;
//import com.example.qlsv.mapper.StudentMapper;
import com.example.qlsv.mapper.StudentModelMapper;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.vo.StudentVO;
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
    private StudentModelMapper studentModelMapper;

//    @Autowired
//    private StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentVO createStudent(StudentDTO studentDTO){

        if (studentRepository.existsByhoten(studentDTO.getHoten())){
            throw new AppException(ErrorCode.STUDENT_EXISTED);
        }

        if (studentRepository.existsByMsv(studentDTO.getMsv())){
            throw new AppException(ErrorCode.MSV_EXISTED);
        }

        if (studentRepository.existsByEmail(studentDTO.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        Student studentEntity = studentModelMapper.toEntity(studentDTO);

        return studentModelMapper.toVO(studentRepository.save(studentEntity));
    }
    @Override
    @Transactional
    public StudentVO updateStudent(String studentId, StudentDTO studentDTO){

        Student studentEntity = studentRepository.findById(studentId)
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

        studentModelMapper.updateEntity(studentEntity,studentDTO);

        return studentModelMapper.toVO(studentRepository.save(studentEntity));

    }
    @Override
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }
    @Override
    public StudentVO getStudenbyId(String id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NO_STUDENT_FOUND));
                return studentModelMapper.toVO(student);
    }
    @Override
    public StudentVO deleteStudent(String studentId){

        Optional<Student> studentOptional  = studentRepository.findById(studentId);

        if (studentOptional .isPresent()){
            Student studentEntity = studentOptional.get();
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
    public List<Student> findStudentsByHoten(String hoten) {

        List<Student> studentResponses = studentRepository.findByHotenContainingIgnoreCase(hoten);

        if (studentResponses.isEmpty()){
            throw new AppException(ErrorCode.NO_STUDENT_FOUND);
        }

        return studentResponses;
    }

    @Override
    public List<Student> findByMultipleFields(String msv, String hoten, String email) {

        List<Student> students = studentRepository.searchByMultipleFields(msv, hoten , email);

        if (students.isEmpty()){
            throw new AppException(ErrorCode.NO_STUDENT_FOUND);
        }
        return students;
    }
}
