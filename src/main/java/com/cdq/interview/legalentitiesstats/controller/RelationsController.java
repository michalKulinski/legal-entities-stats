package com.cdq.interview.legalentitiesstats.controller;

import com.cdq.interview.legalentitiesstats.model.relation.Relation;
import com.cdq.interview.legalentitiesstats.service.RelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RelationsController {

    private RelationsService relationsService;

    @Autowired
    public RelationsController(RelationsService relationsService) {
        this.relationsService = relationsService;
    }


    @GetMapping("/getRelations")
    public List<Relation> getRelations() throws FileNotFoundException, XMLStreamException {
        return relationsService.getRelations();
    }

    @GetMapping("/getRelations/{nodeId}")
    public List<Relation> getRelationsById(@PathVariable String nodeId) throws FileNotFoundException, XMLStreamException {
        return relationsService.getRelationsById(nodeId);
    }

    @GetMapping("/getLowestNumber")
    public Long getLowestNumber() throws FileNotFoundException, XMLStreamException {
        return relationsService.getLowestNumber();
    }

    @GetMapping("/getHighestNumber")
    public Long getHighestNumber() throws FileNotFoundException, XMLStreamException {
        return relationsService.getHighestNumber();
    }

    @GetMapping("/getAverageNumber")
    public Double getAverageNumber() throws FileNotFoundException, XMLStreamException {
        return relationsService.getAverageNumber();
    }
}
