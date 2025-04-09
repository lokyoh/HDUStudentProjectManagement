-- 创建数据库
DROP DATABASE IF EXISTS hduspm;
CREATE DATABASE hduspm;

-- 使用数据库
USE hduspm;

-- 账号表
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '账号ID（唯一标识）',
    username VARCHAR(32) NOT NULL UNIQUE COMMENT '登录用户名',
    password VARCHAR(32) NOT NULL COMMENT '密码',
    role ENUM('student', 'teacher', 'admin') NOT NULL COMMENT '用户角色（学生/教师/管理员）',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号表，存储所有用户的登录信息与权限';

-- 学生信息表
DROP TABLE IF EXISTS students;
CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '学生ID（唯一标识）',
    account_id BIGINT NOT NULL UNIQUE COMMENT '关联账号ID（唯一）',
    name VARCHAR(32) NOT NULL COMMENT '学生姓名',
    student_id VARCHAR(32) NOT NULL COMMENT '学号',
    class_number VARCHAR(32) NOT NULL COMMENT '班级',
    email VARCHAR(64) NULL COMMENT '邮箱',
    phone VARCHAR(32) NULL COMMENT '电话',
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表，存储学生个人信息';

-- 教师信息表
CREATE TABLE teachers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '教师ID（唯一标识）',
    account_id BIGINT NOT NULL UNIQUE COMMENT '关联账号ID（唯一）',
    name VARCHAR(32) NOT NULL COMMENT '教师姓名',
    teacher_id VARCHAR(32) NOT NULL COMMENT '教师号',
    email VARCHAR(64) NULL COMMENT '邮箱',
    phone VARCHAR(32) NULL COMMENT '电话',
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师表，存储教师个人信息';

-- 班级表
DROP TABLE IF EXISTS classes;
CREATE TABLE classes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '班级ID（唯一标识）',
    name VARCHAR(64) NOT NULL COMMENT '班级名称',
    teacher_id BIGINT NOT NULL COMMENT '关联教师ID',
    semester VARCHAR(8) NOT NULL COMMENT '学期',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表，教师创建班级，学生可以加入';

