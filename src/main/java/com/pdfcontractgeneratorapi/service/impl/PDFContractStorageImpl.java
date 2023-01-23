package com.pdfcontractgeneratorapi.service.impl;

import com.pdfcontractgeneratorapi.service.PDFContractStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.pdfcontractgeneratorapi.util.ConstantsUtils.STORAGE_DIR;

@Service
@AllArgsConstructor
public class PDFContractStorageImpl implements PDFContractStorage {

    public File uploadFile(File fileToUpload) throws IOException {
        var newFilePath = Paths.get(STORAGE_DIR + fileToUpload.getName());
        Files.copy(fileToUpload.toPath(), newFilePath);
        Files.deleteIfExists(fileToUpload.toPath());
        return newFilePath.toFile();
    }

    public File downloadFile(String uuid) throws FileNotFoundException {
        var fileName = uuid + ".pdf";
        var filePathToDownload = Paths.get(STORAGE_DIR + fileName);
        throwExceptionIfFileNotExists(fileName, filePathToDownload);
        return filePathToDownload.toFile();
    }

    private static void throwExceptionIfFileNotExists(String fileName, Path path) throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException(String.format("Arquivo [%s] não encontrado no Storage", fileName));
        }
    }
}
