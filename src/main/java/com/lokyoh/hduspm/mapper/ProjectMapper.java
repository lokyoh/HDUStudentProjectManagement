package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.BaseProject;
import com.lokyoh.hduspm.entity.ProjectStudent;
import com.lokyoh.hduspm.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<ProjectStudent> sList(Long id, Long creatorId, Long classId, String status, String reviewStatus);

    ProjectStudent getProject(Long id);

    void create(BaseProject project);

    void change(BaseProject project);

    void addStudent(Long pId, Long sId);

    List<Member> getProjectMembers(Long id);

    String getRole(Long id, Long uid);

    List<BaseProject> getClassProjects(Long id);

    List<ProjectStudent> tList(Long id, Long creatorId, Long classId, String status, String reviewStatus);
}
