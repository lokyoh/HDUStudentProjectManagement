package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.BaseTask;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.STask;
import com.lokyoh.hduspm.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    PageBean<STask> sList(Integer pageNum, Integer pageSize, Long id, String name, Long assignedTo, LocalDate dueDate, String status);

    void create(BaseTask task);

    void change(BaseTask task);

    void addStudent(Long tid, Long sid);

    List<Task> pList(Long id, Long uid);
}
