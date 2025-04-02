package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.BaseTask;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.STask;
import com.lokyoh.hduspm.mapper.TaskMapper;
import com.lokyoh.hduspm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    /**
     * @param pageNum
     * @param pageSize
     * @param name
     * @param assignedTo
     * @param dueDate
     * @param status
     * @return
     */
    @Override
    public PageBean<STask> s_list(Integer pageNum, Integer pageSize, Long id, String name, Long assignedTo, LocalDate dueDate, String status) {
        PageBean<STask> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<STask> rs = taskMapper.s_list(id, name, assignedTo, dueDate, status);
        PageInfo<STask> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }

    /**
     * @param task
     */
    @Override
    public void create(BaseTask task) {
        taskMapper.create(task);
    }

    /**
     * @param task
     */
    @Override
    public void change(BaseTask task) {
        taskMapper.change(task);
    }

    /**
     * @param tid
     * @param sid
     */
    @Override
    public void addStudent(Long tid, Long sid) {
        taskMapper.addStudent(tid, sid);
    }
}
