package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.entity.SProject;
import com.lokyoh.hduspm.service.ClassService;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/class")
@Component
public class ClassesController {
    @Autowired
    private ClassService classService;

    @GetMapping("/list")
    public Result<PageBean<Classes>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
        String role = map.get("role").toString();
        switch (role){
            case "学生":
                PageBean<Classes> pb = classService.s_list(pageNum, pageSize, id);
                return Result.success(pb);
            case "教师":
                break;
            case "管理员":
                break;
        }
        return Result.error("null");
    }
}
