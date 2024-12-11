package com.example.qlsv.service.impl;

import com.example.qlsv.entity.Student;
import com.example.qlsv.exception.AppException;
import com.example.qlsv.exception.ErrorCode;
//import com.example.qlsv.mapper.StudentMapper;

import com.example.qlsv.model.dto.SearchStudentDTO;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.vo.StudentVO;
import com.example.qlsv.repository.StudentRepository;
import com.example.qlsv.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


//    @Autowired
//    private StudentMapper studentMapper;
    ModelMapper mapper = new ModelMapper();

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
            StudentVO studentVO = mapper.map(studentDTO,StudentVO.class);

            return studentVO;
    }
    @Override
    @Transactional
    public StudentVO updateStudent(String studentmsv, StudentDTO studentDTO){

        Student studentEntity = studentRepository.findByMsv(studentmsv)
                .orElseThrow(() -> new AppException(ErrorCode.NO_STUDENT_FOUND));

        boolean duplicatehotenExists = studentRepository.existsByhotenAndIdNot(studentDTO.getHoten(), studentmsv);
        if (duplicatehotenExists) {
            throw new AppException(ErrorCode.DUPLICATE_STUDENT_HO_TEN);
        }

        boolean duplicateemailExists = studentRepository.existsByemailAndIdNot(studentDTO.getEmail(), studentmsv);
        if (duplicateemailExists) {
            throw new AppException(ErrorCode.DUPLICATE_STUDENT_EMAIL);
        }

        StudentVO studentVO = mapper.map(studentDTO,StudentVO.class);

        return studentVO;

    }
    @Override
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }
    @Override
    public StudentVO getStudenbyMsv(String msv){

        ModelMapper mapper = new ModelMapper();

        Student student = studentRepository.findByMsv(msv)
                .orElseThrow(() -> new AppException(ErrorCode.NO_STUDENT_FOUND));

        StudentVO studentVO = mapper.map(student,StudentVO.class);
                return studentVO;
    }
    @Override
    public StudentVO deleteStudent(String msv){

        Optional<Student> studentOptional  = studentRepository.findByMsv(msv);

        if (studentOptional .isPresent()){
            Student studentEntity = studentOptional.get();
            if (studentEntity.getStatus() == 0){
                studentRepository.delete(studentEntity);
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
    public Page<Student> searchStudent(SearchStudentDTO searchStudentDTO) {
        String msv = searchStudentDTO.getMsv();
        String hoten = searchStudentDTO.getHoten();
        String email = searchStudentDTO.getEmail();

        int page = searchStudentDTO.getPage();
        int size = searchStudentDTO.getSize();

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository.search(msv, hoten, email, pageable);
    }
}