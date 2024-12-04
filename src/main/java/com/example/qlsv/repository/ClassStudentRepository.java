package com.example.qlsv.repository;

import com.example.qlsv.entity.ClassStudent;
import com.example.qlsv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassStudentRepository extends JpaRepository<ClassStudent, String> {

    boolean existsByClassIdAndStudentId(String classId, String studentId);

    @Query("SELECT s.id, s.msv, s.hoten " +
            "FROM Student s " +
            "JOIN ClassStudent cs ON s.id = cs.studentId " +
            "JOIN ClassEntity c ON c.id = cs.classId " +
            "WHERE c.id = :classId")
    List<Object[]> findStudentsByClassId(@Param("classId") String classId);
}