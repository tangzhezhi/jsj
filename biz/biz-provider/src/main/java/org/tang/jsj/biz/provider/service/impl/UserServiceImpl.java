package org.tang.jsj.biz.provider.service.impl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Component;
import org.tang.jsj.biz.dto.UserDTO;
import org.tang.jsj.biz.provider.mapper.UserMapper;
import org.tang.jsj.biz.provider.model.User;
import org.tang.jsj.biz.provider.service.UserService;
import javax.annotation.Resource;

@Service(version = "1.0.0",timeout = 60000)
@Component
public class UserServiceImpl  implements UserService {

    @Resource
    private UserMapper userMapper;

//    @DS("master")
//    @Override
//    public void addUser(UserDTO user) {
//        User target = new User();
//        target.setId(IdUtil.fastSimpleUUID());
//        BeanUtil.copyProperties(user,target);
//        userMapper.insert(target.getId(),target.getName(), target.getAge());
//    }

    @DS("master")
    @Override
    public UserDTO selectUserOne(String id) {
        UserDTO target = new UserDTO();
//        User user =  userMapper.selectOne(id);
//        BeanUtil.copyProperties(user,target);
        return target;
    }

}
