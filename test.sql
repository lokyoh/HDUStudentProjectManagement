INSERT INTO accounts (username, password, role)
VALUES ('admin', 'Admin123456', 'admin'),
       ('21051431','Aa123456','student'),
       ('t123456', 'Aa123456', 'teacher'),
       ('25051101', 'Aa123456', 'student'),
       ('25051102', 'Aa123456', 'student'),
       ('25051103', 'Aa123456', 'student'),
       ('25051104', 'Aa123456', 'student'),
       ('25051105', 'Aa123456', 'student'),
       ('25051106', 'Aa123456', 'student'),
       ('25051107', 'Aa123456', 'student'),
       ('25051108', 'Aa123456', 'student'),
       ('25051109', 'Aa123456', 'student'),
       ('25051110', 'Aa123456', 'student'),
       ('25051111', 'Aa123456', 'student'),
       ('25051112', 'Aa123456', 'student'),
       ('25051113', 'Aa123456', 'student'),
       ('25051114', 'Aa123456', 'student'),
       ('25051115', 'Aa123456', 'student'),
       ('25051116', 'Aa123456', 'student'),
       ('25051117', 'Aa123456', 'student'),
       ('25051118', 'Aa123456', 'student'),
       ('25051119', 'Aa123456', 'student'),
       ('25051120', 'Aa123456', 'student'),
       ('t000001', 'Aa123456', 'teacher'),
       ('t000002', 'Aa123456', 'teacher'),
       ('t000003', 'Aa123456', 'teacher'),
       ('t000004', 'Aa123456', 'teacher'),
       ('t000005', 'Aa123456', 'teacher'),
       ('t000006', 'Aa123456', 'teacher');

INSERT INTO students (account_id, name, student_id, class_number, email, phone)
VALUES (2, 'lokyoh', '21051431', '21052314', 'lokyoh@hotmail.com', '12112312312'),
       (4, 's1', '25051101', '25051101', 'lokyoh@hotmail.com', '12112312312'),
       (5, 's2', '25051102', '25051102', 'lokyoh@hotmail.com', '12112312312'),
       (6, 's3', '25051103', '25051103', 'lokyoh@hotmail.com', '12112312312'),
       (7, 's4', '25051104', '25051104', 'lokyoh@hotmail.com', '12112312312'),
       (8, 's5', '25051105', '25051105', 'lokyoh@hotmail.com', '12112312312'),
       (9, 's6', '25051106', '25051106', 'lokyoh@hotmail.com', '12112312312'),
       (10, 's7', '25051107', '25051107', 'lokyoh@hotmail.com', '12112312312'),
       (11, 's8', '25051108', '25051108', 'lokyoh@hotmail.com', '12112312312'),
       (12, 's9', '25051109', '25051109', 'lokyoh@hotmail.com', '12112312312'),
       (13, 's10', '25051110', '25051110', 'lokyoh@hotmail.com', '12112312312'),
       (14, 's11', '25051111', '25051111', 'lokyoh@hotmail.com', '12112312312'),
       (15, 's12', '25051112', '25051112', 'lokyoh@hotmail.com', '12112312312'),
       (16, 's13', '25051113', '25051113', 'lokyoh@hotmail.com', '12112312312'),
       (17, 's14', '25051114', '25051114', 'lokyoh@hotmail.com', '12112312312'),
       (18, 's15', '25051115', '25051115', 'lokyoh@hotmail.com', '12112312312'),
       (19, 's16', '25051116', '25051116', 'lokyoh@hotmail.com', '12112312312'),
       (20, 's17', '25051117', '25051117', 'lokyoh@hotmail.com', '12112312312'),
       (21, 's18', '25051118', '25051118', 'lokyoh@hotmail.com', '12112312312'),
       (22, 's19', '25051119', '25051119', 'lokyoh@hotmail.com', '12112312312'),
       (23, 's20', '25051120', '25051102', 'lokyoh@hotmail.com', '12112312312');

INSERT INTO teachers (account_id, name, teacher_id, email, phone)
VALUES (3, '张三', 't123456', 'zhangsan@lokyoh.com', '12345678912'),
       (24, 't1', 't000001', 't1@lokyoh.com', '12345678912'),
       (25, 't2', 't000002', 't2@lokyoh.com', '12345678912'),
       (26, 't3', 't000003', 't3@lokyoh.com', '12345678912'),
       (27, 't4', 't000004', 't4@lokyoh.com', '12345678912'),
       (28, 't5', 't000005', 't5@lokyoh.com', '12345678912'),
       (29, 't6', 't000006', 't6@lokyoh.com', '12345678912');

INSERT INTO classes (name, teacher_id, semester, description)
VALUES ('毕业设计S123', 1, '2025-1', '毕业设计S123');

INSERT INTO student_classes (student_id, class_id)
VALUES (1,1);

INSERT INTO projects (name, description, creator_id, teacher_id, class_id, start_date)
VALUES ('p1', 'p1-d', 1, 1, 1, '2025-03-14 16:28:45');

INSERT INTO project_members (project_id, student_id, role)
VALUES (1, 1, 'leader');

INSERT INTO tasks (project_id, name, description, assigned_to, due_date, status)
VALUES (1, 't', 't-d', 1, NULL, 'normal');

INSERT INTO task_members (task_id, student_id, role)
VALUES (1, 1, 'leader');

INSERT INTO announcements (title, content, author_id)
VALUES ('公告名称', '公告内容', 1);

INSERT INTO reviews (task_id, auditor_id)
VALUES (1, 1);