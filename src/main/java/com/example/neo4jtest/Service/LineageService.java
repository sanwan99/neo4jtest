package com.example.neo4jtest.Service;

import com.example.neo4jtest.Entiity.Lineage.LineageInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface LineageService {


    LineageInfo getEntityLineageInfo(Long guid, LineageInfo.LineageDirection direction, int depth);
    LineageInfo getEntityLineageInfo(Long guid, LineageInfo.LineageDirection direction);
}
