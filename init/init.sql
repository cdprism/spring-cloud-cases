-- 创建商品表
CREATE TABLE t_product (
    id BIGINT PRIMARY KEY,           -- 雪花ID主键
    product_name VARCHAR(200) NOT NULL, -- 商品名称
    price DECIMAL(10,2) NOT NULL,      -- 商品价格，保留2位小数
    stock INT NOT NULL DEFAULT 0,      -- 商品库存，默认0
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 更新时间
);

-- 添加注释
COMMENT ON TABLE t_product IS '商品表';
COMMENT ON COLUMN t_product.id IS '雪花ID主键';
COMMENT ON COLUMN t_product.product_name IS '商品名称';
COMMENT ON COLUMN t_product.price IS '商品价格';
COMMENT ON COLUMN t_product.stock IS '商品库存';
COMMENT ON COLUMN t_product.created_at IS '创建时间';
COMMENT ON COLUMN t_product.updated_at IS '更新时间';

-- 创建订单表
CREATE TABLE t_order (
    id BIGINT PRIMARY KEY,              -- 雪花ID主键
    product_id BIGINT NOT NULL,          -- 商品ID
    product_price DECIMAL(10,2) NOT NULL, -- 下单时的商品价格
    total_amount DECIMAL(10,2) NOT NULL,  -- 订单总价
    quantity INT NOT NULL,                -- 购买数量
    status VARCHAR(20) DEFAULT 'PENDING', -- 订单状态
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 更新时间
    CONSTRAINT fk_t_order_product FOREIGN KEY (product_id) REFERENCES t_product(id)
);

-- 添加注释
COMMENT ON TABLE t_order IS '订单表';
COMMENT ON COLUMN t_order.id IS '雪花ID主键';
COMMENT ON COLUMN t_order.product_id IS '商品ID';
COMMENT ON COLUMN t_order.product_price IS '下单时的商品价格';
COMMENT ON COLUMN t_order.total_amount IS '订单总价';
COMMENT ON COLUMN t_order.quantity IS '购买数量';
COMMENT ON COLUMN t_order.status IS '订单状态：PENDING-待支付，PAID-已支付，CANCELLED-已取消';
COMMENT ON COLUMN t_order.created_at IS '创建时间';
COMMENT ON COLUMN t_order.updated_at IS '更新时间';

-- 创建索引提高查询性能
CREATE INDEX idx_t_product_product_name ON t_product(product_name);
CREATE INDEX idx_t_order_product_id ON t_order(product_id);
CREATE INDEX idx_t_order_created_at ON t_order(created_at);

-- 创建更新时间触发器函数
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

-- 为两个表创建更新触发器
CREATE TRIGGER update_t_product_updated_at
    BEFORE UPDATE ON t_product
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_t_order_updated_at
    BEFORE UPDATE ON t_order
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- 添加检查约束确保价格和数量为正数
ALTER TABLE t_product ADD CONSTRAINT check_product_price_positive CHECK (price > 0);
ALTER TABLE t_product ADD CONSTRAINT check_product_stock_non_negative CHECK (stock >= 0);
ALTER TABLE t_order ADD CONSTRAINT check_order_price_positive CHECK (product_price > 0);
ALTER TABLE t_order ADD CONSTRAINT check_order_quantity_positive CHECK (quantity > 0);
ALTER TABLE t_order ADD CONSTRAINT check_order_total_amount_positive CHECK (total_amount > 0);


-- 记录
-- 插入10条水果商品记录
INSERT INTO t_product (id, product_name, price, stock) VALUES
    (1700000000000000001, '红富士苹果', 8.50, 100),
    (1700000000000000002, '新疆库尔勒香梨', 12.00, 80),
    (1700000000000000003, '海南香蕉', 5.50, 150),
    (1700000000000000004, '江西赣南脐橙', 9.80, 120),
    (1700000000000000005, '泰国金枕头榴莲', 39.90, 30),
    (1700000000000000006, '云南夏黑葡萄', 15.50, 60),
    (1700000000000000007, '广东徐闻菠萝', 6.80, 90),
    (1700000000000000008, '四川爱媛果冻橙', 11.50, 70),
    (1700000000000000009, '大连樱桃', 45.00, 25),
    (1700000000000000010, '广西金桔', 9.90, 110);

-- 验证商品插入结果
SELECT '商品表插入结果：' as info, * FROM t_product;

-- 插入5条订单记录
-- 订单1：购买苹果2斤，总价17.00
INSERT INTO t_order (id, product_id, product_price, total_amount, quantity, status)
VALUES (1800000000000000001, 1700000000000000001, 8.50, 17.00, 2, 'PAID');

-- 订单2：购买香梨3斤和香蕉2斤（两条记录）
INSERT INTO t_order (id, product_id, product_price, total_amount, quantity, status)
VALUES
    (1800000000000000002, 1700000000000000002, 12.00, 36.00, 3, 'PAID'),
    (1800000000000000003, 1700000000000000003, 5.50, 11.00, 2, 'PAID');

-- 订单3：购买榴莲1个，总价39.90
INSERT INTO t_order (id, product_id, product_price, total_amount, quantity, status)
VALUES (1800000000000000004, 1700000000000000005, 39.90, 39.90, 1, 'PENDING');

-- 订单4：购买葡萄2斤和樱桃1斤
INSERT INTO t_order (id, product_id, product_price, total_amount, quantity, status)
VALUES
    (1800000000000000005, 1700000000000000006, 15.50, 31.00, 2, 'PAID'),
    (1800000000000000006, 1700000000000000009, 45.00, 45.00, 1, 'PAID');

-- 订单5：购买脐橙4斤和菠萝2斤
INSERT INTO t_order (id, product_id, product_price, total_amount, quantity, status)
VALUES
    (1800000000000000007, 1700000000000000004, 9.80, 39.20, 4, 'CANCELLED'),
    (1800000000000000008, 1700000000000000007, 6.80, 13.60, 2, 'CANCELLED');

-- 验证订单插入结果
SELECT '订单表插入结果：' as info, * FROM t_order;

-- 查询每个商品的销售情况（包含商品信息和订单统计）
SELECT
    p.id as 商品ID,
    p.product_name as 商品名称,
    p.price as 当前价格,
    p.stock as 当前库存,
    COUNT(o.id) as 订单数量,
    COALESCE(SUM(o.quantity), 0) as 销售总量,
    COALESCE(SUM(o.total_amount), 0) as 销售总额
FROM t_product p
         LEFT JOIN t_order o ON p.id = o.product_id
GROUP BY p.id, p.product_name, p.price, p.stock
ORDER BY p.id;

-- 查询订单明细（包含商品名称）
SELECT
    o.id as 订单ID,
    p.product_name as 商品名称,
    o.product_price as 单价,
    o.quantity as 数量,
    o.total_amount as 总价,
    o.status as 订单状态,
    o.created_at as 下单时间
FROM t_order o
         JOIN t_product p ON o.product_id = p.id
ORDER BY o.created_at DESC;