package com.example.neo4jtest.Service;

import com.example.neo4jtest.Entiity.Node.ColumnEntity;
import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.example.neo4jtest.Entiity.Node.TableEntity;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import com.example.neo4jtest.Entiity.Relationship.ColumnRelation;
import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import com.example.neo4jtest.Repository.Node.ColumnRepository;
import com.example.neo4jtest.Repository.Node.TableRepository;
import com.example.neo4jtest.Repository.Node.ProcessRepository;
import com.example.neo4jtest.Repository.Relationship.ColumnRelationRepository;
import com.example.neo4jtest.Repository.Relationship.ProcessRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("neo4jServiceImpl")
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private ProcessRelationRepository processRelationRepository;
    @Autowired
    private ColumnRelationRepository columnRelationRepository;

    @Override
    public void saveProcessship(String fromName, String toName,Boolean isTableOut) {

        if (isTableOut){
            TableEntity from = tableRepository.findByName(fromName);
            ProcessEntity to = processRepository.findByName(toName);
            ProcessRelation friendshipRelation = new ProcessRelation();
            friendshipRelation.setEntityStart(from);
            friendshipRelation.setEntityEnd(to);
            to.addProcessRelation(fromName,friendshipRelation.getGuid());
            processRelationRepository.save(friendshipRelation);
        }else {
             ProcessEntity from = processRepository.findByName(fromName);
             TableEntity to= tableRepository.findByName(toName);
             ProcessRelation friendshipRelation = new ProcessRelation();
             friendshipRelation.setEntityStart(from);
             friendshipRelation.setEntityEnd(to);
             from.addProcessRelation(toName,friendshipRelation.getGuid());
            processRelationRepository.save(friendshipRelation);
        }

    }
    @Override
   public void saveTableColumnEdge(Long tableGuid,Long columnGuid){
        TableEntity tableEntity = tableRepository.findById(tableGuid).get();
        ColumnEntity columnEntity = columnRepository.findById(columnGuid).get();
        ColumnRelation tableColumnEdge =new ColumnRelation();
        tableColumnEdge.setEntityStart(tableEntity);
        tableColumnEdge.setEntityEnd(columnEntity);
        columnEntity.addColumnRelation(tableColumnEdge);
        columnRepository.save(columnEntity);

    }

    /**
     *
     * 删除节点时，使用find，save 需要@Transactional注解
     * @param fromName
     * @param toName
     * @param isTableOut
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProcessship(String fromName, String toName,Boolean isTableOut) {
        ProcessEntity processEntity;
        String tableName;
        if (isTableOut){
            processEntity=processRepository.findByName(toName);
            tableName=fromName;
        }else {
            processEntity=processRepository.findByName(fromName);
            tableName=toName;
        }
         Map<String,Long> friendshipRelationList = processEntity.getProcessRelationList();
         Long guid=  friendshipRelationList.get(tableName);
         processRelationRepository.deleteById(guid);

    }

    @Override
    public void saveTableship(String fromName, String toName, Boolean isTableOut) {
        TableEntity from = tableRepository.findByName(fromName);
        TableEntity to = tableRepository.findByName(toName);
        ProcessRelation friendshipRelation = new ProcessRelation();
        friendshipRelation.setEntityStart(from);
        friendshipRelation.setEntityEnd(to);
        from.addProcessRelation(friendshipRelation);
        processRelationRepository.save(friendshipRelation);
    }

    @Override
    public ProcessRelation getEdge(Long guid){
        ProcessRelation processRelation = processRelationRepository.findById(guid).get();
        return processRelation;

    }
}