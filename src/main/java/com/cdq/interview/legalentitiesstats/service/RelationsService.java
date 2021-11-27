package com.cdq.interview.legalentitiesstats.service;

import com.cdq.interview.legalentitiesstats.model.relation.Relation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RelationsService {

    private Parser parser;
    private List<Relation> relations;

    @Autowired
    public RelationsService(Parser parser) {
        this.parser = parser;
    }

    public List<Relation> getRelations() throws FileNotFoundException, XMLStreamException {
        return parser.xmlParser();
    }

    public Long getLowestNumber() throws FileNotFoundException, XMLStreamException {
        relations = parser.xmlParser();
        Map<String, Long> nodesMap = getAllCountedNodes(relations);
        Map.Entry<String, Long> lowestNum = Collections.min(nodesMap.entrySet(), Map.Entry.comparingByValue());

        log.debug("Lowest number of relations: NodeID: " + lowestNum.getKey() + " Value: " + lowestNum.getValue());

        return lowestNum.getValue();
    }

    public Long getHighestNumber() throws FileNotFoundException, XMLStreamException {
        relations = parser.xmlParser();
        Map<String, Long> nodesMap = getAllCountedNodes(relations);
        Map.Entry<String, Long> highestNum = Collections.max(nodesMap.entrySet(), Map.Entry.comparingByValue());

        log.debug("Highest number of relations: NodeID: " + highestNum.getKey() + " Value: " + highestNum.getValue());

        return highestNum.getValue();
    }

    public Double getAverageNumber() throws FileNotFoundException, XMLStreamException {
        relations = parser.xmlParser();
        Map<String, Long> nodesMap = getAllCountedNodes(relations);
        Double average = nodesMap.entrySet().stream().mapToDouble(Map.Entry::getValue).average().orElse(0.0);

        log.debug("Average number of relations: " + average);

        return average;
    }

    public List<Relation> getRelationsById(String nodeId) throws FileNotFoundException, XMLStreamException {
        relations = parser.xmlParser();
        return relations.stream().filter(s -> s.getStartNode().getNodeId().equals(nodeId)
                || s.getEndNode().getNodeId().equals(nodeId)).collect(Collectors.toList());
    }

    private Map<String, Long> getAllCountedNodes(List<Relation> relationsList) {
        Map<String, Long> nodeMap = relations.stream().collect(Collectors.groupingBy(relation -> relation.getStartNode().getNodeId(), Collectors.counting()));
        Map<String, Long> endNodeMap = relations.stream().collect(Collectors.groupingBy(relation -> relation.getEndNode().getNodeId(), Collectors.counting()));

        nodeMap.putAll(endNodeMap);

        return nodeMap;
    }
}
