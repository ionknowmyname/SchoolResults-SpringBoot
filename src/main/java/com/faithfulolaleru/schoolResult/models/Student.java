package com.faithfulolaleru.schoolResult.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends Audit implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "student_class")
    private String studentClass;
}
