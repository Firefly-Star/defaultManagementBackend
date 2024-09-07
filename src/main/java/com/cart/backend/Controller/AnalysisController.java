package com.cart.backend.Controller;

import com.cart.backend.DAO.AnalysisMapper;
import com.cart.backend.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class AnalysisController {

    @Autowired
    AnalysisMapper analysisMapper;


    @RequestMapping("/api/admin/analysis/industry")
    public Result industryAnalysis(@RequestBody int year)
    {
        return Result.Success(analysisMapper.getIndustryAnalysisByYear(year));
    }

    @RequestMapping("/api/admin/analysis/area")
    public Result areaAnalysis(@RequestBody int year)
    {
        return Result.Success(analysisMapper.getAreaAnalysisByYear(year));
    }

    @RequestMapping("/api/admin/analysis/total")
    public Result totalAnalysis()
    {
        return Result.Success((analysisMapper.getTotalClaims()));
    }

}
