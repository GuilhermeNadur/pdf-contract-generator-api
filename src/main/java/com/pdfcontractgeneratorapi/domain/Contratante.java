package com.pdfcontractgeneratorapi.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contratante {

    @CNPJ
    @NotNull
    private String cnpj;

    @NotNull
    private String razaoSocial;

    @NotNull
    private String nomeFantasia;

    @NotNull
    private String endereco;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    private String cep;
}
