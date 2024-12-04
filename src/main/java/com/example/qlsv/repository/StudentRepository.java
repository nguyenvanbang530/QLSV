package com.example.qlsv.repository;
import com.example.qlsv.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    Optional<Student> findByMsv(String msv);
    boolean existsByMsvAndIdNot(String msv, String id);

    boolean existsByhotenAndIdNot(String hoten, String id);
    boolean existsByemailAndIdNot(String email, String id);

    List<Student> findByHotenContainingIgnoreCase(String hoten);

    boolean existsByhoten(String hoten);

    boolean existsByMsv(String msv);

    boolean existsByEmail(String email);

    @Query("SELECT s FROM Student s WHERE " +
            "(s.msv IS NULL OR (s.msv) LIKE CONCAT('%', (:msv), '%')) AND " +
            "(s.hoten IS NULL OR (s.hoten) LIKE CONCAT('%', (:hoten), '%')) AND " +
            "(s.email IS NULL OR (s.email) LIKE CONCAT('%', (:email), '%'))")
    Page<Student> searchByMultipleFields(@Param("msv") String msv,
                                         @Param("hoten") String hoten,
                                         @Param("email") String email, Pageable pageable);
}