-- 班级关联表
DROP TABLE IF EXISTS student_classes;
CREATE TABLE student_classes (
    student_id BIGINT NOT NULL COMMENT '学生ID',
    class_id BIGINT NOT NULL COMMENT '班级ID',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
    PRIMARY KEY (student_id, class_id),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生班级关联表，记录学生加入的所有班级';

-- 项目表
DROP TABLE IF EXISTS projects;
CREATE TABLE projects (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '项目ID（唯一标识）',
    name VARCHAR(128) NOT NULL COMMENT '项目名称',
    description TEXT NULL COMMENT '项目描述',
    creator_id BIGINT NOT NULL COMMENT '创建者（学生ID）',
    teacher_id BIGINT NULL COMMENT '指导教师（可为空）',
    class_id BIGINT NULL COMMENT '所属班级（可为空）',
    status ENUM('normal', 'finish', 'cancel') NOT NULL DEFAULT 'normal' COMMENT '项目状态',
    start_date DATE NOT NULL COMMENT '项目开始日期',
    end_date DATE NULL COMMENT '项目结束日期',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (creator_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目表，存储学生创建的项目';

-- 项目成员表
DROP TABLE IF EXISTS project_members;
CREATE TABLE project_members (
    project_id BIGINT NOT NULL COMMENT '项目ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    role ENUM('member', 'leader') NOT NULL DEFAULT 'member' COMMENT '学生在项目中的角色（成员或组长）',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
    PRIMARY KEY (project_id, student_id),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目成员表，记录项目参与学生及其角色';

-- 任务表
DROP TABLE IF EXISTS tasks;
CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '任务ID（唯一标识）',
    project_id BIGINT NOT NULL COMMENT '项目ID（关联项目）',
    name VARCHAR(128) NOT NULL COMMENT '任务名称',
    description TEXT NULL COMMENT '任务描述',
    assigned_to BIGINT NOT NULL COMMENT '任务负责人（学生ID）',
    due_date DATE NULL COMMENT '任务截止日期',
    status ENUM('plane', 'normal', 'finish', 'cancel') NOT NULL DEFAULT 'plane' COMMENT '任务状态',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时间',
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_to) REFERENCES students(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务表，记录每个项目的任务和负责人';

-- 任务成员表
DROP TABLE IF EXISTS task_members;
CREATE TABLE task_members (
    task_id BIGINT NOT NULL COMMENT '任务ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    role ENUM('leader', 'member') NOT NULL DEFAULT 'member' COMMENT '学生在任务中的角色（负责人或成员）',
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '分配时间',
    PRIMARY KEY (task_id, student_id),
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务成员表，记录任务参与学生及其角色';

-- 任务进度表
DROP TABLE IF EXISTS task_progress;
CREATE TABLE task_progress (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '进度记录ID（唯一标识）',
    task_id BIGINT NOT NULL COMMENT '任务ID（关联任务）',
    student_id BIGINT NOT NULL COMMENT '更新进度的学生ID',
    progress DECIMAL(5, 2) NOT NULL COMMENT '任务进度百分比（0-100）',
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '进度更新时间',
    description TEXT NULL COMMENT '进度描述（可选）',
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务进度表，记录任务的进展情况';

-- 文件表
DROP TABLE IF EXISTS files;
CREATE TABLE files (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '文件ID（唯一标识）',
    project_id BIGINT NOT NULL COMMENT '关联项目ID',
    task_id BIGINT NULL COMMENT '关联任务ID（可为空，若为项目文件则为空）',
    student_id BIGINT NOT NULL COMMENT '上传文件的学生ID',
    file_name VARCHAR(255) NOT NULL COMMENT '文件名称',
    file_path VARCHAR(255) NOT NULL COMMENT '文件存储路径',
    file_size BIGINT NOT NULL COMMENT '文件大小（以字节为单位）',
    upload_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '文件上传时间',
    description TEXT NULL COMMENT '文件描述（可选）',
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件管理表，记录项目或任务相关的文件';

-- 消息通知表
DROP TABLE IF EXISTS notifications;
CREATE TABLE notifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '通知ID（唯一标识）',
    sender_id BIGINT NULL COMMENT '通知发送者ID（关联账号表）',
    receiver_id BIGINT NOT NULL COMMENT '通知接收者ID（关联账号表）',
    message TEXT NOT NULL COMMENT '通知内容',
    type ENUM('system', 'admin', 'teacher', 'student') NOT NULL DEFAULT 'system' COMMENT '通知类型',
    status ENUM('unread', 'read', 'delete') NOT NULL DEFAULT 'unread' COMMENT '通知状态',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '通知发送时间',
    read_at TIMESTAMP NULL COMMENT '通知阅读时间',
    FOREIGN KEY (sender_id) REFERENCES accounts(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息通知表，记录系统内的消息通知';

-- 审核表
DROP TABLE IF EXISTS reviews;
CREATE TABLE reviews (
id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '审核记录ID（唯一标识）',
task_id BIGINT NOT NULL COMMENT '审核对象任务ID',
auditor_id BIGINT NOT NULL COMMENT '审核人ID（关联教师表）',
status ENUM('pending', 'pass', 'fail') DEFAULT 'pending' NOT NULL COMMENT '审核状态',
comments TEXT NULL COMMENT '审核意见（可选）',
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '审核发起时间',
reviewed_at TIMESTAMP NULL COMMENT '审核完成时间',
FOREIGN KEY (auditor_id) REFERENCES teachers(id) ON DELETE CASCADE,
FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审核表，记录任务、项目、文件等的审核信息';

-- 公告表
DROP TABLE IF EXISTS announcements;
CREATE TABLE announcements (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '公告ID（唯一标识）',
    title VARCHAR(255) NOT NULL COMMENT '公告标题',
    content TEXT NOT NULL COMMENT '公告内容',
    author_id BIGINT NOT NULL COMMENT '公告发布者ID（关联教师、管理员或系统用户）',
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '公告发布开始时间',
    end_date TIMESTAMP NULL COMMENT '公告有效结束时间（可为空，表示永久有效）',
    status ENUM('published', 'deleted') NOT NULL DEFAULT 'published' COMMENT '公告状态',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '公告创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '公告最后更新时间',
    FOREIGN KEY (author_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表，记录系统公告信息';

-- 项目标签表
DROP TABLE IF EXISTS project_tags;
CREATE TABLE project_tags (
    project_id BIGINT NOT NULL COMMENT '项目ID',
    tag VARCHAR(32) NOT NULL COMMENT '标签',
    PRIMARY KEY (project_id, tag),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表，记录系统公告信息';

-- 日志表
DROP TABLE IF EXISTS logs;
CREATE TABLE logs (
id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID（唯一标识）',
account_id BIGINT NOT NULL COMMENT '操作用户的ID（关联账户表）',
operation_type ENUM('login', 'operation', 'error', 'system') NOT NULL COMMENT '操作类型（登录、操作、错误、系统等）',
description TEXT NOT NULL COMMENT '日志描述，记录具体操作信息或错误信息',
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '日志创建时间',
FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志表，记录系统中操作、错误和事件等信息';