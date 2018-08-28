package spring.cloud.exapmle.testservice.dao;

import org.apache.ibatis.annotations.*;
import spring.cloud.exapmle.testservice.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User queryById(@Param("id") int id);

    @Select("SELECT * FROM user")
    List<User> queryAll();

    @Insert({"INSERT INTO user(name,age,hobby) VALUES(#{name},#{age},#{hobby})"})
    int add(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int delById(int id);

    @Update("UPDATE user SET name=#{name},age=#{age},hobby=#{hobby} WHERE id = #{id}")
    int updateById(User user);
}
