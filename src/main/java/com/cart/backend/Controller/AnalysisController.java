package com.cart.backend.Controller;


import com.cart.backend.DAO.AnalysisMapper;
import com.cart.backend.Entity.AreaAnalysis;
import com.cart.backend.Entity.IndustryAnalysis;
import com.cart.backend.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class AnalysisController {

    @Autowired
    private AnalysisMapper analysisMapper;

    @RequestMapping("/api/admin/analysis/industry")
    public Result getIndustryAnalysis(@RequestBody Integer year)
    {
        List<IndustryAnalysis> res =  analysisMapper.getIndustryAnalysisByYear(year);

        return Result.Success(res);
    }

    @RequestMapping("/api/admin/analysis/area")
    public Result getAreaAnalysis(@RequestBody Integer year)
    {
        List<AreaAnalysis> res =  analysisMapper.getAreaAnalysisByYear(year);
        return Result.Success(res);
    }

}
