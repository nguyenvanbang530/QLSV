package com.example.qlsv.repository;

import com.example.qlsv.entity.Class_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Class_StudentRepository extends JpaRepository<Class_Student,String> {
}
