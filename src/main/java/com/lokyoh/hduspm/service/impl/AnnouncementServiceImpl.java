package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.Announcement;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.mapper.AnnouncementMapper;
import com.lokyoh.hduspm.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Announcement> list(Integer pageNum, Integer pageSize) {
        PageBean<Announcement> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Announcement> la = announcementMapper.list();
        PageInfo<Announcement> pageInfo = new PageInfo<>(la);
        pageBean.setCount(pageInfo.getTotal());
        pageBean.setItems(pageInfo.getList());
        return pageBean;
    }
}
