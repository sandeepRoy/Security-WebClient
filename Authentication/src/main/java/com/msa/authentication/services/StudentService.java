package com.msa.authentication.services;

import com.msa.authentication.entities.Student;
import com.msa.authentication.repositories.StudentRepository;
import com.msa.authentication.requests.CreateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    public StudentRepository studentRepository;

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setStudent_name(createStudentRequest.getStudent_name());
        Student save = studentRepository.save(student);
        return save;
    }
}
