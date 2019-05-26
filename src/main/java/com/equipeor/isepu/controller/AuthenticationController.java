package com.equipeor.isepu.controller;

import com.equipeor.isepu.payload.ApiResponse;
import com.equipeor.isepu.payload.JwtAuthenticationResponse;
import com.equipeor.isepu.payload.LoginRequest;
import com.equipeor.isepu.payload.SignUpRequest;
import com.equipeor.isepu.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        URI location = null;
        ApiResponse response = authenticationService.registerUser(signUpRequest, location);
        if (!response.getSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
