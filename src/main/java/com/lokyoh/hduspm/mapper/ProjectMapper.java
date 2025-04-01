package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Project;
import com.lokyoh.hduspm.entity.SProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<SProject> s_list(Long id, Long creatorId, Long classId, String status, String reviewStatus);

    Project getProject(Long id);
}
