package com.ht.university.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: ht
 * @Date: Create in 12:33 2020/3/19
 * @Describe:
 * @Last_change:
 */
@Service
public class NoticeService {
    @Autowired
    private NoticeRepository repository;

    /**
     * 查询所有
     * @return
     */
    public List<Notice> getNotices(){
        List<Notice> notices = repository.findAll();
        return notices;
    }

    /**
     * 删除
     * @param id
     */
    public void deleteNotice(Long id){
        repository.deleteById(id);
    }

    /**
     * 新增
     */

    public void addNotice(String title,String content){
        Notice notice=new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateTime(new Date());
        repository.save(notice);
    }

}
