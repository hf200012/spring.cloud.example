package spring.cloud.security.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import spring.cloud.security.domain.SysUser;


@Mapper
public interface SysUserRepository {
    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(String username);

    @Select("select * from sys_user where username=#{username} and password=#{password}")
    SysUser findByUsernameAndPassword(String name, String password);
}
