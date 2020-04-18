# ve(we eat)

create table ve_product (
  product_id 				varchar(32) not null,
  product_name 				varchar(64) not null comment '商品名称',
  # 金额专用 8位+2位小数
  product_price 			decimal(8,2) not null comment '单价',
  product_stock 			int not null comment '库存', 
  product_description 		varchar(64) comment '描述',
  product_icon 				varchar(512) comment '小图',
  category_type 			int not null comment '类目编号',
  # mysql7前不可设置两个current_timestamp
  create_time 				timestamp not null default current_timestamp comment '创建时间',
  update_time 				timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
  primary key (product_id)
) comment '商品表';

create table ve_category (
	category_id				int not null auto_increment,
    category_name			varchar(64) not null comment '类目名字',
    # 类目通常需要制定，因此用自增的id不好，需要专门制定一个字段
    category_type			int not null comment '类目编号',
    create_time 			timestamp not null default current_timestamp comment '创建时间',
	update_time 			timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (category_id),
    # 经常根据类型编号查询，因此添加索引。且由于编号唯一，可以添加唯一索引。索引能提高查询性能
    unique key uqe_category_type (category_type)
) comment '类目表';

create table ve_order_info(
	order_info_id			varchar(32) not null,
    buyer_name 				varchar(32) not null comment '买家名字',
    buyer_phone				varchar(32) not null comment '买家电话',
    buyer_address			varchar(128) not null comment '买家地址',
    # 微信方提供的OAuth认证专有名词
    buyer_openid			varchar(64) not null comment '买家微信openid',
    order_amount			decimal(8,2) not null comment '订单总金额',
    # 状态少，因此用tinyint
    order_status			tinyint(3) not null default 0 comment '订单状态，默认0新下单',
    pay_sataus				tinyint(3) not null default 0 comment '支付状态，默认0未支付',
    create_time 			timestamp not null default current_timestamp comment '创建时间',
	update_time 			timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (order_info_id) ,
    # 经常根据用户openid查询订单，因此创建索引
    key idx_buyer_openid (buyer_openid)
) comment '订单概要(主表)';

create table ve_order_detail(
	order_detail_id			varchar(32) not null,
    order_info_id			varchar(32) not null comment '订单概要id',
    product_id				varchar(32) not null comment '商品id',
    # 允许的冗余字段
    product_name			varchar(64) not null comment '商品名称',
    product_price			decimal(8,2) not null comment '商品单价',
    product_icon			varchar(512) comment '商品小图',
    product_quantity		int not null comment '商品数量',
    create_time 			timestamp not null default current_timestamp comment '创建时间',
	update_time 			timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (order_detail_id),
    # 索引：根据订单id查询
    key idx_order_info_id	(order_info_id)
) comment '订单详情' ;