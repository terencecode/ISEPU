package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.payload.UserRequest;
import com.equipeor.isepu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getCurrentUser(userPrincipal));
    }

    @Secured("ROLE_USER")
    @PutMapping
    public ResponseEntity<?> updateCurrentUser(@RequestBody UserRequest userRequest, @CurrentUser UserPrincipal userPrincipal) {
        userService.updateCurrentUser(userRequest, userPrincipal);
        return ResponseEntity.ok().build();
    }
}
