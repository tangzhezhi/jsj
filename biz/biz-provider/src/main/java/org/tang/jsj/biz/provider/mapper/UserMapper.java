package org.tang.jsj.biz.provider.mapper;

import org.apache.ibatis.annotations.Param;
import org.tang.jsj.biz.provider.model.User;


public interface UserMapper {

    int insert(@Param("id") String id,@Param("name") String name, @Param("age") Integer age);

    User selectOne(@Param("id") String id);
}
