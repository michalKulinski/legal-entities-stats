package com.cdq.interview.legalentitiesstats.service;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ZipExtractor {

    final String source = System.getProperty("user.dir") + "/data.zip";
    final String target = System.getProperty("user.dir") + "/data";

    public void extract() {

        try (ZipFile zipFile = new ZipFile(source)){
            zipFile.extractAll(target);
            log.info("Data extracted successfully");
        } catch (ZipException e) {
            log.error("Cannot extract the file: " + e.getMessage());
        }
    }
}
