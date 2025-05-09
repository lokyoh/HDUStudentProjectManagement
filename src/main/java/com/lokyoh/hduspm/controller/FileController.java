package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
@RequestMapping("/file")
@Component
public class FileController {
    private static final String File_DIR = "files/";

    @Autowired
    private FileService fileService;

    @GetMapping("/project/{id}")
    public Result<Object> getProjectList(@PathVariable Long id) {
        return Result.success(fileService.pList(id));
    }

    @PostMapping("/upload")
    public Result<Object> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请选择文件上传");
        }
        try {
            Path uploadPath = Paths.get(File_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            fileService.addFile();
            return Result.success();
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/download")
    public Result<Object> downloadFile(@RequestParam("file") MultipartFile file) {
        return Result.success();
    }
}
