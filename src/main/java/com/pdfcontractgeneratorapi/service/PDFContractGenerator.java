package com.pdfcontractgeneratorapi.service;

import com.itextpdf.text.DocumentException;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;

import java.io.File;
import java.io.IOException;

public interface PDFContractGenerator {

    File generate(PDFContractRequest request) throws DocumentException, IOException;
}
