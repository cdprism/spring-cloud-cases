# spring-cloud-cases

⭐`对应版本`
- `jdk 21`
- `maven 4.0.0-rc-5`
- `Spring Boot 4.0.3`
- `Spring Cloud 2025.1.1`
- `Spring Cloud Alibaba 2025.1.0.0`
- `Nacos version : 3.1.1`
- `Sentinel version ： 1.8.9`
- `Seata 2.5.0`
- `RocketMQ 5.3.1`
- `SchedulerX 1.13.3`  
[B站教程](https://www.bilibili.com/video/BV1UJc2ezEFU)  
[Github文档](https://github.com/mofan212/spring-cloud-demo/blob/master/README.md)  

- 所有自建工程版本都别包含SNAPSHOT  

## druid 配置
- /druid 可访问druid后台  

- 开启/关闭 Druid 的监控后台页面 在dev和prod设置
```properties
druid.stat-view.enabled=true
```

## nacos 配置  

1. 安装MySQL 并执行conf下mysql-schema.sql脚本
2. 修改conf下的application.properties文件，配置数据库连接
```properties
allowPublicKeyRetrieval=true - 允许客户端从服务器获取公钥
spring.sql.init.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
db.user=root
db.password=mysql
```
生产环境建议配置如下
```sql
-- 登录MySQL
mysql -u root -p

-- 查看当前用户认证插件
SELECT user, host, plugin FROM mysql.user WHERE user = '你的用户名';

-- 修改认证插件为 mysql_native_password
ALTER USER '你的用户名'@'%' IDENTIFIED WITH mysql_native_password BY '你的密码';
ALTER USER '你的用户名'@'localhost' IDENTIFIED WITH mysql_native_password BY '你的密码';

-- 刷新权限
FLUSH PRIVILEGES;
```
3. `@EnableDiscoveryClient`
4. `spring.cloud.nacos.server-addr=${nacos.server-addr} ***nacos.server-addr***` 在不同环境定义

## nacos config  

1. 配置文件增加 `spring.config.import=nacos:XXX.properties`
2. 添加property类 添加注解
```text
@Data
@Component
@ConfigurationProperties(prefix = "provider")
```
3. 命名空间属性 `spring.cloud.nacos.config.namespace=${spring.profiles.active:public}`
4. 配置分组属性 `spring.config.import=nacos:provider.properties?**group**=service-provider`

## openfeign
1. 在调用方使用注解 `@EnableFeignClients`
2. 在提供方定义接口 `@FeignClient(name = "service-provider", path="/provider")`
3. 在调用方调用接口
- ### feign 日志 需在调用方配置 且在调用方生效
1. 配置文件增加 `logging.level.org.shancm.serviceproviderinterface.feign=debug`
2. `FeignConfiguration` 类配置
- ### feign 超时控制 
  `connectTimeout` 连接超时 默认10s `readTimeout` 读取超时 默认60s
```yaml
spring:
  cloud:
    openfeign:
      client:
        config:
          # 默认配置
          default:
            logger-level: full
            connect-timeout: 1000
            read-timeout: 2000
          # 具体 feign 客户端的超时配置
          service-product:
            logger-level: full
            # 连接超时，3000 毫秒
            connect-timeout: 3000
            # 读取超时，5000 毫秒
            read-timeout: 5000
```
- ### feign 重试机制
在`FeignConfiguration.java`中注册`Retryer` bean

- ### feign 拦截器
1. 在服务调用方定义拦截器类 `TokenInterceptor.java` 实现`equestInterceptor`

## sentinel `资源`&`规则` 
1. 启动sentinel-dashboard `java -jar sentinel-dashboard-1.8.9.jar --server.port=8081`
2. 配置文件增加 `spring.cloud.sentinel.transport.dashboard=localhost:8081` 关闭懒加载 `spring.cloud.sentinel.eager=true`

资源：
- `框架: feign接口`
- `@SentinelResource注解`、`@SentinelResourceAspect切面`
  
规则
- `流量控制`&`流量路由`
- `熔断降级` 在调用方配置 用于保护自身
- `系统自适应过载保护`
- `热点流量防护`

### **sentinel 异常处理**
触发sentinel保护机制后抛出`BlockException`(包含流控`FlowException`&热点参数`ParamException`)异常
1. 前端或web请求 `BlockExceptionHandler` 在被访问方定义MyBlockExceptionHandler
2. 被`@SentinelResource`
3. feign请求的资源 可以用callback兜底返回维护
- 在接口目录 下创建`callback.XXXFeignClientCallBack` 实现接口 添加@component
- 在接口上添加`fallback = XXXFeignClientCallBack.class`

### sentinel 降级熔断策略
- 慢调用比例
- 异常比例
- 异常数

### sentinel 系统自适应保护
- 系统规则：load1、cpu使用率、平均RT、并发线程数

## gateway 网关
`统一入口` `请求路由` `权限校验` `流量监控` `熔断降级` `限流` `日志` `过滤器`
1. 路由规则-配置
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: bing-route
          uri: https://cn.bing.com
          predicates:
            - Path=/**
          order: 10
          # id 全局唯一
        - id: order-route
          # 指定服务名称
          uri: lb://service-order
          # 指定断言规则，即路由匹配规则
          predicates:
            - Path=/api/order/**
          order: 1
        - id: product-route
          uri: lb://service-product
          predicates:
            - Path=/api/product/**
          order: 2
```
2. 路由规则-代码  
`GatewayRoutesConfiguration`
```java
@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order", r -> r
                        .path("/order/**")
                        .uri("lb://service-consumer"))

                .build();
    }
```
在 Spring Cloud Gateway 的实现中，断言的实现都是 `RoutePredicateFactory` 接口的实现。

因此除了直接查看官方文档外确定有哪些断言形式外，还可以通过查看 `RoutePredicateFactory` 的实现：

- HeaderRoutePredicateFactory
- PathRoutePredicateFactory
- ReadBodyRoutePredicateFactory
- BeforeRoutePredicateFactory
- ...

| 名称                   | 参数（个数/类型）           | 作用                                         |
|----------------------|---------------------|--------------------------------------------|
| After                | 1/datetime          | 在指定时间之后                                    |
| Before               | 1/datetime          | 在指定时间之前                                    |
| Between              | 2/datetime          | 在指定时间区间内                                   |
| Cookie               | 2/string,regexp     | 包含 cookie 名且必须匹配指定值                        |
| Header               | 2/string,regexp     | 包含请求头且必须匹配指定值                              |
| Host                 | N/string            | 请求 host 必须是指定枚举值                           |
| Method               | N/string            | 请求方式必须是指定枚举值                               |
| Path                 | 2/List<String>,bool | 请求路径满足规则，是否匹配最后的 /                         |
| Query                | 2/string,regexp     | 包含指定请求参数                                   |
| RemoteAddr           | 1/List<String>      | 请求来源于指定网络域（CIDR写法）                         |
| Weight               | 2/string,int        | 按指定权重负载均衡                                  |
| XForwardedRemoteAddr | 1/List<String>      | 从 X-Forwarded-For 请求头中解析请求来源，并判断是否来源于指定网络域 |


### 自定义断言 配置&Java工厂
自定义一个名为 `Vip` 的断言规则，要求存在名为 `user` 的请求参数，并且值为 `Shannon` 时才将请求跳转到 https://cn.bing.com：
```yml
spring:
  cloud:
    gateway:
      routes:
        - id: bing-route
          uri: https://cn.bing.com
          predicates:
            - name: Path
              args:
                patterns: /search
            - name: Query
              args:
                param: q
                regexp: hi
            - Vip=user,Shannon
```
```yml
        - id: Vip
          uri: https://cn.bing.com
          predicates:
            - Path=/search
            - Query=q,hi
            - Vip=user,Shannon
```
- `Vip` 断言规则在 `VipRoutePredicateFactory` 中实现 参考`QueryRoutePredicateFactory`

### 全局过滤器&自定义过滤器

- 全局过滤器 实现`GlobalFilter`
- 自定义过滤器 实现`GatewayFilter`

### 全局跨域











