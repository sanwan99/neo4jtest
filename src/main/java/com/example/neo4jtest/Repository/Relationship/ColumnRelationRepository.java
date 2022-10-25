package com.example.neo4jtest.Repository.Relationship;

import com.example.neo4jtest.Entiity.Node.ColumnEntity;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import com.example.neo4jtest.Entiity.Node.TableEntity;
import com.example.neo4jtest.Entiity.Relationship.ColumnRelation;
import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRelationRepository extends Neo4jRepository<ColumnRelation,Long> {


    /**
     * 根据name获取in方向关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) - [r:TABLE_COLUMN_EDGE*0..] -> " +
            "(b:EntityStruct) " +
            "where b.name = {0} " +
            "return p")
    List<TableEntity> inTable(String name);

    /**
     * 根据name获取out方向关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(b:EntityStruct) " +
            "- [rr:TABLE_COLUMN_EDGE*0..] -> (c:EntityStruct) " +
            "where b.name = {0} " +
            "return p")
    List<ColumnEntity> outColumns(String name);



    /**
     * 根据guid获取in 方向关系
     * @param guid
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) - [r:TABLE_COLUMN_EDGE*0..] -> " +
            "(b:EntityStruct) " +
            "where b.guid = {0} " +
            "return p")
    List<TableEntity> inTable(Long guid);
    /**
     * 根据guid获取out方向关系
     * @param guid
     * @return
     */
    @Query("match " +
            "p=(b:EntityStruct) " +
            "- [rr:TABLE_COLUMN_EDGE*0..] -> (c:EntityStruct) " +
            "where b.guid = {0} " +
            "return p")
    List<ColumnEntity> outColumns(Long guid);


    @Query("LOAD CSV WITH HEADERS " +
            "FROM 'file:///ColumnRelation.csv' " +
            "AS line " +
            "match(from:EntityStruct{guid:line.fromId}),(to:EntityStruct{guid:line.toId}) " +
            "merge (from)-[r:TABLE_COLUMN_EDGE{fromId:line.fromId,fromName:line.fromName,toId:line.toId,toName:line.toName}]->(to)")
    void improtColumnRelationById();
    @Query("LOAD CSV WITH HEADERS " +
            "FROM 'file:///ColumnRelation.csv' " +
            "AS line " +
            "match(from:EntityStruct{name:line.fromName}),(to:EntityStruct{name:line.toName}) " +
            "merge (from)-[r:TABLE_COLUMN_EDGE{fromId:line.fromId,fromName:line.fromName,toId:line.toId,toName:line.toName}]->(to)")
    void improtColumnRelationByName();


}

