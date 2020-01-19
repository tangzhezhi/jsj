package org.tang.jsj.biz.conumer.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserVo {

    private String token;
    private String name;
    private String phone;
    private String sex;
}
