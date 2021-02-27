-- auto-generated definition
create table receiveAddress
(
    id            varchar(32)  not null comment '收货地址ID',
    userId        varchar(32)  null comment '收货地址所属用户Id',
    userName      varchar(200) null comment '收货人姓名',
    telephoneCode varchar(12)  null comment '收货人电话',
    addressId     varchar(32)  null comment '收货人所在地区ID',
    detailAddress varchar(500) null comment '收货人详细地址',
    constraint receiveAddress_id_uindex
        unique (id)
)
    comment '收货地址表';

alter table receiveAddress
    add primary key (id);

