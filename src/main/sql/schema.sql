--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建秒杀表
CREATE  TABLE seckill(
 seckill_id bigint NOT   NULL AUTO_INCREMENT COMMENT '商品库存id',
 name varchar(120) NOT NULL COMMENT '商品名称',
 number int NOT NULL COMMENT '库存数量',
 create_time TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
 start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
 end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
 PRIMARY  KEY  (seckill_id),
 key idx_create_time(create_time),
 key idx_start_time(start_time),
 key idx_end_time(end_time)

)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=utf8 COMMENT='秒杀库存表'


--初始化数据
insert into seckill(name,number,start_time,end_time)
VALUES
 ('1000元秒杀iPhone6',1000,'2016-01-01 00:00:00','2016-01-01 00:00:00'),
 ('200元秒杀ipad2',200,'2016-01-01 00:00:00','2016-01-01 00:00:00'),
 ('300元秒杀小米4',300,'2016-01-01 00:00:00','2016-01-01 00:00:00'),
 ('400元秒杀红米note',400,'2016-01-01 00:00:00','2016-01-01 00:00:00')

--秒杀成功明细表
--用户登录认证相关的信息
CREATE  TABLE success_killed(
 seckill_id bigint NOT   NULL  COMMENT '秒杀商品id',
 user_phone bigint NOT   NULL  COMMENT '用户手机号',
 state tinyint NOT   NULL  DEFAULT -1 COMMENT '状态表示：-1：无效 0：成功 1：已付款',
 create_time TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
 PRIMARY  KEY  (seckill_id,user_phone),/*联合主键*/
 key idx_create_time(create_time)

)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=utf8 COMMENT='秒杀成功明细表'

--连接数据库控制台
mysql -uroot -p


--为什么手写DDL
--记录每次上线的DDL修改
--上线v1.1
ALTER TABLE seckill
DROP INDEX idx_create_time,
ADD INDEX idx_c_s(start_time,end_time);

--上线v1.2
--修改DDL，记录, 以便维护


