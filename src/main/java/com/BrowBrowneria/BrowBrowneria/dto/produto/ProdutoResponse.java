package com.BrowBrowneria.BrowBrowneria.dto.produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        BigDecimal precoPromocional,
        Boolean emPromocao,
        String imagemUrl,
        Boolean ativo,
        String categoria
) {
}
