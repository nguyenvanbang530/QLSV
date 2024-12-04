package com.example.qlsv.repository;

import com.example.qlsv.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, String> {
    @Query("SELECT c.id, c.tenlop, s.id, s.msv, s.hoten " +
            "FROM ClassEntity c " +
            "LEFT JOIN ClassStudent cs ON c.id = cs.classId " +
            "LEFT JOIN Student s ON cs.studentId = s.id ")
    List<Object[]> getClassAndStudents();
}

