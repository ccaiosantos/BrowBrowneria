package com.BrowBrowneria.BrowBrowneria.dto.auth;

import com.BrowBrowneria.BrowBrowneria.enums.PerfilUsuario;

public record AuthResponse(
        String token,
        Long usuarioId,
        String nome,
        String email,
        PerfilUsuario perfil
) {
}
