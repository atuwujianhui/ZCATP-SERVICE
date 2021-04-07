package com.fjzcit.zcatp.mapper;

import com.fjzcit.zcatp.model.pub.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    /**
     * 通过全注解的方式来写SQL，不写XML文件
     * @return
     */
    @Select("select * from sys_user")
    List<User> findAll();

    /**
     * “@Results”注解示例
     * @param id
     * @return
     */
    @Results({
            @Result(property = "id", column = "a"),
            @Result(property = "name", column = "b"),
            @Result(property = "age", column = "c"),
            @Result(property = "address", column = "d")
    })
    @Select("select id a, name b, age c, address d from sys_user where id = #{id}")
    User findById(Long id);

    /**
     * 通过XML配置的方式
     * @return
     */
    Integer getUserCount();

    Integer addUser(User user);

    Integer updateUserById(User user);

    Integer deleteUserById(Integer id);
}