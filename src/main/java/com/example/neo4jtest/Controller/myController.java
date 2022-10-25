package com.example.neo4jtest.Controller;

import com.example.neo4jtest.Entiity.Lineage.LineageInfo;
import com.example.neo4jtest.Entiity.Node.ColumnEntity;
import com.example.neo4jtest.Entiity.Node.EntityStruct;
import com.example.neo4jtest.Entiity.Node.TableEntity;
import com.example.neo4jtest.Entiity.Node.ProcessEntity;
import com.example.neo4jtest.Entiity.Relationship.ProcessRelation;
import com.example.neo4jtest.Repository.Node.ColumnRepository;
import com.example.neo4jtest.Repository.Node.EntityRepository;
import com.example.neo4jtest.Repository.Node.TableRepository;
import com.example.neo4jtest.Repository.Node.ProcessRepository;
import com.example.neo4jtest.Repository.Relationship.ProcessRelationRepository;
import com.example.neo4jtest.Service.EntityStoreImpl;
import com.example.neo4jtest.Service.LineageService;
import com.example.neo4jtest.Service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@RestController
public class myController {

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ProcessRelationRepository processRelationRepository;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private LineageService lineageService;
    @GetMapping("/create2")
    public void testCreate2() {

        tableRepository.deleteAll();
        processRepository.deleteAll();
        processRelationRepository.deleteAll();
    }
    @GetMapping("/create3")
    public void testCreate3() {

        tableRepository.deleteAll();
        processRelationRepository.deleteAll();
        TableEntity tableEntity1 =new TableEntity("TABLE");
        tableEntity1.setName("entity1");
        tableEntity1.setType("table");
        TableEntity tableEntity2 =new TableEntity("TABLE");
        tableEntity2.setName("entity2");
        tableEntity2.setType("table");
        TableEntity tableEntity3 =new TableEntity("TABLE");
        tableEntity3.setName("entity3");
        tableEntity3.setType("table");
        TableEntity tableEntity4 =new TableEntity("TABLE");
        tableEntity4.setName("entity4");
        tableEntity4.setType("table");
        List<TableEntity>tableEntities=new ArrayList<>(Arrays.asList(tableEntity1, tableEntity2, tableEntity3, tableEntity4));
        tableRepository.saveAll(tableEntities);

        ProcessEntity process1=new ProcessEntity("PROCESS");
        process1.setName("process1");
        process1.setType("process");
        ProcessEntity process2=new ProcessEntity("PROCESS");
        process2.setName("process2");
        process2.setType("process");
        List<ProcessEntity>processEntities=new ArrayList<>(Arrays.asList(process1,process2));
        processRepository.saveAll(processEntities);

        HashMap<String, Object> relationshipAttribute=new HashMap<>();

        relationshipAttribute.put("inputs", tableEntity1.toString());
        relationshipAttribute.put("outputs", tableEntity2.toString());
        ProcessRelation processRelation1=new ProcessRelation(tableEntity1,process1,"Process_INPUT_EDGE",relationshipAttribute);
        ProcessRelation processRelation2=new ProcessRelation(tableEntity2,process1,"Process_INPUT_EDGE",relationshipAttribute);
        ProcessRelation processRelation3=new ProcessRelation(tableEntity3,process2,"Process_INPUT_EDGE",relationshipAttribute);
        ProcessRelation processRelation4=new ProcessRelation(process1, tableEntity3,"Process_OUTPUT_EDGE",relationshipAttribute);
        ProcessRelation processRelation5=new ProcessRelation(process2, tableEntity4,"Process_OUTPUT_EDGE",relationshipAttribute);
        List<ProcessRelation> processRelations=new ArrayList<>(Arrays.asList(processRelation1,processRelation2,processRelation3,processRelation4,processRelation5));
        processRelationRepository.saveAll(processRelations);

    }

    @GetMapping("/createTableColumn")
    public void testCreate5(){
        columnRepository.deleteAll();
        ColumnEntity columnEntity1=new ColumnEntity("COLUMN");
        columnEntity1.setName("column1");
        ColumnEntity columnEntity2=new ColumnEntity("COLUMN");
        columnEntity2.setName("column2");
        ColumnEntity columnEntity3=new ColumnEntity("COLUMN");
        columnEntity3.setName("column3");
        List<ColumnEntity>columnEntitys=new ArrayList<>(Arrays.asList(columnEntity1, columnEntity2, columnEntity3));
        columnRepository.saveAll(columnEntitys);
        relationshipService.saveTableColumnEdge(1640914647095901L,columnEntity1.getGuid());
        relationshipService.saveTableColumnEdge(1640914647095901L,columnEntity2.getGuid());
        relationshipService.saveTableColumnEdge(1640914647095901L,columnEntity3.getGuid());
    }
    @GetMapping("/create4")
    public void testCreate4() {

        tableRepository.deleteAll();
        processRelationRepository.deleteAll();
        processRepository.deleteAll();
        TableEntity tableEntity1 =new TableEntity("TABLE");
        tableEntity1.setName("entity11");
        tableEntity1.setType("table");
        TableEntity tableEntity2 =new TableEntity("TABLE");
        tableEntity2.setName("entity22");
        tableEntity2.setType("table");
        TableEntity tableEntity3 =new TableEntity("TABLE");
        tableEntity3.setName("entity33");
        tableEntity3.setType("table");
        TableEntity tableEntity4 =new TableEntity("TABLE");
        tableEntity4.setName("entity44");
        tableEntity4.setType("table");
        List<TableEntity>tableEntities=new ArrayList<>(Arrays.asList(tableEntity1, tableEntity2, tableEntity3, tableEntity4));
        tableRepository.saveAll(tableEntities);

        ProcessEntity process1=new ProcessEntity("PROCESS");
        process1.setName("process11");
        process1.setType("process");
        ProcessEntity process2=new ProcessEntity("PROCESS");
        process2.setName("process22");
        process2.setType("process");
        List<ProcessEntity>processEntities=new ArrayList<>(Arrays.asList(process1,process2));
        processRepository.saveAll(processEntities);

        relationshipService.saveProcessship(tableEntity1.getName(),process1.getName(),true);
        relationshipService.saveProcessship(tableEntity2.getName(),process1.getName(),true);
        relationshipService.saveProcessship(tableEntity3.getName(),process2.getName(),true);
        relationshipService.saveProcessship(process1.getName(), tableEntity3.getName(),false);
        relationshipService.saveProcessship(process2.getName(), tableEntity4.getName(),false);


    }

