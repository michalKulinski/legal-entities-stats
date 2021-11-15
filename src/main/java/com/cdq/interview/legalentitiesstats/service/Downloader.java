package com.cdq.interview.legalentities.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class Downloader {

    public boolean downloadFile(String url) {

        String path = System.getProperty("user.dir") + "/data.zip";

        if (!new File(path).isFile()) {
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                System.out.println("Starting");
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                System.out.println("File downloaded");
                return true;
            } catch (IOException e) {
                // handle exception
            }

        } else {
            System.out.println("File already exists");
            return true;
        }
        return false;
    }
}
