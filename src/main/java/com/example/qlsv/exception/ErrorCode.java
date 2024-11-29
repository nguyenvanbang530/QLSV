    package com.example.qlsv.exception;

    import lombok.AccessLevel;
    import lombok.Getter;
    import lombok.experimental.FieldDefaults;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.HttpStatusCode;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    public enum ErrorCode {
        STUDENT_EXISTED(400,"sinh vien da ton tai",HttpStatus.BAD_REQUEST),
        MSV_EXISTED(400,"mã sinh vien da ton tai",HttpStatus.BAD_REQUEST),
        EMAIL_EXISTED(400,"email sinh vien da ton tai",HttpStatus.BAD_REQUEST),
        INVALID_PHONE_NUMBER(400,"Số điện thoại không hợp lệ",HttpStatus.BAD_REQUEST),
        EMAIL_INVALID(400," email không hợp lệ",HttpStatus.BAD_REQUEST),
        DIA_CHI_NOTNULL(400,"Dia chi khong duoc de trong",HttpStatus.BAD_REQUEST),
        HO_TEN_INVALID(400,"Tên phải dài hơn 3 ký tự",HttpStatus.BAD_REQUEST),
        NGAY_SINH_NOTNULL(400,"Ngày sinh không được để trống",HttpStatus.BAD_REQUEST),
        GIOI_TINH_NOTNULL(400,"giới tính không được để trống",HttpStatus.BAD_REQUEST),
        INVALID_MSV(400,"mã sinh viên phải là số",HttpStatus.BAD_REQUEST),
        INVALID_KEY(400,"Invalid message key",HttpStatus.BAD_REQUEST),
        CANNOT_DELETE_STUDENT_WHIT_STATUS_IS_1(400,"Không thể xóa sinh viên với trạng thái là 1",HttpStatus.BAD_REQUEST),
        NO_STUDENT_FOUND(404,"Không tìm thấy sinh viên ",HttpStatus.NOT_FOUND),
        DUPLICATE_STUDENT_MSV(400,"Trùng mã sinh viên",HttpStatus.BAD_REQUEST),
        DUPLICATE_STUDENT_HO_TEN(400,"Trùng Họ Tên sinh viên",HttpStatus.BAD_REQUEST),
        DUPLICATE_STUDENT_EMAIL(400,"Trùng Email sinh viên",HttpStatus.BAD_REQUEST),
        MSV_NOTNULL(400,"msv không được để trống",HttpStatus.BAD_REQUEST),
      ;

        ErrorCode(int code, String message, HttpStatusCode statusCode) {
            this.code = code;
            this.message = message;
            this.statusCode = statusCode;
        }

        int code;
        String message;
        HttpStatusCode statusCode;

    }
