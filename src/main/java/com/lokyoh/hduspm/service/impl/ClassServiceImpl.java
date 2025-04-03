package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.mapper.ClassMapper;
import com.lokyoh.hduspm.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    /**
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @Override
    public PageBean<Classes> s_list(Integer pageNum, Integer pageSize, Long id) {
        PageBean<Classes> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Classes> rs = classMapper.s_list(id);
        PageInfo<Classes> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }
}
