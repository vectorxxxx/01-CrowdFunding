## 1. 发起项目

```mysql
create table t_type
(
    id     int(11) not null auto_increment,
    name   varchar(255) comment '分类名称',
    remark varchar(255) comment '分类介绍',
    primary key (id)
) COMMENT ='分类表';

create table t_project_type
(
    id        int not null auto_increment,
    projectid int(11),
    typeid    int(11),
    primary key (id)
) COMMENT ='项目分类中间表';

create table t_tag
(
    id   int(11) not null auto_increment,
    pid  int(11),
    name varchar(255),
    primary key (id)
) COMMENT ='标签表';

create table t_project_tag
(
    id        int(11) not null auto_increment,
    projectid int(11),
    tagid     int(11),
    primary key (id)
) COMMENT ='项目标签中间表';

create table t_project
(
    id                  int(11) not null auto_increment,
    project_name        varchar(255) comment '项目名称',
    project_description varchar(255) comment '项目描述',
    money               bigint(11) comment '筹集金额',
    day                 int(11) comment '筹集天数',
    status              int(4) comment '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败',
    deploydate          varchar(10) comment '项目发起时间',
    supportmoney        bigint(11) comment '已筹集到的金额',
    supporter           int(11) comment '支持人数',
    completion          int(3) comment '百分比完成度',
    memberid            int(11) comment '发起人的会员 id',
    createdate          varchar(19) comment '项目创建时间',
    follower            int(11) comment '关注人数',
    header_picture_path varchar(255) comment '头图路径',
    primary key (id)
) COMMENT ='项目表';

create table t_project_item_pic
(
    id            int(11) not null auto_increment,
    projectid     int(11),
    item_pic_path varchar(255),
    primary key (id)
) COMMENT ='项目表项目详情图片表';

create table t_member_launch_info
(
    id                 int(11) not null auto_increment,
    memberid           int(11) comment '会员 id',
    description_simple varchar(255) comment '简单介绍',
    description_detail varchar(255) comment '详细介绍',
    phone_num          varchar(255) comment '联系电话',
    service_num        varchar(255) comment '客服电话',
    primary key (id)
) COMMENT ='项目发起人信息表';

create table t_return
(
    id               int(11) not null auto_increment,
    projectid        int(11),
    type             int(4) comment '0 - 实物回报， 1 虚拟物品回报',
    supportmoney     int(11) comment '支持金额',
    content          varchar(255) comment '回报内容',
    count            int(11) comment '回报产品限额，“0”为不限回报数量',
    signalpurchase   int(11) comment '是否设置单笔限购',
    purchase         int(11) comment '具体限购数量',
    freight          int(11) comment '运费，“0”为包邮',
    invoice          int(4) comment '0 - 不开发票， 1 - 开发票',
    returndate       int(11) comment '项目结束后多少天向支持者发送回报',
    describ_pic_path varchar(255) comment '说明图片路径',
    primary key (id)
) COMMENT ='回报信息表';

create table t_member_confirm_info
(
    id       int(11) not null auto_increment,
    memberid int(11) comment '会员 id',
    paynum   varchar(200) comment '易付宝企业账号',
    cardnum  varchar(200) comment '法人身份证号',
    primary key (id)
) COMMENT ='发起人确认信息表';
```

修改

```mysql
alter table t_project
    modify project_description longtext null comment '项目描述';
alter table t_member_launch_info
    modify description_detail longtext null comment '详细介绍';
alter table t_return
    modify content longtext null comment '回报内容';
alter table t_project
    modify money int null comment '筹集金额';
alter table t_project
    modify deploydate varchar(19) null comment '项目发起时间';
alter table t_member_launch_info
    add project_id int null;
alter table t_member_confirm_info
    add project_id int null;
alter table t_order_project
    modify return_content varchar(1000) null comment '回报内容';
alter table t_order
    modify order_num varchar(100) null comment '订单号';
alter table t_order
    modify pay_order_num varchar(100) null comment '支付宝流水号';
```

## 2、支付

沙箱网关地址为

```
https://openapi-sandbox.dl.alipaydev.com/gateway.do
```

netapp

```
#将本文件放置于natapp同级目录 程序将读取 [default] 段
#在命令行参数模式如 natapp -authtoken=xxx 等相同参数将会覆盖掉此配置
#命令行参数 -config= 可以指定任意config.ini文件
[default]
authtoken=xxxxxxxxxxxxxxxx      #对应一条隧道的authtoken
clienttoken=                    #对应客户端的clienttoken,将会忽略authtoken,若无请留空,
log=none                        #log 日志文件,可指定本地文件, none=不做记录,stdout=直接屏幕输出 ,默认为none
loglevel=INFO                   #日志等级 DEBUG, INFO, WARNING, ERROR 默认为 DEBUG
http_proxy=                     #代理设置 如 http://10.123.10.10:3128 非代理上网用户请务必留空
```

## 3、订单

```mysql
create table t_order
(
    id            int auto_increment comment '订单主键'
        primary key,
    order_num     int          null comment '订单号',
    pay_order_num int          null comment '支付宝流水号',
    order_amount  int          null comment '订单金额',
    invoice       int          null comment '是否需要发票（0: 不开，1: 开）',
    Invoice_title varchar(50)  null comment '发票抬头',
    order_remark  varchar(100) null comment '订单备注',
    address_id    int          null comment '收货地址ID'
)
    comment '订单信息表';

create table t_address
(
    id             int auto_increment comment '地址信息主键'
        primary key,
    consignee_name varchar(50)  null comment '收货人',
    phone_num      varchar(50)  null comment '手机号码',
    address        varchar(200) null comment '详细地址',
    member_id      int          null comment '会员ID'
)
    comment '收货地址表';

create table t_order_project
(
    id                 int auto_increment comment '项目信息主键'
        primary key,
    project_name       varchar(100) null comment '项目名称',
    launch_name        varchar(100) null comment '发起人',
    return_content     varchar(200) null comment '回报内容',
    return_count       int          null comment '回报数量',
    support_unit_price int          null comment '支持单价',
    delivery_charge    int          null comment '配送费用',
    order_id           int          null comment '订单ID'
)
    comment '订单项目信息表';
```
