package org.tang.jsj.biz.provider.service;

import org.tang.jsj.biz.dto.UserDTO;


public interface UserService {

    UserDTO selectUserOne(String id);

    UserDTO selectUserOneFromSlave(String id);
}
