package com.example.neo4jtest.Service;

import com.example.neo4jtest.Entiity.Node.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fhl
 */
@Repository
public interface EntityStore {

    /**
     * 返回EntityStruct
     * @param guid
     * @return EntityStruct
     */
    EntityStruct getEntityById(Long guid);

    /**
     *
     * @param guid
     * @return NGEntities
     */
    NGEntities getByIds(List<Long> guid);

    /**
     *
     * @param entities
     * @return
     */
    boolean create(NGEntities entities);

    /**
     * 修改单独的节点
     * @param entitie
     * @param guid
     * @return
     */
    boolean updateEntity(EntityStruct entitie,Long guid);

    /**
     * 修改多个节点
     * @param entities
     * @param guids
     * @return
     */
    boolean updateEntities(NGEntities entities,Long guids);


}
