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
    @Table(name = "class_student")
    public class ClassStudent {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id")
        String id;

        @Column(name = "class_id")
        String classId;

        @Column(name = "student_id")
        String studentId;

        @Column(name = "created_at")
        Date createdAT;

        @Column(name = "updated_at")
        Date updatedAT;

        @Column(name = "status")
        int status;
        }
