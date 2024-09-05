package com.cart.backend.Controller;

import com.cart.backend.DAO.ClaimMapper;
import com.cart.backend.Entity.Claim;
import com.cart.backend.Entity.ClaimSelectParam;
import com.cart.backend.Entity.Result;
import com.cart.backend.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class ClaimController {

    @Autowired
    private ClaimMapper claimMapper;

    @RequestMapping("/api/user/claim")
    public Result CreateDefaultCalim(@RequestHeader("userToken") String userToken, @RequestBody Claim claim)
    {
        Map<String, Object> claims = JwtUtils.parseJwt(userToken);
        Integer id = (Integer)claims.get("id");

        claim.setClaimerId(id);
        claim.setReviewStatus("未审核");
        claim.setClaimTime(new Timestamp(System.currentTimeMillis()));

        claimMapper.InsertClaim(claim);

        if(claim.getId() != null)
        {
            return Result.Success(null);
        }
        else
        {
            return Result.Fail("Failed to create default claim");
        }
    }

    @RequestMapping("/api/admin/claim-reviews")
    public Result ReviewClaim(@RequestHeader("adminToken") String adminToken, @RequestBody Claim claim)
    {
        Map<String, Object> claims = JwtUtils.parseJwt(adminToken);
        Integer id = (Integer)claims.get("id");

        claim.setReviewerId(id);
        claim.setReviewTime(new Timestamp(System.currentTimeMillis()));

        claimMapper.UpdateClaim(claim);
        return Result.Success(null);
    }

    @RequestMapping("/api/user/claims")
    public Result UserSelectClaim(@RequestHeader("userToken") String userToken, @RequestBody ClaimSelectParam param)
    {

        Map<String, Object> claims = JwtUtils.parseJwt(userToken);
        Integer id = (Integer)claims.get("id");

        param.setClaimerId(id);

        List<Claim> res = claimMapper.SelectClaim(param);
        return Result.Success(res);
    }

    @RequestMapping("/api/admin/claims")
    public Result UserSelectClaim(@RequestBody ClaimSelectParam param)
    {

        List<Claim> claims = claimMapper.SelectClaim(param);
        return Result.Success(claims);
    }



}
