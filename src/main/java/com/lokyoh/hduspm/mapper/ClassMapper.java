package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Classes> s_list(Long id);
}
