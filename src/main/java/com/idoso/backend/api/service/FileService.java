package com.idoso.backend.api.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
@Slf4j
public class FileService {

    public String converteBytesParaArquivo(String path, String bytes) {

        byte[] decodedBytes = Base64.getDecoder().decode(bytes);
        try {
            FileUtils.writeByteArrayToFile(new File(path), decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;

   }


   public String converteArquivoParaBytes(String pathToFile) {
        byte[] fileContent = new byte[0];

        try {
            fileContent = FileUtils.readFileToByteArray(new File(pathToFile));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(fileContent);

    }


}
