package com.cart.backend;

import com.cart.backend.DAO.AnalysisMapper;
import com.cart.backend.Entity.AreaAnalysis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Test
    void contextLoads() {

        List<AreaAnalysis> l = analysisMapper.getAreaAnalysisByYear(2024);
        System.out.println(l);

    }

}
