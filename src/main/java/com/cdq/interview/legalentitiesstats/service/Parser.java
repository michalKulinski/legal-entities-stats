package com.cdq.interview.legalentitiesstats.service;

import com.cdq.interview.legalentitiesstats.model.relation.EndNode;
import com.cdq.interview.legalentitiesstats.model.relation.Relation;
import com.cdq.interview.legalentitiesstats.model.relation.StartNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Parser {

    Relation relation = null;
    StartNode startNode = null;
    EndNode endNode = null;

    boolean isStartNode = false;
    boolean isNodeID = false;
    boolean isEndNode = false;

    public List<Relation> xmlParser() throws FileNotFoundException, XMLStreamException {

        File[] files = new File(System.getProperty("user.dir") + "/data").listFiles();

        List<Relation> relationList = new ArrayList<>();

        for (File file : files) {

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader(file));

            while (streamReader.hasNext()) {
                streamReader.next();
                int event = streamReader.getEventType();
                switch (event) {
                    case XMLStreamReader.START_ELEMENT:
                        if (streamReader.getLocalName().equals("Relationship")) {
                            relation = new Relation();
                        } else if (streamReader.getLocalName().equals("StartNode")) {
                            startNode = new StartNode();
                            isStartNode = true;
                        } else if (streamReader.getLocalName().equals("EndNode")) {
                            endNode = new EndNode();
                            isEndNode = true;
                        } else if (streamReader.getLocalName().equals("NodeID")) {
                            isNodeID = true;
                        } else if (streamReader.getLocalName().equalsIgnoreCase("RelationshipType")) {
                            relation.setRelationType(streamReader.getElementText());
                        }
                        break;
                    case XMLStreamReader.CHARACTERS:
                        if (isStartNode && isNodeID) {
                            startNode.setNodeId(streamReader.getText());
                            relation.setStartNode(startNode);
                            isStartNode = false;
                            isNodeID = false;
                        } else if (isEndNode && isNodeID) {
                            endNode.setNodeId(streamReader.getText());
                            relation.setEndNode(endNode);
                            isEndNode = false;
                            isNodeID = false;
                        }
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        if (streamReader.getLocalName().equals("Relationship")) {
                            relationList.add(relation);
                        }
                        break;
                }
            }
        }
        log.debug("List of relations: " + relationList);
        return relationList;

    }

}