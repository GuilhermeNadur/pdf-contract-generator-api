package com.pdfcontractgeneratorapi.controller;

import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import com.pdfcontractgeneratorapi.service.PDFContractService;
import com.pdfcontractgeneratorapi.test.TestConstants;
import com.pdfcontractgeneratorapi.test.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(com.pdfcontractgeneratorapi.controller.PDFContractController.class)
class PDFContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PDFContractService pdfContractService;

    @InjectMocks
    private com.pdfcontractgeneratorapi.controller.PDFContractController pdfContractController;

    @Test
    void generatePDFContract201() throws Exception {
        var request = ResourceUtils.getFile("classpath:request/generatePDFContractRequest201.json");
        var response = ResourceUtils.getFile("classpath:response/8a016bbd-e316-4d9c-9a3d-debfb02b928e.pdf");
        when(pdfContractService.generate(any(PDFContractRequest.class))).thenReturn(TestUtils.getPDFResponse(response, HttpStatus.CREATED));
        mockMvc.perform(
                post(TestConstants.API_V1_PDF_CONTRACT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(Files.readString(request.toPath())))
                .andExpect(status().isCreated());
    }

    @Test
    void generatePDFContract200() throws Exception {
        var request = ResourceUtils.getFile("classpath:request/generatePDFContractRequest201.json");
        var response = ResourceUtils.getFile("classpath:response/8a016bbd-e316-4d9c-9a3d-debfb02b928e.pdf");
        when(pdfContractService.findByUUID(any(String.class))).thenReturn(TestUtils.getPDFResponse(response, HttpStatus.OK));
        mockMvc.perform(
                get(TestConstants.API_V1_PDF_CONTRACT_UUID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
