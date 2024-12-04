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
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(unique = true, name = "tenlop", nullable = false)
    String tenlop;

    @Column(name = "mota", nullable = false)
    String mota;

    @Column(name = "created_at")
    Date createdAT;

    @Column(name = "updated_at")
    Date updatedAT;

    @Column(name = "status")
    int status;

}
