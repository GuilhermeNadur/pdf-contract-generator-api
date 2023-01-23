package com.pdfcontractgeneratorapi.test;

import com.pdfcontractgeneratorapi.domain.Contratado;
import com.pdfcontractgeneratorapi.domain.Contratante;
import com.pdfcontractgeneratorapi.domain.Mensalidade;
import com.pdfcontractgeneratorapi.domain.Testemunha;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

public class TestUtils {

    public static ResponseEntity<Object> getPDFResponse(File pdf, HttpStatus status) throws IOException {
        var headers = getPDFHeaders(pdf.getName());
        return new ResponseEntity<>(Files.readAllBytes(pdf.toPath()), headers, status);
    }

    public static HttpHeaders getPDFHeaders(String fileName) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return headers;
    }

    public static PDFContractRequest getTestRequest() {
        return PDFContractRequest.builder()
                .contratado(Contratado.builder()
                        .cnpj("INSERÇÃO DE DADO")
                        .nomeFantasia("INSERÇÃO DE DADO")
                        .razaoSocial("INSERÇÃO DE DADO")
                        .cep("INSERÇÃO DE DADO")
                        .cidade("INSERÇÃO DE DADO")
                        .endereco("INSERÇÃO DE DADO")
                        .build())
                .contratante(Contratante.builder()
                        .cnpj("INSERÇÃO DE DADO")
                        .nomeFantasia("INSERÇÃO DE DADO")
                        .razaoSocial("INSERÇÃO DE DADO")
                        .cep("INSERÇÃO DE DADO")
                        .cidade("INSERÇÃO DE DADO")
                        .endereco("INSERÇÃO DE DADO")
                        .build())
                .mensalidade(Mensalidade.builder()
                        .quantidadeLojas("INSERÇÃO DE DADO")
                        .valor1(BigDecimal.ZERO)
                        .valor2(BigDecimal.ZERO)
                        .valor3(BigDecimal.ZERO)
                        .valorFinal(BigDecimal.ZERO)
                        .valorPorExtenso("INSERÇÃO DE DADO")
                        .build())
                .testemunhas(List.of(
                        Testemunha.builder()
                                .cpf("INSERÇÃO DE DADO")
                                .nome("INSERÇÃO DE DADO")
                                .build(),
                        Testemunha.builder()
                                .cpf("INSERÇÃO DE DADO")
                                .nome("INSERÇÃO DE DADO")
                                .build()))
                .dataInicio(LocalDateTime.of(2023,1, 22, 13, 0, 0))
                .build();
    }
}
