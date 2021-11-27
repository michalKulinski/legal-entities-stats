package com.cdq.interview.legalentitiesstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            zipExtractor.extract();
        }
    }
}
