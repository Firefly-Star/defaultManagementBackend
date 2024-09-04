package com.cart.backend.DAO;

import com.cart.backend.Entity.Auth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into auth (username, password, email, role) " +
            "values (#{username}, #{password}, #{email}, #{role})")
    public void insertAuth(Auth auth);

    @Select("select id from auth " +
            "where username = #{username} and password = #{password} and role = #{role}")
    public Integer selectAuth(Auth auth);

}
