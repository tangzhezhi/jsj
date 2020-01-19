package org.tang.jsj.biz.conumer.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuVo {
    private String code;
    private String name;
    private String iconUrl;
    private String url;
    private boolean hasNewMsg;
    private boolean isNeedOrder = false;
    private int isShow = 1;
}
