package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.ClassesInfo;
import com.lokyoh.hduspm.entity.PageBean;

public interface ClassService {
    PageBean<Classes> list(Integer pageNum, Integer pageSize, Long id, String role);

    ClassesInfo getClassInfo(Long id, Long uid);
}
