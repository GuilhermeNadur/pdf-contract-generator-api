package com.pdfcontractgeneratorapi.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdfcontractgeneratorapi.domain.Paragrafo;
import com.pdfcontractgeneratorapi.dto.request.PDFContractRequest;
import com.pdfcontractgeneratorapi.service.PDFContractGenerator;
import com.pdfcontractgeneratorapi.util.paragraph.ParagraphConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pdfcontractgeneratorapi.util.ConstantsUtils.PDF_EXTENSION;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.TEMP_DIR;

@Slf4j
@Service
@AllArgsConstructor
public class PDFContractGeneratorImpl implements PDFContractGenerator {

    public File generate(PDFContractRequest request) throws DocumentException, IOException {
        var uuid = UUID.randomUUID().toString();
        var pdfPath = Paths.get(TEMP_DIR + uuid + PDF_EXTENSION);
        Files.createFile(pdfPath);
        return generatePDF(request, uuid, pdfPath);
    }

    private static File generatePDF(PDFContractRequest request, String uuid, Path pdfPath) throws DocumentException, IOException {
        var document = new Document();
        var paragraphs = getContractParagraphs(uuid, request);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath.toFile()));
            document.open();
            addParagraphs(document, paragraphs);
        } catch (DocumentException | IOException e) {
            log.error("Ocorreu um erro na geração do arquivo PDF: {}", e.getMessage());
            throw e;
        } finally {
            document.close();
        }
        return pdfPath.toFile();
    }

    private static List<Paragraph> getContractParagraphs(String uuid, PDFContractRequest request) throws IOException {
        var paragraphs = new ArrayList<Paragraph>();
        var layout = ResourceUtils.getFile("classpath:layout/pdf-contract-layout.json");
        var completedLayout = fillVariables(Files.readString(layout.toPath()), request, uuid);
        var paragrafos = getParagrafos(completedLayout);
        return convertAndAddAllParagraphs(paragraphs, paragrafos);
    }

    private static List<Paragraph> convertAndAddAllParagraphs(ArrayList<Paragraph> paragraphs, List<Paragrafo> paragrafos) {
        for (var paragrafo : paragrafos) {
            paragraphs.add(ParagraphConverter.convertToITextParagraph(paragrafo));
        }
        return paragraphs;
    }

    private static void addParagraphs(Document document, List<Paragraph> paragraphs) throws DocumentException {
        for (var paragraph : paragraphs) {
            document.add(paragraph);
        }
    }

    private static List<Paragrafo> getParagrafos(String layout) {
        Type listType = new TypeToken<ArrayList<Paragrafo>>(){}.getType();
        return new Gson().fromJson(layout, listType);
    }

    private static String fillVariables(String stringToFormat, PDFContractRequest request, String uuid) {
        var contratado = request.getContratado();
        var contratante = request.getContratante();
        var testemunhas = request.getTestemunhas();
        var mensalidade = request.getMensalidade();
        var dataInicio = request.getDataInicio();
        return String.format(
                stringToFormat,
                // CÓDIGO DOCUMENTO
                uuid,
                // CONTRATADO
                contratado.getRazaoSocial(),
                contratado.getCnpj(),
                contratado.getEndereco(),
                contratado.getCidade(),
                contratado.getEstado(),
                contratado.getCep(),
                contratado.getCnpj(),
                contratado.getNomeFantasia(),
                // CONTRATANTE
                contratante.getRazaoSocial(),
                contratante.getCnpj(),
                contratante.getEndereco(),
                contratante.getCidade(),
                contratante.getEstado(),
                contratante.getCep(),
                contratante.getNomeFantasia(),
                // TERMOS DEFINIDOS
                contratado.getRazaoSocial(),
                contratado.getNomeFantasia(),
                contratante.getRazaoSocial(),
                contratante.getNomeFantasia(),
                // MENSALIDADE
                mensalidade.getValor1().toString(),
                mensalidade.getValor2().toString(),
                mensalidade.getValor3().toString(),
                mensalidade.getQuantidadeLojas(),
                mensalidade.getValorFinal().toString(),
                mensalidade.getValorPorExtenso(),
                // FORO
                dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                contratado.getRazaoSocial(),
                contratado.getNomeFantasia(),
                contratado.getCnpj(),
                contratante.getRazaoSocial(),
                contratante.getNomeFantasia(),
                contratante.getCnpj(),
                // TESTEMUNHAS
                testemunhas.get(0).getNome(),
                testemunhas.get(0).getCpf(),
                testemunhas.get(1).getNome(),
                testemunhas.get(1).getCpf()
        );
    }
}
