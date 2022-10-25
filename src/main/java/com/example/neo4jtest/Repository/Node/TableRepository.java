package com.example.neo4jtest.Repository.Node;

import com.example.neo4jtest.Entiity.Node.ColumnEntity;
import com.example.neo4jtest.Entiity.Node.TableEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends Neo4jRepository<TableEntity,Long> {

    TableEntity findByName(String name);

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
            "FROM 'file:///Table.csv' " +
            "AS line " +
            "MERGE(p:EntityStruct:Table{name:line.name," +
            "type:line.type," +
            "guid:line.guid," +
            "typeName:line.typeName})")
    void imporTabeleCsv();

}
