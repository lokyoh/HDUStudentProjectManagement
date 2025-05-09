package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.entity.Review;
import com.lokyoh.hduspm.entity.ReviewInfo;

public interface ReviewService {
    PageBean<Review> list(int pageNum, int pageSize, Long id);

    ReviewInfo getReview(Long id, Long uid);

    void newReview(Long id, Long tid);

    void delReview(Long id);

    void check(Review review);
}
