package com.pdfcontractgeneratorapi.service.impl;

import com.itextpdf.text.DocumentException;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import com.pdfcontractgeneratorapi.service.PDFContractGenerator;
import com.pdfcontractgeneratorapi.service.PDFContractService;
import com.pdfcontractgeneratorapi.service.PDFContractStorage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.pdfcontractgeneratorapi.util.ConstantsUtils.STORAGE_DIR;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.TEMP_DIR;

@Service
@AllArgsConstructor
public class PDFContractServiceImpl implements PDFContractService {

    private final PDFContractStorage storage;
    private final PDFContractGenerator generator;

    public ResponseEntity<Object> generate(PDFContractRequest request) throws DocumentException, IOException {
        Files.createDirectories(Paths.get(TEMP_DIR));
        Files.createDirectories(Paths.get(STORAGE_DIR));
        var pdfToSave = generator.generate(request);
        var pdfSaved = storage.uploadFile(pdfToSave);
        return getPDFResponse(pdfSaved, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> findByUUID(String uuid) throws IOException {
        var pdf = storage.downloadFile(uuid);
        return getPDFResponse(pdf, HttpStatus.OK);
    }

    private static ResponseEntity<Object> getPDFResponse(File pdf, HttpStatus status) throws IOException {
        var headers = getPDFHeaders(pdf.getName());
        return new ResponseEntity<>(Files.readAllBytes(pdf.toPath()), headers, status);
    }

    private static HttpHeaders getPDFHeaders(String fileName) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return headers;
    }
}
