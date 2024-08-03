package com.studentms.controller;

import com.studentms.dto.LoginResponse;
import com.studentms.dto.UserDTO;
import com.studentms.model.User;
import com.studentms.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO){
        User registeredUser = authenticationService.signup(userDTO);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDTO userDTO){
        LoginResponse loginResponse = authenticationService.authenticate(userDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
