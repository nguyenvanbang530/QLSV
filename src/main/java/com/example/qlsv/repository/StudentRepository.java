package com.example.qlsv.repository;
import com.example.qlsv.entity.StudentEntity;
import com.example.qlsv.model.dto.StudentDTO;
import com.example.qlsv.model.response.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {

    boolean existsByMsvAndIdNot(String msv, String id);

    boolean existsByhotenAndIdNot(String hoten, String id);
    boolean existsByemailAndIdNot(String email, String id);

    List<StudentEntity> findByHotenContainingIgnoreCase(String hoten);

    boolean existsByhoten(String hoten);

    boolean existsByMsv(String msv);

    boolean existsByEmail(String email);
}
