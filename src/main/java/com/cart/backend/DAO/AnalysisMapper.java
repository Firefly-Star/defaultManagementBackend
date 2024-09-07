package com.cart.backend.DAO;

import com.cart.backend.Entity.Analysis;
import com.cart.backend.Entity.AreaAnalysis;
import com.cart.backend.Entity.IndustryAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnalysisMapper {

    @Select("select * from areastatics where year = #{year}")
    public List<AreaAnalysis> getAreaAnalysisByYear(int year);

    @Select("select * from industrystatics where year = #{year}")
    public List<IndustryAnalysis> getIndustryAnalysisByYear(int year);

    @Select("select * from totalclaims")
    public List<Analysis> getTotalClaims();

}
