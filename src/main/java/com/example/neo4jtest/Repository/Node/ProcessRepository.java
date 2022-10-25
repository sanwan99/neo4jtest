package com.example.neo4jtest.Repository.Node;

import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRepository extends Neo4jRepository<ProcessEntity,Long> {
    ProcessEntity findByName(String name);

    @Query("LOAD CSV WITH HEADERS " +
            "FROM 'file:///Process.csv' " +
            "AS line " +
            "MERGE(p:EntityStruct:Process{name:line.name," +
            "type:line.type," +
            "guid:line.guid," +
            "typeName:line.typeName})")
    void importProcessCsv();

}
