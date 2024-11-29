package com.example.qlsv.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class StudentDTO {

    String id;

    @Pattern(regexp = "^[0-9]*$", message = "INVALID_MSV")
    @NotNull(message = " MSV_NOTNULL")
    String msv;

    @Size(min = 3,message = "HO_TEN_INVALID")
    String hoten;

    @NotNull(message = " NGAY_SINH_NOTNULL")
    String ngaysinh;

    @NotNull(message = " GIOI_TINH_NOTNULL")
    String gioitinh;

    @NotNull(message = " DIA_CHI_NOTNULL")
    String diachi;

    @Email(message = "EMAIL_INVALID")
    String email;

    @Pattern(regexp = "^[0-9]*$", message = "INVALID_PHONE_NUMBER")
    @Size(min = 10, message = "INVALID_PHONE_NUMBER")
    String sdt;

    Date createdAT;

    Date updatedAT;

    int status;
}
