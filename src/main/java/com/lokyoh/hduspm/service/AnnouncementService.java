package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.Announcement;
import com.lokyoh.hduspm.entity.PageBean;

public interface AnnouncementService {
    PageBean<Announcement> list(Integer pageNum, Integer pageSize);

    Announcement get(Integer id);
}
