package com.cdq.interview.legalentitiesstats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class LeiDataService {

    private Downloader downloader;
    private ZipExtractor zipExtractor;

    @Autowired
    public LeiDataService(Downloader downloader, ZipExtractor zipExtractor) {
        this.downloader = downloader;
        this.zipExtractor = zipExtractor;
    }

    public void prepareData(String url){
        boolean isDownloaded = downloader.downloadFile(url);

        if(isDownloaded){
            try {
                zipExtractor.extract();
            } catch (IOException e) {
                log.error("Cannot extract zip file: " + e);
            }
        }
    }
}
