package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    Teacher info(Long id);

    void changeInfo(Teacher teacher);

    void add(Teacher teacher);
}
