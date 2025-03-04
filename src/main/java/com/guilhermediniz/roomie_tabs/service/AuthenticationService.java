package com.guilhermediniz.roomie_tabs.service;

import com.guilhermediniz.roomie_tabs.dto.auth.RequestDto;
import com.guilhermediniz.roomie_tabs.dto.auth.ResponseDto;
import com.guilhermediniz.roomie_tabs.dto.user.RegisterRequestDto;
import com.guilhermediniz.roomie_tabs.dto.user.RegisterResponseDto;
import com.guilhermediniz.roomie_tabs.entity.User;
import com.guilhermediniz.roomie_tabs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailService userDetailService;

    public ResponseDto authenticate(RequestDto authRequest) {
        UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getUsername());

        if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.createJwtToken(authentication);

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ResponseDto(token, user.getUsername());
    }

    public String authenticate(Authentication authentication) {
        return jwtService.createJwtToken(authentication);
    }

    public RegisterResponseDto registerUser(RegisterRequestDto registerRequestDto) {
        Optional<User> existingUser = userRepository.findByUsername(registerRequestDto.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        return new RegisterResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                "User registered successfully"
        );
    }
}
