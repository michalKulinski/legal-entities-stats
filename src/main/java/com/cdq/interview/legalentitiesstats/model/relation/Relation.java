package com.cdq.interview.legalentitiesstats.model.relation;

import lombok.Data;

@Data
public class Relation {

    private StartNode startNode;

    private EndNode endNode;

    private String relationType;
}
