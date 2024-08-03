package com.studentms.service;

import com.studentms.dto.LoginResponse;
import com.studentms.dto.UserDTO;
import com.studentms.model.User;
import com.studentms.repository.UserRepository;
import com.studentms.security.jwt.JwtHelper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    public User signup(UserDTO userDTO){
        User user = User.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getFullName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public LoginResponse authenticate(UserDTO userDTO){

        User user = userRepository.findByEmailOrUsername(userDTO.getEmail(), "")
                .orElseThrow(EntityNotFoundException::new);

        String jwtToken = null;
        if(user != null) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(),
                            userDTO.getPassword()
                    )
            );
            jwtToken = jwtHelper.generateToken(user);

        }
        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtHelper.getExpirationTime())
                .build();
    }
}
