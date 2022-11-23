package com.faithfulolaleru.schoolResult.repositories;

import com.faithfulolaleru.schoolResult.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findStudentByName(String name);
}
