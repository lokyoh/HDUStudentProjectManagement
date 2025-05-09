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

import java.security.Provider;
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
    public PageBean<ProjectStudent> list(int pageNum, int pageSize, Long id, Long creatorId, Long classId, String status, Long teacherId, String role) {
        PageHelper.startPage(pageNum, pageSize);
        PageBean<ProjectStudent> pb = new PageBean<>();
        List<ProjectStudent> rs;
        switch (role) {
            case "student":
                rs = projectMapper.sList(id, creatorId, classId, status, teacherId);
                break;
            case "teacher":
                rs = projectMapper.tList(id, creatorId, classId, status, teacherId);
                break;
            case "admin":
                rs = projectMapper.list(creatorId, classId, status, teacherId);
                break;
            default:
                return null;
        }
        PageInfo<ProjectStudent> p = new PageInfo<>(rs);
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
    public ProjectStudent getProject(Long id, Long uid) {
        ProjectStudent projectStudent = projectMapper.getProject(id);
        if (projectStudent != null) {
            if (uid!=null) projectStudent.setRole(projectMapper.getRole(id, uid));
            else projectStudent.setRole("visitor");
        }
        return projectStudent;
    }

    /**
     * @param project
     */
    @Override
    @Transactional
    public void create(BaseProject project) {
        project.setStartDate(LocalDate.now());
        project.setCreatorId(userMapper.studentInfo(project.getCreatorId()).getId());
        projectMapper.create(project);
        projectMapper.addStudent(project.getId(), project.getCreatorId(), "leader");
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
        projectMapper.addStudent(pId, sId, "member");
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Member> getProjectMembers(Long id) {
        return projectMapper.getProjectMembers(id);
    }

    /**
     * @param uid
     * @param role
     * @return
     */
    @Override
    public SearchParamsProject getSearchParams(Long uid, String role) {
        SearchParamsProject sp = new SearchParamsProject();
        switch (role) {
            case "student":
                sp.setClasses(projectMapper.getSCP(uid));
                sp.setTeachers(projectMapper.getSTP(uid));
                sp.setCreators(projectMapper.getSSP(uid));
                break;
            case "teacher":
                sp.setClasses(projectMapper.getTCP(uid));
                sp.setTeachers(null);
                sp.setCreators(projectMapper.getTSP(uid));
                break;
            case "admin":
                sp.setCreators(projectMapper.getSP(uid));
                sp.setClasses(projectMapper.getCP(uid));
                sp.setTeachers(projectMapper.getTP(uid));
                break;
            default:
                return null;
        }
        return sp;
    }
}
