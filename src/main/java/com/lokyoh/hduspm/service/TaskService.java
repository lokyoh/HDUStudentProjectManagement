package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.BaseTask;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.STask;

import java.time.LocalDate;

public interface TaskService {
    PageBean<STask> sList(Integer pageNum, Integer pageSize, Long id, String name, Long assignedTo, LocalDate dueDate, String status);

    void create(BaseTask task);

    void change(BaseTask task);

    void addStudent(Long tid, Long sid);
}
