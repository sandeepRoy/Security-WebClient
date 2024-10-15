package com.msa.authclient.service;

import com.msa.authclient.endpoint.Endpoint;
import com.msa.authclient.request.CreateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthClientService {

    @Autowired
    public WebClient authClient;

    // GET - String, Username of the user logged in
    public String whoLoggedIn(String token) {
        String whoLoggedIn = authClient
                .get()
                .uri(Endpoint.who_logged_in)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return whoLoggedIn;
    }

    // POST - Student, Create Student for Logged In User
    public Object createStudent(String token, CreateStudentRequest createStudentRequest) {
        Object new_student = authClient
                .post()
                .uri(Endpoint.new_student)
                .header("Authorization", "Bearer " + token)
                .bodyValue(createStudentRequest)
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        return new_student;
    }

}
