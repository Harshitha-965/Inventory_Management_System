package com.harshitha.inventory_management_backend.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.harshitha.inventory_management_backend.security.dto.AuthenticationResponse;
import com.harshitha.inventory_management_backend.security.dto.LoginRequest;
import com.harshitha.inventory_management_backend.security.dto.RegisterRequest;
import com.harshitha.inventory_management_backend.security.service.AuthenticationService;
import com.harshitha.inventory_management_backend.util.ApiResponse;
import com.harshitha.inventory_management_backend.util.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(
            @RequestBody RegisterRequest request) {

        return ResponseBuilder.success(
                authenticationService.register(request),
                "User registered successfully",
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(
            @RequestBody LoginRequest request) {

        return ResponseBuilder.success(
                authenticationService.login(request),
                "Login successful",
                HttpStatus.OK);
    }
}