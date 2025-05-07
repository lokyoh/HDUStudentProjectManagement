package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcement")
@Component
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/list")
    private Result<Object> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ){
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
        return Result.success(announcementService.list(pageNum, pageSize));
    }

    @GetMapping("/get/{id}")
    private Result<Object> get(@PathVariable Integer id){
        return Result.success(announcementService.get(id));
    }
}
