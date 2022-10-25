package com.example.neo4jtest.Repository.Node;

import com.example.neo4jtest.Entiity.Node.ColumnEntity;
import com.example.neo4jtest.Entiity.Node.TableEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends Neo4jRepository<ColumnEntity,Long> {

    ColumnEntity findByName(String name);
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

    @Query("LOAD CSV WITH HEADERS " +
            "FROM 'file:///Column.csv' " +
            "AS line " +
            "MERGE(p:EntityStruct:Column{name:line.name," +
            "type:line.type," +
            "guid:line.guid," +
            "typeName:line.typeName})")
    void importColumnCsv();
}
