package com.example.qlsv.model.dto;

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
public class ClassDTO {

    String id;
    @Size(min = 3,message = "Tên Lớp không được dưới 3 kí tự")
    String tenlop;

    String mota;

    Date createdAT;

    Date updatedAT;

    int status;
}
