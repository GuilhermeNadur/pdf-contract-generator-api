package com.pdfcontractgeneratorapi.service;

import com.itextpdf.text.DocumentException;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import com.pdfcontractgeneratorapi.service.impl.PDFContractServiceImpl;
import com.pdfcontractgeneratorapi.test.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.pdfcontractgeneratorapi.util.ConstantsUtils.STORAGE_DIR;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.TEMP_DIR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PDFContractServiceTest {

    @Mock
    private com.pdfcontractgeneratorapi.service.PDFContractStorage pdfContractStorage;

    @Mock
    private com.pdfcontractgeneratorapi.service.PDFContractGenerator pdfContractGenerator;

    @InjectMocks
    private PDFContractServiceImpl pdfContractServiceImpl;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEMP_DIR));
        Files.createDirectories(Paths.get(STORAGE_DIR));
    }

    @Test
    void generate() throws DocumentException, IOException {
        var file = ResourceUtils.getFile("classpath:response/8a016bbd-e316-4d9c-9a3d-debfb02b928e.pdf");
        var actualResponse = TestUtils.getPDFResponse(file, HttpStatus.CREATED);
        when(pdfContractGenerator.generate(any(PDFContractRequest.class))).thenReturn(file);
        when(pdfContractStorage.uploadFile(any(File.class))).thenReturn(file);
        var expectedResponse = pdfContractServiceImpl.generate(PDFContractRequest.builder().build());
        assertThat(actualResponse, is(equalTo(expectedResponse)));
    }
}
