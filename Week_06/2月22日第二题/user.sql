-- auto-generated definition
create table user
(
    id             varchar(32)  not null comment 'id,主键，使用mysql的自增的形式创建',
    userName       varchar(100) null comment '用户名',
    userid         varchar(20)  null comment '用户ID',
    aliasName      varchar(100) null comment '别名',
    createTime     bigint       not null comment '创建日期',
    cardId         varchar(18)  not null comment '身份证号',
    telephoneCode  varchar(12)  null comment '电话号',
    lastUpdateTime bigint       not null comment '当前用户最后更新时间',
    sex            varchar(1)   null comment '性别',
    birthdate      bigint       null comment '出生日期',
    constraint user_id_uindex
        unique (id)
)
    comment '用户的基本信息，包括用户名、id、昵称、电话等';

create index user_birthdate_index
    on user (birthdate);

create index user_createTime_index
    on user (createTime);

create index user_userid_index
    on user (userid);

alter table user
    add primary key (id);

