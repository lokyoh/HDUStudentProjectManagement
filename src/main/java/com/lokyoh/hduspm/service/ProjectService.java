package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Project;
import com.lokyoh.hduspm.entity.SProject;

public interface ProjectService {
    PageBean<SProject> s_list(int pageNum, int pageSize, Long id, Long creatorId, Long classId, String status, String reviewStatus);

    Project getProject(Long id);
}
