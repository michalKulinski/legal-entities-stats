package com.cdq.interview.legalentitiesstats.controller;

import com.cdq.interview.legalentitiesstats.model.data.LeiData;
import com.cdq.interview.legalentitiesstats.service.LeiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeiDataController {

    private LeiDataService leiDataService;

    @Autowired
    public LeiDataController(LeiDataService leiDataService) {
        this.leiDataService = leiDataService;
    }

    @PostMapping("/downloadData")
    public void downloadData(@RequestBody LeiData leiData) {
        leiDataService.prepareData(leiData.getUrl());
    }

    @DeleteMapping("/deleteData")
    public void deleteData() {
        leiDataService.deleteData();
    }
}
