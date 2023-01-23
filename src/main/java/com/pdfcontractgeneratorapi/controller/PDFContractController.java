package com.pdfcontractgeneratorapi.controller;

import com.itextpdf.text.DocumentException;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import com.pdfcontractgeneratorapi.service.PDFContractService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/pdf/contract")
@AllArgsConstructor
public class PDFContractController {

    private final PDFContractService pdfContractService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Object> generatePDFContract(@RequestBody @Valid PDFContractRequest request) throws DocumentException, IOException {
        log.info("[POST][generatePDFContract] Generating a new PDF contract...");
        return pdfContractService.generate(request);
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Object> getPDFContractByUUID(@PathVariable(value = "uuid") String uuid) throws IOException {
        log.info("[GET][getPDFContractByUUID] Getting PDF contract by UUID...");
        return pdfContractService.findByUUID(uuid);
    }
}
