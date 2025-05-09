package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TaskMapper {
    List<STask> sList(Long id, String name, Long assignedTo, LocalDate dueDate, String status);

    void create(BaseTask task);

    void change(BaseTask task);

    void addStudent(Long tId, Long sId, String role);

    List<Task> pList(Long id, Long uid);

    BaseTask getTask(Long id);

    List<Member> getMembers(Long id);

    TaskProgress getProgress(Long id);
}
