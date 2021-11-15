package com.cdq.interview.legalentities.service;

import net.lingala.zip4j.ZipFile;
import org.springframework.stereotype.Component;

@Component
public class ZipExtractor {

    public void extract() {

        String source = System.getProperty("user.dir") + "/data.zip";
        String target = System.getProperty("user.dir") + "/data";

        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(target);
        } catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        }
    }
}
