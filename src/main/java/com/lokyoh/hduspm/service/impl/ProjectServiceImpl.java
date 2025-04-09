package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.mapper.ProjectMapper;
import com.lokyoh.hduspm.mapper.UserMapper;
import com.lokyoh.hduspm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;

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
    public PageBean<Project> sList(int pageNum, int pageSize, Long id, Long creatorId, Long classId, String status, String reviewStatus) {
        PageBean<Project> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Project> rs = projectMapper.sList(id, creatorId, classId, status, reviewStatus);
        PageInfo<Project> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Project getProject(Long id, Long uid) {
        Project project = projectMapper.getProject(id);
        if (project != null) {
            project.setRole(projectMapper.getRole(id, uid));
        }
        return project;
    }

    /**
     * @param project
     */
    @Override
    @Transactional
    public void create(BaseProject project) {
        project.setStartDate(LocalDate.now());
        project.setCreatorId(userMapper.getStudentId(project.getCreatorId()));
        projectMapper.create(project);
        projectMapper.addStudent(project.getId(), project.getCreatorId());
    }

    /**
     * @param project
     */
    @Override
    public void change(BaseProject project) {
        projectMapper.change(project);
    }

    /**
     * @param pId
     * @param sId
     */
    @Override
    public void addStudent(Long pId, Long sId) {
        projectMapper.addStudent(pId, sId);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<ProjectMember> getProjectMembers(Long id) {
        return projectMapper.getProjectMembers(id);
    }
}
