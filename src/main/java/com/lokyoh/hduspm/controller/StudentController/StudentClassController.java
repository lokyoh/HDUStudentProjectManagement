package com.lokyoh.hduspm.controller.StudentController;

import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Result;
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
@RequestMapping("/student/class")
@Component
public class StudentClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/list")
    public Result<PageBean<Classes>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) return Result.error("pageNum错误");
        if (pageSize > 30 || pageSize < 1) return Result.error("pageSize错误");
        PageBean<Classes> pb = classService.s_list(pageNum, pageSize, id);
        return Result.success(pb);
    }
}
