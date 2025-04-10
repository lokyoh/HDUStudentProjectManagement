package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.PFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    PFile pList(Long id);
}
