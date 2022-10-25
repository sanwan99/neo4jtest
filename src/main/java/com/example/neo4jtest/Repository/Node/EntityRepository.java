package com.example.neo4jtest.Repository.Node;

import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
    public interface EntityRepository extends Neo4jRepository<EntityStruct,Long> {


}
