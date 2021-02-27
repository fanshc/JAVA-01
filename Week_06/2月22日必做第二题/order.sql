-- auto-generated definition
create table `order`
(
    id               varchar(32) not null,
    createDate       bigint      null,
    address          varchar(32) null,
    totalPrace       int         null,
    userid           varchar(32) null,
    orderState       varchar(3)  null,
    receiveAddressId varchar(32) null comment '收货地址Id',
    constraint order_id_uindex
        unique (id)
)
    comment '订单表';

alter table `order`
    add primary key (id);

