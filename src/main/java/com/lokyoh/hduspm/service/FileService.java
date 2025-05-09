package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.PFile;

public interface FileService {
    PFile pList(Long id);

    void addFile();
}
