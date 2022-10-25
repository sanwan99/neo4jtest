package com.example.neo4jtest.Entiity.Relationship;


import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@RelationshipEntity(type = "Process_Edge")
@Component
public class ProcessRelation implements Serializable {
    private static final long serialVersionUID=1L;
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());


    @Id
    private Long guid;

    @StartNode
    private EntityStruct EntityStart;

    @EndNode
    private EntityStruct EntityEnd;


    @Properties
    private Map<String,Object> relationshipAttribute=new HashMap<>();

    private String relation;
    private String fromName;
    private String toName;
    private Long fromId;
    private Long toId;

    public void setEntityStart(EntityStruct entity){
        this.EntityStart=entity;
        this.fromName=entity.getName();
        this.fromId=entity.getGuid();
    }
    public void setEntityEnd(EntityStruct entity){
        this.EntityEnd=entity;
        this.toName=entity.getName();
        this.toId=entity.getGuid();
    }

    public ProcessRelation(){
        this.guid=s_nextId.getAndIncrement();
    }


    public ProcessRelation(EntityStruct parent,EntityStruct child,String relation,HashMap<String,Object> relationshipAttribute){

        this.EntityStart=parent;
        this.EntityEnd=child;
        this.relation=relation;
       this.relationshipAttribute=relationshipAttribute;
    }
}
