package com.equipeor.isepu.controller;

import com.equipeor.isepu.payload.response.ApiResponse;
import com.equipeor.isepu.payload.response.JwtAuthenticationResponse;
import com.equipeor.isepu.payload.request.LoginRequest;
import com.equipeor.isepu.payload.request.SignUpRequest;
import com.equipeor.isepu.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new JwtAuthenticationResponse(authenticationService.authenticateUser(loginRequest)));
    }

    @PutMapping("/professor")
    public ResponseEntity<?> registerProfessor(@Valid @RequestBody SignUpRequest signUpRequest) {
        URI location = null;
        ApiResponse response = authenticationService.registerUser(signUpRequest, location);
        if (!response.getSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PutMapping("/student")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody SignUpRequest signUpRequest) {
        URI location = null;
        ApiResponse response = authenticationService.registerUser(signUpRequest, location);
        if (!response.getSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> checkEmailAvailability (@PathVariable String email) {
        boolean available = authenticationService.checkEmailAvailability(email);
        return available ? ResponseEntity.ok().body(available) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
