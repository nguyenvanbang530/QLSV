package com.example.qlsv.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentVO {

    String msv;

    String hoten;

    String ngaysinh;

    String gioitinh;

    String diachi;

    String email;

    String sdt;

    Date createdAT;

    Date updatedAT;

    int status;
}
