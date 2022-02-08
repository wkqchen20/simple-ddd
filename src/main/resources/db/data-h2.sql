DELETE FROM user;

INSERT INTO user(id, user_id, nickname, others_info, company_info, created_time, modified_time) VALUES
(1, 10001, '张三', '{"key1":"测试1", "key2":"测试2"}', '{"department":"开发部"}', current_time, current_time);