package com.lokyoh.hduspm.controller.StudentController;

import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.SProject;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.service.ProjectService;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student/project")
@Component
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public Result<PageBean<SProject>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long creatorId,
            @RequestParam(required = false) Long classId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String reviewStatus
    ) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) return Result.error("pageNum错误");
        if (pageSize > 30 || pageSize < 1) return Result.error("pageSize错误");
        PageBean<SProject> pb = projectService.s_list(pageNum, pageSize, id, creatorId, classId, status, reviewStatus);
        return Result.success(pb);
    }

    @GetMapping("/get/{id}")
    public Result<Object> get(@PathVariable Long id) {
        return Result.success(projectService.getProject(id));
    }
}
