package com.pdfcontractgeneratorapi.service;

import com.itextpdf.text.DocumentException;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PDFContractService {

    ResponseEntity<Object> generate(PDFContractRequest request) throws IOException, DocumentException;

    ResponseEntity<Object> findByUUID(String uuid) throws IOException;
}
