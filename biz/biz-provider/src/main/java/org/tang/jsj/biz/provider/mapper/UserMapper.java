package org.tang.jsj.biz.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.tang.jsj.biz.provider.model.User;

@Mapper
@Component
public interface UserMapper {

//    int insert(@Param("id") String id,@Param("name") String name, @Param("age") Integer age);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User selectOne(@Param("id") String id);
}
