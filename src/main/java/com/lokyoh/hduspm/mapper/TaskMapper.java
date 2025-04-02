package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.BaseTask;
import com.lokyoh.hduspm.entity.STask;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TaskMapper {
    List<STask> s_list(Long id, String name, Long assignedTo, LocalDate dueDate, String status);

    void create(BaseTask task);

    void change(BaseTask task);

    void addStudent(Long tid, Long sid);
}
