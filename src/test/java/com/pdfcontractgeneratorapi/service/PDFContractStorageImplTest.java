package com.pdfcontractgeneratorapi.service;

import com.pdfcontractgeneratorapi.service.impl.PDFContractStorageImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.pdfcontractgeneratorapi.test.TestConstants.PDF_FILE_TEST;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.STORAGE_DIR;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.TEMP_DIR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = { PDFContractStorageImpl.class })
public class PDFContractStorageImplTest {

    @Autowired
    private PDFContractStorageImpl pdfContractStorage;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEMP_DIR));
        Files.createDirectories(Paths.get(STORAGE_DIR));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEMP_DIR + PDF_FILE_TEST));
        Files.deleteIfExists(Paths.get(STORAGE_DIR + PDF_FILE_TEST));
    }

    @Test
    void generate() throws IOException {
        var resourceFile = ResourceUtils.getFile("classpath:response/" + PDF_FILE_TEST);
        var tempFile = copyToTempFile(resourceFile);
        var storageFile = pdfContractStorage.uploadFile(tempFile);
        var actualBytes = Files.readAllBytes(resourceFile.toPath());
        var expectedBytes = Files.readAllBytes(storageFile.toPath());
        assertThat(actualBytes, is(equalTo(expectedBytes)));
    }

    private static File copyToTempFile(File resourceFile) throws IOException {
        var tempFilePath = Paths.get(TEMP_DIR + resourceFile.getName());
        Files.copy(resourceFile.toPath(), tempFilePath);
        return tempFilePath.toFile();
    }
}
