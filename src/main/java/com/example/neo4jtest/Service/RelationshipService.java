package com.example.neo4jtest.Service;

import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipService {
    void saveProcessship(String fromName, String toName,Boolean isTableOut);
    void deleteProcessship(String fromName, String toName,Boolean isTableOut);
    void saveTableship(String fromName, String toName,Boolean isTableOut);
    void saveTableColumnEdge(Long tableGuid,Long columnGuid);
    ProcessRelation getEdge(Long guid);

}
