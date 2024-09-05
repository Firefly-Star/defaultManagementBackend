package com.cart.backend.DAO;

import com.cart.backend.Entity.Claim;
import com.cart.backend.Entity.ClaimSelectParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClaimMapper {

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO claim(clientId, reasonId, reviewStatus, severity, reviewerId, claimTime, reviewTime, externSeverity, type, claimerId) " +
            "VALUES (#{clientId}, #{reasonId}, #{reviewStatus}, #{severity}, #{reviewerId}, #{claimTime}, #{reviewTime}, #{externSeverity}, #{type}, #{claimerId})")
    public void InsertClaim(Claim claim);

    @Update("UPDATE claim SET reviewerId=#{reviewerId}, reviewTime=#{reviewTime}, reviewStatus=#{reviewStatus} " +
            "WHERE id=#{id}")
    public void UpdateClaim(Claim claim);

    public List<Claim> SelectClaim(ClaimSelectParam param);
}
