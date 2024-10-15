package com.msa.authclient.controller;

import com.msa.authclient.request.CreateStudentRequest;
import com.msa.authclient.service.AuthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth-client")
public class AuthClientController {

    private static String whoLoggedIn = "";

    @Autowired
    public AuthClientService authClientService;

    @GetMapping("/who-logged-in")
    public ResponseEntity<String> whoLoggedIn(@RequestHeader("Authorization") String token) {
        String whoLoggedIn = authClientService.whoLoggedIn(token.substring(7));
        return new ResponseEntity<>(whoLoggedIn, HttpStatus.OK);
    }

    @PostMapping("/new-student")
    public ResponseEntity<Object> newStudent(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateStudentRequest createStudentRequest
    ) {
        Object student = authClientService.createStudent(token.substring(7), createStudentRequest);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
