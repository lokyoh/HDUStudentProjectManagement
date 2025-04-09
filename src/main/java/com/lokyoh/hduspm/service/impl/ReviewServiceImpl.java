package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.mapper.ReviewMapper;
import com.lokyoh.hduspm.service.ReviewService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
}
