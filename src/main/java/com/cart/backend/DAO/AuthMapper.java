package com.cart.backend.DAO;

import com.cart.backend.Entity.Auth;
import com.cart.backend.Entity.EditPassword;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthMapper {

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into auth (username, password, email, role, name) " +
            "values (#{username}, #{password}, #{email}, #{role}, #{name})")
    public void insertAuth(Auth auth);

    @Select("select * from auth " +
            "where username = #{username} and password = #{password} and role = #{role}")
    public Auth selectAuth(Auth auth);

    @Select("select * from auth " +
            "where id = #{id}")
    public Auth UserInfo(Integer id);

    @Update("update auth set password = #{newPassword} " +
            "where id = #{id} and password = #{oldPassword}")
    public int updateAuth(EditPassword editPassword);

}
