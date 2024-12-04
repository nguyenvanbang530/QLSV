package com.example.qlsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class,String> {

    @Query("SELECT s.id as student_id, s.msv FROM Class c " +
            "JOIN Class_Student cs ON c.id = cs.class_id " +
            "JOIN Student s ON cs.student_id = s.id")
    List<Object[]> findStudentIdAndMsv();
}

