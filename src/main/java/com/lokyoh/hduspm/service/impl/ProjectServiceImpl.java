package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Project;
import com.lokyoh.hduspm.entity.SProject;
import com.lokyoh.hduspm.mapper.ProjectMapper;
import com.lokyoh.hduspm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * @param pageNum 页面数目
     * @param pageSize 页面大小
     * @param creatorId 创建者
     * @param classId 班级
     * @param status 状态
     * @param reviewStatus 审核状态
     * @return 项目列表
     */
    @Override
    public PageBean<SProject> s_list(int pageNum, int pageSize, Long id, Long creatorId, Long classId, String status, String reviewStatus) {
        PageBean<SProject> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<SProject> rs = projectMapper.s_list(id, creatorId, classId, status, reviewStatus);
        PageInfo<SProject> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Project getProject(Long id) {
        return projectMapper.getProject(id);
    }
}
