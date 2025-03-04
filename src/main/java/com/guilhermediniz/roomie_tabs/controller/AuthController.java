package com.guilhermediniz.roomie_tabs.controller;

import com.guilhermediniz.roomie_tabs.dto.auth.ResponseDto;
import com.guilhermediniz.roomie_tabs.dto.auth.RequestDto;
import com.guilhermediniz.roomie_tabs.dto.user.RegisterRequestDto;
import com.guilhermediniz.roomie_tabs.dto.user.RegisterResponseDto;
import com.guilhermediniz.roomie_tabs.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticate(@Valid @RequestBody RequestDto authRequest) {
        ResponseDto response = authenticationService.authenticate(authRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        RegisterResponseDto response = authenticationService.registerUser(registerRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
