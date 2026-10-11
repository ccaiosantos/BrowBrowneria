package com.BrowBrowneria.BrowBrowneria.service;

import com.BrowBrowneria.BrowBrowneria.dto.auth.AuthResponse;
import com.BrowBrowneria.BrowBrowneria.dto.auth.CadastroRequest;
import com.BrowBrowneria.BrowBrowneria.dto.auth.LoginRequest;
import com.BrowBrowneria.BrowBrowneria.entity.Usuario;
import com.BrowBrowneria.BrowBrowneria.enums.PerfilUsuario;
import com.BrowBrowneria.BrowBrowneria.repository.UsuarioRepository;
import com.BrowBrowneria.BrowBrowneria.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse cadastrar(CadastroRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email().toLowerCase())
                .senha(passwordEncoder.encode(request.senha()))
                .telefone(request.telefone())
                .perfil(PerfilUsuario.ROLE_CLIENTE)
                .build();

        usuario = usuarioRepository.save(usuario);
        return toAuthResponse(usuario);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));

        return toAuthResponse(usuario);
    }

    private AuthResponse toAuthResponse(Usuario usuario) {
        return new AuthResponse(
                jwtService.generateToken(usuario),
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil()
        );
    }
}
