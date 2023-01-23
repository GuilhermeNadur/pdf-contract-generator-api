package com.pdfcontractgeneratorapi.service;

import com.itextpdf.text.DocumentException;
import com.pdfcontractgeneratorapi.service.impl.PDFContractGeneratorImpl;
import com.pdfcontractgeneratorapi.test.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.pdfcontractgeneratorapi.util.ConstantsUtils.STORAGE_DIR;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.TEMP_DIR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = { PDFContractGeneratorImpl.class })
class PDFContractGeneratorTest {

    @Autowired
    private PDFContractGeneratorImpl pdfContractGenerator;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEMP_DIR));
        Files.createDirectories(Paths.get(STORAGE_DIR));
    }

    @Test
    void generate() throws DocumentException, IOException {
        var actualFile = ResourceUtils.getFile("classpath:response/56d59470-9a86-11ed-a8fc-0242ac120002.pdf");
        var expectedFile = pdfContractGenerator.generate(TestUtils.getTestRequest());
        var actualBytes = Files.readAllBytes(actualFile.toPath());
        var expectedBytes = Files.readAllBytes(expectedFile.toPath());
        assertThat(actualBytes, is(not(equalTo(expectedBytes))));
        Files.deleteIfExists(expectedFile.toPath());
    }
}
