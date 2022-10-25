package com.example.neo4jtest.Service;

import com.example.neo4jtest.Entiity.Lineage.LineageInfo;
import com.example.neo4jtest.Entiity.Lineage.LineageRelation;
import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import com.example.neo4jtest.Repository.Relationship.ProcessRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.neo4jtest.Entiity.Lineage.LineageInfo.LineageDirection.*;

@Service
public class LineageServiceImpl implements LineageService {
    @Autowired
    private ProcessRelationRepository processRelationRepository;
    @Autowired
    private RelationshipService relationshipService;
    @Override
    public LineageInfo getEntityLineageInfo(Long guid, LineageInfo.LineageDirection direction, int depth) {
        LineageInfo lineageInfo;
                                                                                                      
        lineageInfo=getLineageInfoV1(guid,direction,depth);

        return lineageInfo;
    }

    @Override
    public LineageInfo getEntityLineageInfo(Long guid, LineageInfo.LineageDirection direction) {
        LineageInfo lineageInfo;

        lineageInfo=getLineageInfoV1(guid,direction,0);

        return lineageInfo;
    }

    public LineageInfo getLineageInfoV1(Long guid, LineageInfo.LineageDirection direction, int depth) {
        LineageInfo lineageInfo;

        if (direction.equals(INPUT)){
            lineageInfo=getLineageInfo(guid,INPUT,depth);
        }else if (direction.equals(OUTPUT)){
            lineageInfo=getLineageInfo(guid,OUTPUT,depth);
        }else {
            lineageInfo=getBothLineageInfo(guid,depth);
        }

        return lineageInfo;
    }

    public LineageInfo getLineageInfo(Long guid, LineageInfo.LineageDirection direction, int depth) {

        List<ProcessEntity> processEntities;
        Map<Long, EntityStruct> guidEntityMap=new HashMap<>();
        Set<LineageRelation> relations=new HashSet<>();
        depth=depth*2;
        if (direction.equals(INPUT)){
            processEntities = processRelationRepository.inRelationship(guid,depth);
        }else {
            processEntities = processRelationRepository.outRelationship(guid,depth);
        }
        for (ProcessEntity processEntity:processEntities){
            Map<String, Long> processRelationList = processEntity.getProcessRelationList();
            Collection<Long> values = processRelationList.values();
            if (!values.isEmpty()){
                for (Long value:values){
                    ProcessRelation edge = relationshipService.getEdge(value);
                    processEdge(edge,guidEntityMap,relations);
                }
            }
        }
        return new LineageInfo(guid,guidEntityMap,relations,direction,depth);
    }
    public LineageInfo getBothLineageInfo(Long guid,int depth) {
        LineageInfo inLineageInfo = getLineageInfo(guid, INPUT, depth);
        LineageInfo outLineageInfo = getLineageInfo(guid, OUTPUT, depth);
        LineageInfo bothLineageInfo=inLineageInfo;
        bothLineageInfo.getRelations().addAll(outLineageInfo.getRelations());
        bothLineageInfo.getGuidEntityMap().putAll(outLineageInfo.getGuidEntityMap());
        bothLineageInfo.setLineageDirection(BOTH);
        return bothLineageInfo;

    }
    private void processEdge(ProcessRelation edge,Map<Long, EntityStruct> guidEntityMap,Set<LineageRelation> relations){


        EntityStruct entityStart = edge.getEntityStart();
        EntityStruct entityEnd = edge.getEntityEnd();
        Long fromId = edge.getFromId();
        Long toId = edge.getToId();
        Long relationGuid = edge.getGuid();
        if (!guidEntityMap.containsKey(fromId)){
            guidEntityMap.put(fromId,entityStart);
        }
        if (!guidEntityMap.containsKey(toId)){
            guidEntityMap.put(toId,entityEnd);
        }
        relations.add(new LineageRelation(fromId,toId,relationGuid));
    }
}
