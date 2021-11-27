package com.cdq.interview.legalentitiesstats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Service
public class Downloader {

    final String PATH = System.getProperty("user.dir") + "/data.zip";

    public boolean downloadFile(String url) {

        if (!new File(PATH).isFile()) {
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(PATH)) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                log.info("Download starting");
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                log.info("File downloaded successfully");
                return true;
            } catch (IOException e) {
                log.error("Cannot save file: " + e.getMessage());
            }

        } else {
            log.info("File already exists");
            return true;
        }
        return false;
    }
}
