package com.cart.backend.DAO;

import com.cart.backend.Entity.AreaAnalysis;
import com.cart.backend.Entity.IndustryAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnalysisMapper {

    @Select("SELECT * from areaclaimgrowthstats " +
            "where Year = #{Year}")
    public List<AreaAnalysis> getAreaAnalysisByYear(int Year);

    @Select("SELECT * from industryclaimgrowthstats " +
            "where Year = #{Year}")
    public List<IndustryAnalysis> getIndustryAnalysisByYear(int Year);

}

