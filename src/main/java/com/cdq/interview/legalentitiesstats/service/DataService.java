package com.cdq.interview.legalentities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private Downloader downloader;
    private ZipExtractor zipExtractor;

    @Autowired
    public DataService(Downloader downloader, ZipExtractor zipExtractor) {
        this.downloader = downloader;
        this.zipExtractor = zipExtractor;
    }

    public void prepareData(String url){
        boolean isDownloaded = downloader.downloadFile(url);
        if(isDownloaded){
            zipExtractor.extract();
        }
    }

    public void deleteData(){

    }

}