    @GetMapping("/delete1")
    public void testdelete1() {
        relationshipService.deleteProcessship("entity11","process11",true);

    }
    @GetMapping("/getin1")
    public void getin1() {
        List<ProcessEntity> entityStructs1 = processRelationRepository.inRelationship(9877941150505L);
        List<ProcessEntity> entityStructs2 = processRelationRepository.outRelationship("process22");
        List<ProcessEntity> entityStructs3 = processRelationRepository.bothRelationship("process22");
        System.out.println(entityStructs1.toString()+entityStructs2.toString()+entityStructs3.toString());

    }
    @GetMapping("/getrelation")
    public void testget1() {
        Optional<ProcessRelation> byId = processRelationRepository.findById(9877939427703L);
        ProcessRelation processRelation = byId.get();
        System.out.println(processRelation);

    }
    @GetMapping("/getlineage")
    public void testget2() {
        LineageInfo entityLineageInfo0 = lineageService.getEntityLineageInfo(40079075437803L, LineageInfo.LineageDirection.INPUT,0);
        LineageInfo entityLineageInfo3 = lineageService.getEntityLineageInfo(40079075437803L, LineageInfo.LineageDirection.INPUT,3);
        LineageInfo entityLineageInf1 = lineageService.getEntityLineageInfo(40079075437803L, LineageInfo.LineageDirection.INPUT,4);
        LineageInfo entityLineageInfo5 = lineageService.getEntityLineageInfo(40079075437803L, LineageInfo.LineageDirection.INPUT,5);
        System.out.println(entityLineageInfo0.toString());
    }

    @GetMapping("/importTable")
    public void import1(){
        tableRepository.imporTabeleCsv();

    }
    @GetMapping("/importRL")
    public void import2(){
        processRelationRepository.improtRrocessRelationById();
    }
    @GetMapping("/getEntity1")
    public void getEntity1(){
        EntityStoreImpl entityStore = new EntityStoreImpl();
        EntityStruct entityById = entityStore.getEntityById(1740914647095901L);
        TableEntity tableEntity= (TableEntity) entityById;
        System.out.println(tableEntity);

    }
    @GetMapping("/create6")
    public void testCreate6() {

        tableRepository.deleteAll();
        processRelationRepository.deleteAll();
        processRepository.deleteAll();
        columnRepository.deleteAll();
        TableEntity tableEntity1 =new TableEntity("组织");
        tableEntity1.setName("云南建投集团");
        tableEntity1.setType("一级节点");
        TableEntity tableEntity2 =new TableEntity("组织");
        tableEntity2.setName("上海建工集团股份有限公司");
        tableEntity2.setType("二级节点");
        TableEntity tableEntity3 =new TableEntity("组织");
        tableEntity3.setName("上海建工集团总承包");
        tableEntity3.setType("三级节点");
        TableEntity tableEntity4 =new TableEntity("组织");
        tableEntity4.setName("组织288");
        tableEntity4.setType("三级节点");
        ColumnEntity columnEntity1=new ColumnEntity("人员");
        columnEntity1.setName("sm");
        ColumnEntity columnEntity2=new ColumnEntity("人员");
        columnEntity2.setName("fhl");
        ColumnEntity columnEntity3=new ColumnEntity("人员");
        columnEntity3.setName("flx");
        ColumnEntity columnEntity4=new ColumnEntity("人员");
        columnEntity4.setName("盼盼");
        ColumnEntity columnEntity5=new ColumnEntity("人员");
        columnEntity5.setName("张金强");
        List<TableEntity>tableEntities=new ArrayList<>(Arrays.asList(tableEntity1, tableEntity2, tableEntity3, tableEntity4));
        tableRepository.saveAll(tableEntities);
        List<ColumnEntity>columnEntitys=new ArrayList<>(Arrays.asList(columnEntity1, columnEntity2, columnEntity3,columnEntity4,columnEntity5));
        columnRepository.saveAll(columnEntitys);
        relationshipService.saveTableship(tableEntity1.getName(),tableEntity2.getName(),true);
        relationshipService.saveTableship(tableEntity2.getName(),tableEntity3.getName(),true);
        relationshipService.saveTableship(tableEntity2.getName(), tableEntity4.getName(),true);
        relationshipService.saveTableColumnEdge(tableEntity3.getGuid(),columnEntity1.getGuid());
        relationshipService.saveTableColumnEdge(tableEntity4.getGuid(),columnEntity2.getGuid());
        relationshipService.saveTableColumnEdge(tableEntity4.getGuid(),columnEntity3.getGuid());
        relationshipService.saveTableColumnEdge(tableEntity2.getGuid(),columnEntity4.getGuid());
        relationshipService.saveTableColumnEdge(tableEntity2.getGuid(),columnEntity5.getGuid());


    }
}
