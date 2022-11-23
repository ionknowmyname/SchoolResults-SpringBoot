package com.faithfulolaleru.schoolResult.controllers;

import com.faithfulolaleru.schoolResult.dtos.StudentRequest;
import com.faithfulolaleru.schoolResult.dtos.StudentResponse;
import com.faithfulolaleru.schoolResult.response.AppResponse;
import com.faithfulolaleru.schoolResult.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/")
    public AppResponse<?> createStudent(@RequestBody StudentRequest requestDto) {

        StudentResponse response = studentService.createStudent(requestDto);

        return AppResponse.builder()
                .status(AppResponse.ResponseStatus.SUCCESSFUL)
                .message("Created Student Successfully")
                .data(response)
                .build();
    }
}
