-- auto-generated definition
create table produce
(
    id          varchar(32)  not null comment '商品id',
    name        varchar(200) null comment '商品名称
',
    productdate bigint       null comment '生产日期',
    `desc`      varchar(400) null comment '商品描述',
    figureType  varchar(10)  null comment '计价类型',
    constraint produce_id_uindex
        unique (id)
)
    comment '商品标';

alter table produce
    add primary key (id);

