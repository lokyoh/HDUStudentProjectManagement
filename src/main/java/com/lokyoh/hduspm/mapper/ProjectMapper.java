package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.BaseProject;
import com.lokyoh.hduspm.entity.Project;
import com.lokyoh.hduspm.entity.ProjectMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> sList(Long id, Long creatorId, Long classId, String status, String reviewStatus);

    Project getProject(Long id);

    void create(BaseProject project);

    void change(BaseProject project);

    void addStudent(Long pId, Long sId);

    List<ProjectMember> getProjectMembers(Long id);

    String getRole(Long id, Long uid);
}
