package com.example.qlsv.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchStudentDTO {

    @NotNull
    @Min(value = 0, message = "Page must be greater than 0")
    private Integer page;

    @NotNull
    @Min(value = 0, message = "Size must be greater than 0")
    private Integer size;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String msv;
    private String hoten;
    private String email;

    public SearchStudentDTO(){
        this.page = 0;
        this.size = 10;
    }
}