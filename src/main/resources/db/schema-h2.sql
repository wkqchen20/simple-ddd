DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id BIGINT(20) auto_increment NOT NULL COMMENT '主键ID',
    user_id bigint not null comment 'bussiness pk',
    status tinyint not null default 1 comment '状态',
    head_image_url varchar(255) not null default '' comment 'url',
    email varchar(255) not null default '' comment 'email',
    nickname varchar(64) not null default '' comment 'name',
    phone varchar(64) not null default '' comment 'phone',
    others_info varchar(2048) not null default '{}' comment '',
    company_info varchar(2048) not null default '{}' comment '',
    created_time datetime not null,
    modified_time datetime not null,
    PRIMARY KEY (id)
);
