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
```

## 支付宝开放平台密钥工具

### 应用公钥

```
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjR94vuIP0OFrLanBbkzo5TIBFJ3TOyKq+G59+cJoQHmOTv9KCnRKX/jaDAorfacRlDRaBIFipkL47GwNSa+jSwhBs56/GCrPbdCJ4Fm5ZHtUMLWEGVAuy6RA0qG7wVavGGSzaP2LKG0nz8P13bG+fmkzGctZlzw141llXJj9fZB2X91Dh+Kw4qPCQC5PntDC+x2POBMcIJPP2HeSsgIzB4GNtYKg4IaWI6I5hDu6X53wr3aGhO5SU2wGQg6J8xl98jsgotV8ijLY49+jyW+4jEvLUltWAsvCf7KD5aipDA9mTroX5nv9fbTrr16xsOvLbAiev6ci023KTyqdi901EQIDAQAB
```

### 应用私钥

```
MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCNH3i+4g/Q4WstqcFuTOjlMgEUndM7Iqr4bn35wmhAeY5O/0oKdEpf+NoMCit9pxGUNFoEgWKmQvjsbA1Jr6NLCEGznr8YKs9t0IngWblke1QwtYQZUC7LpEDSobvBVq8YZLNo/YsobSfPw/Xdsb5+aTMZy1mXPDXjWWVcmP19kHZf3UOH4rDio8JALk+e0ML7HY84Exwgk8/Yd5KyAjMHgY21gqDghpYjojmEO7pfnfCvdoaE7lJTbAZCDonzGX3yOyCi1XyKMtjj36PJb7iMS8tSW1YCy8J/soPlqKkMD2ZOuhfme/19tOuvXrGw68tsCJ6/pyLTbcpPKp2L3TURAgMBAAECggEAQDrGtfolwKgYI9xTUWRny4kKvNtoPE33CsMfSwGUeWPZxVKj3EY5Y5zbgQdP7AySD6YUImDRCWMJ8HDtS1BJPekwHhRuXQ1/AzgayKWNn5ISK2kyCmNYKQ9FAZji34gYgT2z+mELaQblS8agrJotXVN8Eo1e5VpeHG+flpl7Jh5gAEYajvAr3UHac9hUnOVq8UfdytSPgJp9a6C6WXBELIfWGPTc1CMsiWKRNhGkg+02ME0IJx6eYCZAdi+YHRbWhGUrUlaiH/XWA+l/py5hQFRIP4DIWaja3UQWgSiKMh60JW4dvxnbdm4GEy6sspHWcf3aeyRalyPRZRhM7bu6kQKBgQDTgSZvEM9yTFJw5qSipvt5rd7whwaQ5gJWwJ9mQV6qcJdU6CHQ6XRRa76BCBNrM1sOW+qGeRn0/mL8PKoUmmzK7vnR7tTQN7/TSMan14oa82D/urZw4uqDTeQNF2Ec60CSmVjg/WVhua9WfhTVtvZ+wkx/2sbHz+GdDMyTeqVxvwKBgQCqz9TGnwMV0zRhQ64Py0oc/h+yRV6Sqx6n1SglWXGI9dZ1oOUslE2wKrtKsRHwKvnqEm3jhDxoDcKw/8vOh8AQmnKkbgVfVOMXySw/QrFTuttF6Z2eR8umEFf1Vgd1YPaYoAqSfjAGbVNNS3nal677itXLq1CQ4dlYrdcsT7FtLwKBgQCVSHYJNJNHOGmD+LdBepzK0JcnrBZNVZtXAhRAB2m64UdfDY04uF56TZMj3cBfDNt7MhNBoN5aVuck4+ikLC8XuT8pFuqsoHzLs3VL6E05IolXVsEIwpEF5odNWjAYSk/EaWobO59AL+B7Kvins8EdzSRTpeZiB+9Ve0A14/48XwKBgQCIDRIFQhJLB/booee6XMmjXiYQ8H/rgrDgLT/Uv/FWjblL5+OiJl3au66CR5dqHFECx7sPRPx4RL2WDahf0HCCyQIXRiqxTAO1pl6/5HcpoeOVRAZvU/A5TTtCBkvaimjgGFlTZX8LH3kCT+O24KpXYLX5i3KYc8uSfqSsfq1rsQKBgQCTEA4O16R+athoUiUTQvfzmfY4ShGXmAs4PcgkyWDW4w5ABa76YdZM9gdZ3a7X0M53E8ymZT0ghtB3ipGHTJlPMLLl8N9o8pF+09sjnQol/ywUv92GHnroeIJQhDyQs8tjFFYqIM8dXINA+CCjzOUyZvEYxCPdTisP9c4aQfbhEA==
```

### 支付宝公钥

```
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp9DhAzI4LGCJuwTQ+9TQn4FOvxUgyeLHheAjejI37DYrfwuEcBND1MlRHTekv0d80FDy962w2nmw4ZhQqCP+it2qSdRt0nszaXYNaZMyx1yDB+ZdQIuPWhwwOkWz6JroGvifZNuMkPbLZmzjd1J9ySd9bAFXmwEw5rinsenC493JXluuEZLCK0ZvdcSBJjfFmJaaZgkQk7+PygTeVbJzcDqfM7NM71t9wg2YOa9vxO8NEqUD5HnM2DSuCd+TRVk1FQ7sQqADY48jskhqrtOUEG7K5KO0nzR7I8Y9HsqstV/HapFcanEqKKJi/wLNb0yzUzsNVBssTBZRxfF6nkeTTQIDAQAB
```

### 沙箱网关地址为

```
https://openapi-sandbox.dl.alipaydev.com/gateway.do
```

### netapp

```
#将本文件放置于natapp同级目录 程序将读取 [default] 段
#在命令行参数模式如 natapp -authtoken=xxx 等相同参数将会覆盖掉此配置
#命令行参数 -config= 可以指定任意config.ini文件
[default]
authtoken=74e02aeafcf3bffb      #对应一条隧道的authtoken
clienttoken=                    #对应客户端的clienttoken,将会忽略authtoken,若无请留空,
log=none                        #log 日志文件,可指定本地文件, none=不做记录,stdout=直接屏幕输出 ,默认为none
loglevel=INFO                  #日志等级 DEBUG, INFO, WARNING, ERROR 默认为 DEBUG
http_proxy=                     #代理设置 如 http://10.123.10.10:3128 非代理上网用户请务必留空
```

http://2e6dsi.natappfree.cc

### AlipayConfig.java

```java
public class AlipayConfig
{

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000132663643";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCNH3i+4g/Q4WstqcFuTOjlMgEUndM7Iqr4bn35wmhAeY5O/0oKdEpf+NoMCit9pxGUNFoEgWKmQvjsbA1Jr6NLCEGznr8YKs9t0IngWblke1QwtYQZUC7LpEDSobvBVq8YZLNo/YsobSfPw/Xdsb5+aTMZy1mXPDXjWWVcmP19kHZf3UOH4rDio8JALk+e0ML7HY84Exwgk8/Yd5KyAjMHgY21gqDghpYjojmEO7pfnfCvdoaE7lJTbAZCDonzGX3yOyCi1XyKMtjj36PJb7iMS8tSW1YCy8J/soPlqKkMD2ZOuhfme/19tOuvXrGw68tsCJ6/pyLTbcpPKp2L3TURAgMBAAECggEAQDrGtfolwKgYI9xTUWRny4kKvNtoPE33CsMfSwGUeWPZxVKj3EY5Y5zbgQdP7AySD6YUImDRCWMJ8HDtS1BJPekwHhRuXQ1/AzgayKWNn5ISK2kyCmNYKQ9FAZji34gYgT2z+mELaQblS8agrJotXVN8Eo1e5VpeHG+flpl7Jh5gAEYajvAr3UHac9hUnOVq8UfdytSPgJp9a6C6WXBELIfWGPTc1CMsiWKRNhGkg+02ME0IJx6eYCZAdi+YHRbWhGUrUlaiH/XWA+l/py5hQFRIP4DIWaja3UQWgSiKMh60JW4dvxnbdm4GEy6sspHWcf3aeyRalyPRZRhM7bu6kQKBgQDTgSZvEM9yTFJw5qSipvt5rd7whwaQ5gJWwJ9mQV6qcJdU6CHQ6XRRa76BCBNrM1sOW+qGeRn0/mL8PKoUmmzK7vnR7tTQN7/TSMan14oa82D/urZw4uqDTeQNF2Ec60CSmVjg/WVhua9WfhTVtvZ+wkx/2sbHz+GdDMyTeqVxvwKBgQCqz9TGnwMV0zRhQ64Py0oc/h+yRV6Sqx6n1SglWXGI9dZ1oOUslE2wKrtKsRHwKvnqEm3jhDxoDcKw/8vOh8AQmnKkbgVfVOMXySw/QrFTuttF6Z2eR8umEFf1Vgd1YPaYoAqSfjAGbVNNS3nal677itXLq1CQ4dlYrdcsT7FtLwKBgQCVSHYJNJNHOGmD+LdBepzK0JcnrBZNVZtXAhRAB2m64UdfDY04uF56TZMj3cBfDNt7MhNBoN5aVuck4+ikLC8XuT8pFuqsoHzLs3VL6E05IolXVsEIwpEF5odNWjAYSk/EaWobO59AL+B7Kvins8EdzSRTpeZiB+9Ve0A14/48XwKBgQCIDRIFQhJLB/booee6XMmjXiYQ8H/rgrDgLT/Uv/FWjblL5+OiJl3au66CR5dqHFECx7sPRPx4RL2WDahf0HCCyQIXRiqxTAO1pl6/5HcpoeOVRAZvU/A5TTtCBkvaimjgGFlTZX8LH3kCT+O24KpXYLX5i3KYc8uSfqSsfq1rsQKBgQCTEA4O16R+athoUiUTQvfzmfY4ShGXmAs4PcgkyWDW4w5ABa76YdZM9gdZ3a7X0M53E8ymZT0ghtB3ipGHTJlPMLLl8N9o8pF+09sjnQol/ywUv92GHnroeIJQhDyQs8tjFFYqIM8dXINA+CCjzOUyZvEYxCPdTisP9c4aQfbhEA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://2e6dsi.natappfree.cc/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://2e6dsi.natappfree.cc/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

}
```
