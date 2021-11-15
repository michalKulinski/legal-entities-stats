package com.cdq.interview.legalentities.controller;

import com.cdq.interview.legalentities.data.Data;
import com.cdq.interview.legalentities.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    private DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/downloadData")
    public void downloadData(@RequestBody Data data) {
        dataService.prepareData(data.url);
    }

    @DeleteMapping("/deleteData")
    public void deleteData() {
        dataService.deleteData();
    }
}
