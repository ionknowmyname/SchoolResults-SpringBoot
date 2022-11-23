package com.faithfulolaleru.schoolResult.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Integer id;
    private String name;
    private String studentClass;
    private Instant createdAt;

}
