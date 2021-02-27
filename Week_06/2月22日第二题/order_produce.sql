-- auto-generated definition
create table order_produce
(
    id            varchar(32) not null,
    orderId       varchar(32) null comment '订单id',
    produceId     varchar(32) null comment '商品id',
    preferenceId  varchar(32) null comment '优惠Id',
    originalPrice int         null comment '原价',
    currentPrice  int         null comment '现价',
    totalPrice    int         null comment '总价格',
    produceCount  int         null comment '购买的商品数量',
    constraint order_produce_id_uindex
        unique (id)
)
    comment '订单商品表';

alter table order_produce
    add primary key (id);

