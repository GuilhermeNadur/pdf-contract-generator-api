package com.pdfcontractgeneratorapi.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Testemunha {

    @NotNull
    private String nome;

    @CPF
    @NotNull
    private String cpf;
}
