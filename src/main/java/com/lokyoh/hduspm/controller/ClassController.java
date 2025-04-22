package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.service.ClassService;
import com.lokyoh.hduspm.utils.JwtUtil;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/class")
@Component
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/list")
    public Result<Object> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestHeader(value = "Authorization", defaultValue = "") String token
    ) {
        if(!token.isEmpty()) {
            Map<String, Object> map = JwtUtil.parseToken(token);
            Long uid = Long.parseLong(map.get("id").toString());
            pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
            pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
            String role = map.get("role").toString();
            PageBean<Classes> pb = classService.list(pageNum, pageSize, uid, role);
            return Result.success(pb);
        }
        return Result.error("no permission");
    }

    @GetMapping("/get/{id}")
    public Result<Object> getClass(@PathVariable Long id, @RequestHeader(value = "Authorization", defaultValue = "") String token) {
        Long uid = null;
        if(!token.isEmpty()) {
            Map<String, Object> map = JwtUtil.parseToken(token);
            uid = Long.parseLong(map.get("id").toString());
        }
        return Result.success(classService.getClassInfo(id, uid));
    }
}
