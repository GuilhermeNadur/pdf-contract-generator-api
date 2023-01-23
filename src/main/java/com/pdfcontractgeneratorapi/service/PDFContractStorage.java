package com.pdfcontractgeneratorapi.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface PDFContractStorage {

    File uploadFile(File file) throws IOException;

    File downloadFile(String uuid) throws FileNotFoundException;
}
