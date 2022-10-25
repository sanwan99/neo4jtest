package com.example.neo4jtest.Service;

import com.example.neo4jtest.Entiity.Node.*;
import com.example.neo4jtest.Repository.Node.ColumnRepository;
import com.example.neo4jtest.Repository.Node.EntityRepository;
import com.example.neo4jtest.Repository.Node.ProcessRepository;
import com.example.neo4jtest.Repository.Node.TableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class EntityStoreImpl implements EntityStore {

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Override
    public EntityStruct getEntityById(Long guid) {
        EntityStruct entityStruct = entityRepository.findById(guid).get();
        return entityStruct;
    }

    @Override
    public NGEntities getByIds(List<Long> guid) {
        NGEntities ngEntities=new NGEntities();
        for (Long id:guid){
            EntityStruct entity = getEntityById(id);
            ngEntities.addEntity(entity);
        }
        return ngEntities;
    }

    @Override
    public boolean create(NGEntities entities) {
        preCreate(entities);
        return false;
    }

    private void preCreate(NGEntities entities){
        List<EntityStruct> entityList = entities.getEntities();
        List<EntityStruct> tableEntities=new ArrayList<>();
        List<EntityStruct> columnEntities=new ArrayList<>();
        List<EntityStruct> processEntities=new ArrayList<>();
        for (EntityStruct entity:entityList){
            if ("TABLE".equals(entity.getTypeName())){
                tableEntities.add( entity);
            }
            if ("COLUMN".equals(entity.getTypeName())){
                columnEntities.add((ColumnEntity) entity);
            }
            if ("PROCESS".equals(entity.getTypeName())){
                processEntities.add((ProcessEntity) entity);
            }
        }
        if (!tableEntities.isEmpty()){
            createVertex(tableEntities,"TABLE");
        }
        if (!processEntities.isEmpty()){
            createVertex(tableEntities,"PROCESS");
        }
        if (!columnEntities.isEmpty()){
            createVertex(tableEntities,"COLUMN");
        }
    }
    private void createVertex(List<EntityStruct> entityList,String TypeName){

       try {
           switch (TypeName){
               case "TABLE":{
                   List<TableEntity> tableEntities= Collections.singletonList((TableEntity) entityList);
                   tableRepository.saveAll(tableEntities);
               }
               break;
               case "PROCESS":{
                   List<ProcessEntity> processEntities= Collections.singletonList((ProcessEntity) entityList);
                   processRepository.saveAll(processEntities);
               }
               break;
               case "COLUMN":{
                   List<ColumnEntity> columnEntities= Collections.singletonList((ColumnEntity) entityList);
                   columnRepository.saveAll(columnEntities);
               }
               break;
               default:
                   entityRepository.saveAll(entityList);
           }
       }catch (Exception e){
           log.error("createVertex失败",TypeName);
       }
    }

    @Override
    public boolean updateEntity(EntityStruct entitie, Long guid) {
        EntityStruct entityStruct =getEntityById(guid);
        if (!entitie.equals(entityStruct)){
            entityStruct=entitie;
            entityRepository.save(entityStruct);
        }
        return false;
    }

    @Override
    public boolean updateEntities(NGEntities entities, Long guids) {
        return false;
    }
}
