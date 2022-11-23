package com.faithfulolaleru.schoolResult.services;

import com.faithfulolaleru.schoolResult.dtos.StudentRequest;
import com.faithfulolaleru.schoolResult.dtos.StudentResponse;
import com.faithfulolaleru.schoolResult.exception.ErrorResponse;
import com.faithfulolaleru.schoolResult.exception.GeneralException;
import com.faithfulolaleru.schoolResult.models.Student;
import com.faithfulolaleru.schoolResult.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public Student findStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND,
                        ErrorResponse.ERROR_STUDENT_NOT_EXIST,
                        "Student with id doesn't exist"));

        return student;
    }

    public boolean isStudentExist(Integer id) {
        return studentRepository.existsById(id);
    }

    public boolean isStudentExistByName(String name) {
        Optional<Student> optional = studentRepository.findStudentByName(name);

        if(optional.isPresent()) {
            return true;
        }
        return false;
    }

    public StudentResponse createStudent(StudentRequest requestDto) {

        boolean studentExist = isStudentExistByName(requestDto.getName());

        if(studentExist) {
            throw new GeneralException(HttpStatus.NOT_FOUND,
                    ErrorResponse.ERROR_STUDENT_ALREADY_EXIST,
                    "Student with name already exists");
        }

        Student newStudent = Student.builder()
                .name(requestDto.getName())
                .studentClass(requestDto.getStudentClass())
                .build();

        Student savedStudent = studentRepository.save(newStudent);

        return modelMapper.map(savedStudent, StudentResponse.class);
    }
}
