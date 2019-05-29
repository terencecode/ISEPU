package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.JwtTokenProvider;
import com.equipeor.isepu.exception.AppException;
import com.equipeor.isepu.model.*;
import com.equipeor.isepu.payload.response.ApiResponse;
import com.equipeor.isepu.payload.request.LoginRequest;
import com.equipeor.isepu.payload.request.SignUpRequest;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.RoleRepository;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Set;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    ProfessorRepository professorRepository;

    public ApiResponse registerUser(SignUpRequest signUpRequest, URI location) {

        if(professorRepository.existsByEmail(signUpRequest.getEmail()) || studentRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ApiResponse(false, "Email Address already in use!");
        }

        long userId;

        if (signUpRequest.getPromo() != null) {
            userId = registerStudent(signUpRequest);
        } else {
            userId = registerProfessor(signUpRequest);
        }


        location = (fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(userId).toUri());

        return new ApiResponse(true, "User registered successfully");
    }

    private long registerStudent(SignUpRequest signUpRequest) {

        Student student = new Student(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getPromo());

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        addRole(student, RoleName.ROLE_USER);
        Student result = studentRepository.save(student);
        return result.getId();
    }

    private long registerProfessor(SignUpRequest signUpRequest) {

        Professor professor = new Professor(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        professor.setPassword(passwordEncoder.encode(professor.getPassword()));

        addRole(professor, RoleName.ROLE_USER);
        addRole(professor, RoleName.ROLE_PROFESSOR);
        Professor result = professorRepository.save(professor);
        return result.getId();
    }

    private void addRole(User user, RoleName roleName) {
        Role userRole = roleRepository.findByName(roleName)
                .orElseThrow(() -> new AppException("User Role not set."));
        Set<Role> roles = user.getRoles();
        roles.add(userRole);
        user.setRoles(roles);
    }

    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }

    public boolean checkEmailAvailability (String email) {
        return !professorRepository.findByEmail(email).isPresent() && !studentRepository.findByEmail(email).isPresent();
    }
}
