package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.entity.PFile;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.mapper.FileMapper;
import com.lokyoh.hduspm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    /**
     * @param id
     * @return
     */
    @Override
    public PFile pList(Long id) {
        return fileMapper.pList(id);
    }
}
