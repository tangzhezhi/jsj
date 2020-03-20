package org.tang.jsj.biz.conumer.vo;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDetailVo {
    private String msgId;
    private String title;
    private String content;
}
