package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> getReviews(Long id);

    List<Review> getTeacherReviews(Long id);

    Review getReview(Long id);

    void newReview(Long id, Long tid);

    void delReview(Long id);

    void check(Review review);
}
