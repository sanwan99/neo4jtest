package com.example.neo4jtest.Service;


import org.springframework.stereotype.Repository;

/**
 *
 * @author fhl
 */
@Repository
public interface ImportService {

    /**
     * todo
     */
    void importCsvData();

    /**
     * todo
     */
    void importJsonData();

}
