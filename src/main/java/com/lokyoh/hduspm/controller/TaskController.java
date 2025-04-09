package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.BaseTask;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.entity.STask;
import com.lokyoh.hduspm.service.TaskService;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/task")
@Component
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public Result<PageBean<STask>> list(
            @RequestParam Long pId,
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long assignedTo,
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(required = false) String status
    ) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long id = Long.parseLong(map.get("id").toString());
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
        PageBean<STask> pb = taskService.sList(pageNum, pageSize, id, name, assignedTo, dueDate, status);
        return Result.success(pb);
    }

    @GetMapping("/project/{id}")
    public Result<Object> pList(@PathVariable Long id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long uid = Long.parseLong(map.get("id").toString());
        return Result.success(taskService.pList(id, uid));
    }

    @PutMapping("/create")
    public Result<Object> create(@RequestBody BaseTask task){
        taskService.create(task);
        return Result.success();
    }

    @PutMapping("/change")
    public Result<Object> change(@RequestBody BaseTask task){
        taskService.change(task);
        return Result.success();
    }

    @PostMapping("/student/add")
    public Result<Object> addStudent(@RequestParam Long tid, Long sid){
        taskService.addStudent(tid, sid);
        return Result.success();
    }

    @PostMapping("/progress/add")
    public Result<Object> addProgress(@RequestParam Long id){
        return Result.success();
    }
}
