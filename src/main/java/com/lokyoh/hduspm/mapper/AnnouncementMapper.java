package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    List<Announcement> list();
}
