package org.tang.jsj.biz.conumer.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactVo {

    private String firstPinyin;
    private String name;
    private String sex;
    private String phone;
    private String departName;
    private String pinyin;
}
