package com.msa.authentication.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {
    private String student_name;

    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "student_name='" + student_name + '\'' +
                '}';
    }
}
