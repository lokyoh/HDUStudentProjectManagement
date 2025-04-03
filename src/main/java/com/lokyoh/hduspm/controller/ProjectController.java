package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.BaseProject;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.entity.SProject;
import com.lokyoh.hduspm.service.ProjectService;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/project")
@Component
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/student/list")
    public Result<PageBean<SProject>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long creatorId,
            @RequestParam(required = false) Long classId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String reviewStatus
    ) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long id = Long.parseLong(map.get("id").toString());
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
        PageBean<SProject> pb = projectService.s_list(pageNum, pageSize, id, creatorId, classId, status, reviewStatus);
        return Result.success(pb);
    }

    @PutMapping("/create")
    public Result<Object> create(@RequestBody BaseProject project){
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        project.setCreatorId(id);
        projectService.create(project);
        return Result.success();
    }

    @PutMapping("/change")
    public Result<Object> change(@RequestBody BaseProject project){
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        project.setCreatorId(id);
        projectService.change(project);
        return Result.success();
    }

    @PutMapping("/addMember")
    public Result<Object> addStudent(@RequestParam Long pid, @RequestParam Long sid){
        projectService.addStudent(pid, sid);
        return Result.success();
    }

    @GetMapping("/get/{id}")
    public Result<Object> get(@PathVariable Long id) {
        return Result.success(projectService.getProject(id));
    }

    @GetMapping("/members/{id}")
    public Result<Object> member(@PathVariable Long id){
        return Result.success(projectService.getProjectMembers(id));
    }
}
