INSERT INTO accounts (username, password, role)
VALUES ('admin', 'Admin123456', '管理员'),
       ('21051431','Aa123456','学生'),
       ('t123456', 'Aa123456', '教师');

INSERT INTO students (account_id, name, student_id, class_number, email, phone)
VALUES (2, 'lokyoh', '21051431', '21052314', 'lokyoh@hotmail.com', '12112312312');

INSERT INTO teachers (account_id, name, email, phone)
VALUES (3, '张三', 'zhangsan@lokyoh.com', '12345678912');

INSERT INTO classes (name, teacher_id, semester)
VALUES ('毕业设计S123', 1, '2025-1');

INSERT INTO student_classes (student_id, class_id)
VALUES (1,1);

INSERT INTO projects (name, description, creator_id, class_id, start_date)
VALUES ('p1', 'p1-d', 1, 1, '2025-03-14 16:28:45');

INSERT INTO project_members (project_id, student_id, role)
VALUES (1, 1, '组长');

INSERT INTO tasks (project_id, name, description, assigned_to, due_date, status)
VALUES (1, 't', 't-d', 1, NULL, '进行中');

INSERT INTO task_members (task_id, student_id, role)
VALUES (1, 1, '负责人');
