package com.pdfcontractgeneratorapi.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mensalidade {

    @NotNull
    private String quantidadeLojas;

    @NotNull
    private BigDecimal valor1;

    @NotNull
    private BigDecimal valor2;

    @NotNull
    private BigDecimal valor3;

    @NotNull
    private BigDecimal valorFinal;

    @NotNull
    private String valorPorExtenso;
}
