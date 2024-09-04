package com.cart.backend.Controller;

import com.cart.backend.DAO.ReasonMapper;
import com.cart.backend.Entity.Reason;
import com.cart.backend.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReasonController {

    @Autowired
    private ReasonMapper reasonMapper;

    @RequestMapping("/api/admin/reasons")
    public Result adminReasonList(@RequestBody Reason reason) {

        if (!StringUtils.hasLength(reason.getCause()))
        {
            reason.setCause(null);
        }
        if (!StringUtils.hasLength(reason.getType()))
        {
            reason.setType(null);
        }
        List<Reason> re = reasonMapper.SelectReason(reason);
        return Result.Success(re);
    }

    @RequestMapping("/api/user/reasons")
    public Result userReasonList(@RequestBody Reason reason) {

        if (!StringUtils.hasLength(reason.getCause()))
        {
            reason.setCause(null);
        }
        if (!StringUtils.hasLength(reason.getType()))
        {
            reason.setType(null);
        }
        List<Reason> re = reasonMapper.SelectReason(reason);
        return Result.Success(re);
    }

    @RequestMapping("/api/admin/addReason")
    public Result AddReason(@RequestBody Reason reason) {
        reasonMapper.InsertReason(reason);

        if (reason.getId() != null)
        {
            return Result.Success(null);
        }
        else
        {
            return Result.Fail("Failed to add reason");
        }
    }

    @RequestMapping("/api/admin/editReason")
    public Result editReason(@RequestBody Reason reason) {
        reasonMapper.UpdateReason(reason);
        return Result.Success(null);
    }

    @RequestMapping("/api/admin/deleteReason")
    public Result deleteReasons(@RequestBody Reason reason) {
        reasonMapper.DeleteReason(reason);
        return Result.Success(null);
    }
}