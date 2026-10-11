package com.BrowBrowneria.BrowBrowneria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @Column(precision = 12, scale = 2)
    private BigDecimal precoPromocional;

    @Column(nullable = false)
    @Builder.Default
    private Boolean emPromocao = false;

    @Column(length = 500)
    private String imagemUrl;

    @Column(nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    @Column(length = 80)
    private String categoria;
}
