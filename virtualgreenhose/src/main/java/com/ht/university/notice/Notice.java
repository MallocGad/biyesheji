package com.ht.university.notice;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: ht
 * @Date: Create in 12:27 2020/3/19
 * @Describe:
 * @Last_change:
 */
@Entity
@Table(name = "notice")
public class Notice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long noticeId;
    private String title;
    private String content;
    private Date createTime;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
