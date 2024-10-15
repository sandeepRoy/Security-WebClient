package com.msa.authentication.controller;

import com.msa.authentication.entities.Student;
import com.msa.authentication.entities.User;
import com.msa.authentication.repositories.UserRepository;
import com.msa.authentication.requests.CreateStudentRequest;
import com.msa.authentication.responses.AuthResponse;
import com.msa.authentication.requests.AuthenticateRequest;
import com.msa.authentication.responses.UserProfileResponse;
import com.msa.authentication.services.AuthenticationService;
import com.msa.authentication.requests.RegisterRequest;
import com.msa.authentication.services.JwtService;
import com.msa.authentication.services.StudentService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static String whoLoggedIn = "";

    @Autowired
    public AuthenticationService authenticationService;

    @Autowired
    public StudentService studentService;

    @Autowired
    public UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticateRequest));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String token) {
        String response = authenticationService.remove(token.substring(7));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/who-logged-in")
    public ResponseEntity<String> whoLoggedIn(@RequestHeader("Authorization") String token) {
        System.out.println("TOKEN: " + token);
        whoLoggedIn = authenticationService.whoLoggedIn(token.substring(7));
        return new ResponseEntity<>(whoLoggedIn, HttpStatus.OK);
    }

    @PostMapping("/new-student")
    public ResponseEntity<Object> createStudent(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateStudentRequest createStudentRequest

    ) {
        System.out.println("Who Logged In Earlier: " + whoLoggedIn);
        System.out.println("Who is trying to Login Now: " + authenticationService.whoLoggedIn(token.substring(7)));
        System.out.println("What's in the payload: \n" + createStudentRequest.toString());
        if(whoLoggedIn.equals(authenticationService.whoLoggedIn(token.substring(7)))) {
            Student student = studentService.createStudent(createStudentRequest);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("TOKEN MISMATCH", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logged Out!";
    }
}
