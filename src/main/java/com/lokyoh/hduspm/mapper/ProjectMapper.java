package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<ProjectStudent> sList(Long id, Long creatorId, Long classId, String status, Long teacherId);

    ProjectStudent getProject(Long id);

    void create(BaseProject project);

    void change(BaseProject project);

    void addStudent(Long pId, Long sId, String role);

    List<Member> getProjectMembers(Long id);

    String getRole(Long id, Long uid);

    List<BaseProject> getClassProjects(Long id);

    List<ProjectStudent> tList(Long id, Long creatorId, Long classId, String status, Long teacherId);

    List<ProjectStudent> list(Long creatorId, Long classId, String status, Long teacherId);

    List<Param> getSCP(Long uid);

    List<Param> getSTP(Long uid);

    List<Param> getSSP(Long uid);

    List<Param> getTCP(Long uid);

    List<Param> getTSP(Long uid);

    List<Param> getSP(Long uid);

    List<Param> getCP(Long uid);

    List<Param> getTP(Long uid);
}
