package com.example.neo4jtest.Repository.Relationship;

import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import com.example.neo4jtest.Entiity.Node.TableEntity;
import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRelationRepository extends Neo4jRepository<ProcessRelation,Long> {


    /**
     * 根据name获取in方向关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) - [r:Process_Edge*0..] -> " +
            "(b:EntityStruct) " +
            "where b.name = {0} " +
            "return p")
    List<ProcessEntity> inRelationship(String name);

    /**
     * 根据name获取out方向关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(b:EntityStruct) " +
            "- [rr:Process_Edge*0..] -> (c:EntityStruct) " +
            "where b.name = {0} " +
            "return p")
    List<ProcessEntity> outRelationship(String name);

    /**
     * 根据name获取 both 方向关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) <- [r:Process_Edge*0..] -> " +
            "(b:EntityStruct) " +
            "<- [rr:Process_Edge*0..] -> (c:EntityStruct) " +
            "where b.name = {0} " +
            "return p")
    List<ProcessEntity> bothRelationship(String name);

    /**
     * 根据guid获取in 方向关系
     * @param guid
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) - [r:Process_Edge*0..] -> " +
            "(b:EntityStruct) " +
            "where b.guid = {0} " +
            "return p")
    List<ProcessEntity> inRelationship(Long guid);
    /**
     * 根据guid获取out方向关系
     * @param guid
     * @return
     */
    @Query("match " +
            "p=(b:EntityStruct) " +
            "- [rr:Process_Edge*0..] -> (c:EntityStruct) " +
            "where b.guid = {0} " +
            "return p")
    List<ProcessEntity> outRelationship(Long guid);

    /**
     * 根据guid获取 both 方向关系
     *
     * @param guid
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) <- [r:Process_Edge*0..] -> " +
            "(b:EntityStruct) " +
            "<- [rr:Process_Edge*0..] -> (c:EntityStruct) " +
            "where b.guid = {0} " +
            "return p")
    List<ProcessEntity> bothRelationship(Long guid);
    /**
     * 根据guid获取in 方向关系
     * depth为深度
     * @param guid
     * @param depth
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) - [r:Process_Edge*0..] -> " +
            "(b:EntityStruct) " +
            "where b.guid = {0} " +
            "return p "+
            "limit {1}")
    List<ProcessEntity> inRelationship(@Param("guid") Long guid,@Param("depth") int depth);
    /**
     * 根据guid获取out方向关系
     * depth为深度
     * @param guid
     * @param depth
     * @return
     */
    @Query("match " +
            "p=(b:EntityStruct) " +
            "- [rr:Process_Edge*0..3] -> (c:EntityStruct) " +
            "where b.guid = {0} " +
            "return p"+
            "limit {1}")
    List<ProcessEntity> outRelationship(Long guid,int depth);

    /**
     * 根据guid获取 both 方向关 系
     *depth为深度
     * @param guid
     * @param depth
     * @return
     */
    @Query("match " +
            "p=(a:EntityStruct) <- [r:Process_Edge*0..3] -> " +
            "(b:EntityStruct) " +
            "<- [rr:Process_Edge*0..3] -> (c:EntityStruct) " +
            "where b.guid = {0} " +
            "return p" +
            "limit {1}")
    List<ProcessEntity> bothRelationship(Long guid,int depth);


    /**
     * apoc
     * @param guid
     * @param depth
     * @return
     */

    @Query("match" +
            "n:EntityStruct" +
            "CALL apoc.path.expand(n,NULL,NULL,0,-1) YIELD path" +
            "where n.guid=guid" +
            "RETURN path")
    List<ProcessEntity> inRelationship1(@Param("guid") Long guid,@Param("depth") int depth);


    @Query("LOAD CSV WITH HEADERS " +
            "FROM 'file:///ProcessRelation.csv' " +
            "AS line " +
            "match(from:EntityStruct{guid:line.fromId}),(to:EntityStruct{guid:line.toId}) " +
            "merge (from)-[r:Process_Edge{fromId:line.fromId,fromName:line.fromName,toId:line.toId,toName:line.toName}]->(to)")
    void improtRrocessRelationById();
    @Query("LOAD CSV WITH HEADERS " +
            "FROM 'file:///ProcessRelation.csv' " +
            "AS line " +
            "match(from:EntityStruct{name:line.fromName}),(to:EntityStruct{name:line.toName}) " +
            "merge (from)-[r:Process_Edge{fromId:line.fromId,fromName:line.fromName,toId:line.toId,toName:line.toName}]->(to)")
    void improtRrocessRelationByName();

}

