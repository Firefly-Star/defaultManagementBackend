package com.cart.backend.Controller;

import com.cart.backend.DAO.ReasonMapper;
import com.cart.backend.Entity.Reason;
import com.cart.backend.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8081/user"})
public class ReasonController {

    @Autowired
    private ReasonMapper reasonMapper;

    @RequestMapping("/api/admin/reasons")
    public Result adminReasonList(@RequestBody Reason reason) {

        List<Reason> re = reasonMapper.SelectReason(reason);

        return Result.Success(re);
    }

    @RequestMapping("/api/user/reasons")
    public Result userReasonList(@RequestBody Reason reason) {

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
