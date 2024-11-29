package com.example.qlsv.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(unique = true, name = "msv", nullable = false)
    String msv;

    @Column(unique = true, name = "hoten", nullable = false)
    String hoten;

    @Column(name = "ngaysinh", nullable = false)
    String ngaysinh;

    @Column(name = "gioitinh", nullable = false)
    String gioitinh;

    @Column(name = "diachi", nullable = false)
    String diachi;

    @Column(unique = true, name = "email")
    String email;

    @Column(name = "sdt", nullable = false)
    String sdt;

    @Column(name = "created_at")
    Date createdAT;

    @Column(name = "updated_at")
    Date updatedAT;

    @Column(name = "status")
    int status;

}
