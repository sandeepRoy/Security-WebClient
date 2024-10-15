package com.msa.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.msa.authentication.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
