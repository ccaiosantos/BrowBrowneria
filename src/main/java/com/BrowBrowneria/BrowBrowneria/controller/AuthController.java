package com.BrowBrowneria.BrowBrowneria.controller;

import com.BrowBrowneria.BrowBrowneria.dto.auth.AuthResponse;
import com.BrowBrowneria.BrowBrowneria.dto.auth.CadastroRequest;
import com.BrowBrowneria.BrowBrowneria.dto.auth.LoginRequest;
import com.BrowBrowneria.BrowBrowneria.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse cadastrar(@Valid @RequestBody CadastroRequest request) {
        return authService.cadastrar(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
