package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.PageBean;

public interface ClassService {
    PageBean<Classes> s_list(Integer pageNum, Integer pageSize, Long id);
}
