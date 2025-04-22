package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.*;

import java.util.List;

public interface ProjectService {
    PageBean<ProjectStudent> list(int pageNum, int pageSize, Long id, Long creatorId, Long classId, String status, String reviewStatus, String role);

    ProjectStudent getProject(Long id, Long uid);

    void create(BaseProject project);

    void change(BaseProject project);

    void addStudent(Long pId, Long sId);

    List<Member> getProjectMembers(Long id);
}
