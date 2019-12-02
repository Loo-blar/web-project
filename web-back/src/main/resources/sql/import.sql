INSERT INTO user (id, username, password, name, email,phone,enabled) VALUES (1, 'admin', '2lyBWgZlsF9OEORLymlLAL/Wjfie2cEGMM9FIRLqm+M=', '羽过天晴', 'ygtq@haibusiness.com','12345678',true);
INSERT INTO user (id, username, password, name, email,phone,enabled)  VALUES (2, 'test', '2lyBWgZlsF9OEORLymlLAL/Wjfie2cEGMM9FIRLqm+M=', '张三', 'zhangsan@haibusiness.com','87654321',true);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_PUBLIC');


INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);

INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (1, null, '菜单管理', '2019-01-21 20:21:41', 'line_style', 'menu', 1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (2, null, '用户管理', '2019-01-21 20:26:52', 'people', 'user', 1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (3, null, '学院概况', '2019-01-21 20:29:21', 'dvr', 'overview', 1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (4, null, '学院简介', '2019-01-21 20:32:25', 'assignment', 'introduction', 1, 3);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (5, null,  '师资队伍', '2019-01-21 20:51:13', 'assignment_ind', 'teacher',   1, 3);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (6, null, '专业设置', '2019-01-21 20:52:45', 'perm_data_setting', 'specialty',  1, 3);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (7, null,  '教研工作', '2019-01-21 20:54:33', 'school', 'teachingResearch',  1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (8, null,  '教学活动', '2019-01-21 21:05:25', 'local_library', 'teaching',   1, 7);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (9, null,  '科研情况', '2019-01-21 21:06:34', 'list_alt', 'research',  1, 7);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (10, null,  '团学工作', '2019-01-21 21:08:04', 'recent_actors', 'memberStudent',  1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (11, null,  '团学活动', '2019-01-21 21:10:58', 'outlined_flag', 'member', 1, 10);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (12, null, '优秀学子', '2019-01-21 21:15:06', 'how_to_reg', 'student',  1, 10);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (13, null,  '就业情况', '2019-01-21 21:18:12', 'card_travel', 'employment',  1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (14, null, '校内实训', '2019-01-21 21:19:36', 'account_balance', 'training',  1, 13);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (15, null,  '定岗实习', '2019-01-21 21:21:14', 'event', 'practice',   1, 13);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (16, null, '就业信息', '2019-01-21 21:23:56', 'library_books', 'employmentInfor',  1, 13);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (17, null, '校企合作', '2019-01-21 21:25:40', 'business', 'collaboration', 1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (18, null, '企业情况', '2019-01-21 21:28:18', 'ballot', 'enterprise',  1, 17);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (19, null,  '合作动态', '2019-01-21 21:30:38', 'cached', 'cooperationInfor', 1, 17);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (20, null,  '下载中心', '2019-01-31 08:45:37', 'save_alt', 'download', 1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (21, null, '图片新闻', '2019-01-31 09:45:36', 'photo_library', 'carousel',   1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (22, null,  '通知通告', '2019-01-21 21:34:04', 'cast', 'notice',   1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (23, null,  '学院动态', '2019-01-21 21:35:16', 'cast_connected', 'dynamic',  1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (24, null,  '媒体报道', '2019-01-21 21:36:01', 'account_box', 'media', 1, null);
INSERT INTO menu (id, title, update_time, icon, name,  update_user, parent) VALUES (26,   '本科教育', '2019-01-21 21:05:25', 'local_library', 'coll',   1, 7);



INSERT INTO menu_authority (menu_id, authority_id) VALUES (1, 1);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (2, 1);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (3, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (4, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (5, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (6, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (7, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (8, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (9, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (10, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (11, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (12, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (13, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (14, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (15, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (16, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (17, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (18, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (19, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (20, 3);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (21, 1);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (21, 2);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (22, 1);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (22, 2);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (23, 1);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (23, 2);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (24, 1);
INSERT INTO menu_authority (menu_id, authority_id) VALUES (24, 2);
