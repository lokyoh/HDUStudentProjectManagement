package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.mapper.*;
import com.lokyoh.hduspm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ReviewMapper reviewMapper;

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
    public PageBean<STask> sList(Integer pageNum, Integer pageSize, Long id, String name, Long assignedTo, LocalDate dueDate, String status) {
        PageBean<STask> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<STask> rs = taskMapper.sList(id, name, assignedTo, dueDate, status);
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

    /**
     * @param id
     * @return
     */
    @Override
    public List<Task> pList(Long id, Long uid) {
        return taskMapper.pList(id, uid);
    }

    /**
     * @param id
     * @param uid
     * @return
     */
    @Override
    public TaskInfo getTask(Long id, Long uid) {
        BaseTask task = taskMapper.getTask(id);
        if (task != null) {
            TaskInfo taskInfo = new TaskInfo(task);
            taskInfo.setMembers(taskMapper.getMembers(id));
            taskInfo.setFiles(fileMapper.tList(id));
            taskInfo.setProgress(taskMapper.getProgress(id));
            taskInfo.setReviews(reviewMapper.getReviews(id));
            String role = "visitor";
            if (uid != null)
                role = userMapper.getAccountById(uid).getRole();
            if (role.equals("teacher")) {
                Long tid = projectMapper.getProject(id).getTeacherId();
                if (!Objects.equals(tid, userMapper.teacherInfo(uid).getId())) return null;
            }else if (role.equals("student")) {
                String pRole = projectMapper.getRole(task.getProjectId(), uid);
                if (!pRole.equals("leader")) {
                    for (Member member: taskInfo.getMembers()) {
                        if (member.getId().equals(userMapper.studentInfo(uid).getId())) {
                            return taskInfo;
                        }
                    }
                    return null;
                }else return taskInfo;
            }
            taskInfo.setRole("admin");
            return taskInfo;
        }
        return null;
    }
}
