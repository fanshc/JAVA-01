-- auto-generated definition
create table producestock
(
    id           varchar(32) not null comment '库存Id',
    procuceId    varchar(32) null comment '商品Id',
    storeHorseId varchar(32) null comment '仓库Id',
    produceCount int         null comment '库存数量',
    adminUserId  varchar(32) null comment '管理员Id',
    constraint producestock_id_uindex
        unique (id)
)
    comment '库存表';

alter table producestock
    add primary key (id);

