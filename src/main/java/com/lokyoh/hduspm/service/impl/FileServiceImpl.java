package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.mapper.FileMapper;
import com.lokyoh.hduspm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
}
