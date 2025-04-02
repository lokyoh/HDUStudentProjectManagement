package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@Component
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/get/{id}")
    public Result<Object> get(@PathVariable Long id) {
        return Result.success(projectService.getProject(id));
    }

    @GetMapping("/member/{id}")
    public Result<Object> member(@PathVariable Long id){
        return Result.success(projectService.getProjectMembers(id));
    }
}
