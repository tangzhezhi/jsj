package org.tang.jsj.biz.conumer.vo;

import lombok.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeVo {
    private String msgId;
    private String title;
    private String summary;
    private String thumbImg;
    private String publisher;
    private String publishTime;
}
