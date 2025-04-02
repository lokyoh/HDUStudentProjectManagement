package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.*;

import java.util.List;

public interface ProjectService {
    PageBean<SProject> s_list(int pageNum, int pageSize, Long id, Long creatorId, Long classId, String status, String reviewStatus);

    Project getProject(Long id);

    void create(BaseProject project);

    void change(BaseProject project);

    void addStudent(Long pId, Long sId);

    List<ProjectMember> getProjectMembers(Long id);
}
