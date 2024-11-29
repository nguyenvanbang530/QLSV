package com.example.qlsv.repository;
import com.example.qlsv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    boolean existsByMsvAndIdNot(String msv, String id);

    boolean existsByhotenAndIdNot(String hoten, String id);
    boolean existsByemailAndIdNot(String email, String id);

    List<Student> findByHotenContainingIgnoreCase(String hoten);

    boolean existsByhoten(String hoten);

    boolean existsByMsv(String msv);

    boolean existsByEmail(String email);

    @Query("SELECT s FROM Student s WHERE " +
            "(s.msv IS NULL OR LOWER(s.msv) LIKE CONCAT('%', LOWER(:msv), '%')) AND " +
            "(s.hoten IS NULL OR LOWER(s.hoten) LIKE CONCAT('%', LOWER(:hoten), '%')) AND " +
            "(s.email IS NULL OR LOWER(s.email) LIKE CONCAT('%', LOWER(:email), '%'))")
    List<Student> searchByMultipleFields(@Param("msv") String msv,
                                         @Param("hoten") String hoten,
                                         @Param("email") String email);
}
