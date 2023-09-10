package com.example.demo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.security.payload.AuthRequest;
import com.example.demo.security.payload.AuthResponse;
import com.example.demo.services.IUserService;
import com.example.demo.ultility.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessString(user);
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User request) {
        try {
            AuthResponse authResponse = userService.createUser(request);
            return ResponseEntity.ok().body(authResponse);
        } catch (Exception e) {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
