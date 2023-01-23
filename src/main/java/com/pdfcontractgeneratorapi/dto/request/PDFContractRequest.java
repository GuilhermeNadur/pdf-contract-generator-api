package com.pdfcontractgeneratorapi.dto.request;

import com.pdfcontractgeneratorapi.domain.Contratado;
import com.pdfcontractgeneratorapi.domain.Contratante;
import com.pdfcontractgeneratorapi.domain.Mensalidade;
import com.pdfcontractgeneratorapi.domain.Testemunha;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDFContractRequest {

    @Valid
    @NotNull
    private Contratado contratado;

    @Valid
    @NotNull
    private Contratante contratante;

    @Valid
    @NotNull
    private Mensalidade mensalidade;

    @Valid
    @NotNull
    private List<Testemunha> testemunhas;

    @NotNull
    private LocalDateTime dataInicio;
}
