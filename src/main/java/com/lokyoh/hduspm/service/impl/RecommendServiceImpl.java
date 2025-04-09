package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.mapper.RecommendMapper;
import com.lokyoh.hduspm.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RecommendMapper recommendMapper;
}
