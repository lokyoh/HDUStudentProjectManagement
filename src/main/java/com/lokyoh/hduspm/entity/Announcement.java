package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private int id;
    private String title;
    private String content;
    private String authorId;
    private String authorName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
