package com.example.neo4jtest.Service;

import com.example.neo4jtest.Repository.Node.ColumnRepository;
import com.example.neo4jtest.Repository.Node.ProcessRepository;
import com.example.neo4jtest.Repository.Node.TableRepository;
import com.example.neo4jtest.Repository.Relationship.ColumnRelationRepository;
import com.example.neo4jtest.Repository.Relationship.ProcessRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImprotServiceImpl implements ImportService {


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
    public void importCsvData() {
        tableRepository.imporTabeleCsv();
        processRepository.importProcessCsv();
        columnRepository.importColumnCsv();
        processRelationRepository.improtRrocessRelationById();
        columnRelationRepository.improtColumnRelationById();
    }

    @Override
    public void importJsonData() {

    }
}
