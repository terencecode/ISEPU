package com.equipeor.isepu.configuration;

import com.equipeor.isepu.model.User;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user;
        try {
            user = studentRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with email : " + email)
                    );
        } catch (UsernameNotFoundException e) {
            user = professorRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with email : " + email)
                    );
        }

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user;
        try {
            user = studentRepository.findById(id)
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User not found with id : " + id)
                    );
        } catch (UsernameNotFoundException e) {
            user = professorRepository.findById(id)
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User not found with id : " + id)
                    );
        }


        return UserPrincipal.create(user);
    }
}
