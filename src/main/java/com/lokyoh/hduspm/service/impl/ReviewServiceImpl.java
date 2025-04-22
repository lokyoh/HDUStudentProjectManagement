package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.mapper.ProjectMapper;
import com.lokyoh.hduspm.mapper.ReviewMapper;
import com.lokyoh.hduspm.mapper.UserMapper;
import com.lokyoh.hduspm.service.ProjectService;
import com.lokyoh.hduspm.service.ReviewService;
import com.lokyoh.hduspm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * @param id
     * @return
     */
    @Override
    public PageBean<Review> list(int pageNum, int pageSize, Long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageBean<Review> pb = new PageBean<>();
        List<Review> reviewList = reviewMapper.getTeacherReviews(id);
        PageInfo<Review> p = new PageInfo<>(reviewList);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }

    /**
     * @param id
     * @param uid
     * @return
     */
    @Override
    public ReviewInfo getReview(Long id, Long uid) {
        Review review = reviewMapper.getReview(id);
        if (uid == null || review== null) return null;
        ProjectStudent project = projectMapper.getProject(review.getProjectId());
        Long tid = userMapper.teacherInfo(uid).getId();
        if (Objects.equals(tid, project.getTeacherId())) {
            ReviewInfo reviewInfo = new ReviewInfo(review);
            TaskInfo task = taskService.getTask(review.getTaskId(), uid);
            reviewInfo.setTask(task);
            reviewInfo.setProject(project);
            return reviewInfo;
        }
        return null;
    }
}
