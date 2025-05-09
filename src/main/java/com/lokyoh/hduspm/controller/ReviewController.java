package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.ProjectStudent;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.entity.Review;
import com.lokyoh.hduspm.entity.TaskInfo;
import com.lokyoh.hduspm.service.ProjectService;
import com.lokyoh.hduspm.service.ReviewService;
import com.lokyoh.hduspm.service.TaskService;
import com.lokyoh.hduspm.utils.JwtUtil;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/review")
@Component
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public Result<Object> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long id = Long.parseLong(map.get("id").toString());
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
        return Result.success(reviewService.list(pageNum, pageSize, id));
    }

    @GetMapping("/get/{id}")
    public Result<Object> get(@PathVariable Long id, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        if(!token.isEmpty()) {
            Map<String, Object> map = JwtUtil.parseToken(token);
            Long uid = Long.parseLong(map.get("id").toString());
            return Result.success(reviewService.getReview(id, uid));
        }else{
            return Result.error("no permission");
        }
    }

    @PostMapping("/new")
    public Result<Object> newReview(@RequestParam Long id, @RequestHeader(value = "Authorization", defaultValue = "") String token){
        if(!token.isEmpty()) {
            Map<String, Object> map = JwtUtil.parseToken(token);
            Long uid = Long.parseLong(map.get("id").toString());
            TaskInfo taskInfo = taskService.getTask(id, uid);
            ProjectStudent project = projectService.getProject(taskInfo.getProjectId(), uid);
            reviewService.newReview(id, project.getTeacherId());
            return Result.success();
        }else{
            return Result.error("no permission");
        }
    }

    @PostMapping("/del")
    public Result<Object> delReview(@RequestParam Long id) {
        reviewService.delReview(id);
        return Result.success();
    }

    @PostMapping("/check")
    public Result<Object> checkReview(@RequestBody Review review){
        reviewService.check(review);
        return Result.success();
    }
}
