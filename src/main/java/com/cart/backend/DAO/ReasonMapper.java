package com.cart.backend.DAO;

import com.cart.backend.Entity.Reason;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReasonMapper {

    public List<Reason> SelectReason(Reason reason);

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO reason(cause, enable, type) " +
            "VALUES(#{cause}, 1, #{type}) ")
    public void InsertReason(Reason reason);

    @Update("UPDATE reason " +
            "SET cause=#{cause}, enable=#{enable} " +
            "WHERE id=#{id}")
    public void UpdateReason(Reason reason);

    @Delete("DELETE FROM reason WHERE id=#{id}")
    public void DeleteReason(Reason reason);
}
