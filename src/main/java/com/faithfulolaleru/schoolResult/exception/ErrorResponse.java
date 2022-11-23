package com.faithfulolaleru.schoolResult.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    public static final String ERROR_STUDENT_NOT_EXIST = "STUDENT_DOES_NOT_EXIST";

    public static final String ERROR_STUDENT_ALREADY_EXIST = "STUDENT_ALREADY_EXISTS";


    private String error;
    private String message;
    private HttpStatus httpStatus;


    public ErrorResponse(final GeneralException ex) {
        this.httpStatus = ex.getHttpStatus();
        this.error = ex.getError();
        this.message = ex.getMessage();   //  ex.getLocalizedMessage()
    }
}
