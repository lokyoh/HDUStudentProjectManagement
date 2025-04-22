package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Component
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/project/{id}")
    public Result<Object> getProjectList(@PathVariable Long id) {
        return Result.success(fileService.pList(id));
    }

    @PutMapping("/upload")
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        return Result.success();
    }

    @GetMapping("/download")
    public Result<Object> downloadFile(@RequestParam("file") MultipartFile file) {
        return Result.success();
    }
}
