package com.example.neo4jtest.Entiity.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NGEntities implements Serializable {

    private static final long serialVersionUID=1L;
    private List<EntityStruct> entityStructs;
    public NGEntities(List<EntityStruct> entityStructs) {
        this.entityStructs = entityStructs;
    }
    public NGEntities() {
        this(null);
    }
    public List<EntityStruct>getEntities(){
        return entityStructs;
    }
    public void setEntityStructs(List<EntityStruct>entityStructs){this.entityStructs=entityStructs;}
    public EntityStruct getEntity(Long guid){
        EntityStruct entityStruct = new EntityStruct();
        for (EntityStruct entity:entityStructs){
            if (entity.getGuid().equals(guid)){
               entityStruct=entity;
               break;
            }
        }
        return entityStruct;
    }
    public void addEntity(EntityStruct entityStruct){
        List<EntityStruct> entityStructs=this.entityStructs;
        if (entityStructs==null){
            entityStructs=new ArrayList<>();
            this.entityStructs=entityStructs;
        }
        entityStructs.add(entityStruct);
    }
}
