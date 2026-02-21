package org.shancm.serviceconsumer.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Date 2026-02-19 18:05
 * @Author by shancm
 * @Description TODO
 */
@Configuration
public class DatasourceConfiguration {

    @Value("${datasource.url}")
    private String datasourceUrl;
    @Value("${datasource.username}")
    private String datasourceUsername = "postgres";
    @Value("${datasource.password}")
    private String datasourcePassword = "mysql";
    private final static String DRIVE_CLASS_NAME = "org.postgresql.Driver";
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setDriverClassName(driverClassName);//如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        dataSource.setDriverClassName(DRIVE_CLASS_NAME);
        dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效
        dataSource.setTestOnBorrow(false);//借用连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(false);//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        //连接空闲时检测，如果连接空闲时间大于timeBetweenEvictionRunsMillis指定的毫秒，执行validationQuery指定的SQL来检测连接是否有效
        dataSource.setTestWhileIdle(true);//如果检测失败，则连接将被从池中去除
        dataSource.setInitialSize(5); //初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
        dataSource.setMaxActive(20);
        dataSource.setTimeBetweenEvictionRunsMillis(60000); //检查空闲连接的频率.Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
        dataSource.setMinEvictableIdleTimeMillis(300000); //连接的最小生存时间.连接保持空闲而不被驱逐的最小时间
        dataSource.setPoolPreparedStatements(true); //开启PSCache
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20); //设置PSCache值
        dataSource.setConnectionErrorRetryAttempts(3); //连接出错后再尝试连接三次
        dataSource.setBreakAfterAcquireFailure(true); //数据库服务宕机自动重连机制
        dataSource.setTimeBetweenConnectErrorMillis(30000); //连接出错后重试时间间隔
        dataSource.setAsyncInit(true); //异步初始化策略
        dataSource.setRemoveAbandoned(true); //是否自动回收超时连接
        dataSource.setRemoveAbandonedTimeout(1800); //超时时间(以秒数为单位) 超过此值后，druid将强制回收该连接
        dataSource.setTransactionQueryTimeout(30000); //事务超时时间 ******文档默认6000
        dataSource.setFilters("stat,wall,log4j2");

        //        https://developer.aliyun.com/article/932770
//        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
//        web-stat-filter:
//        enabled: true
//        url-pattern: "/*"
//        exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
//        stat-view-servlet:
//        url-pattern: "/druid/*"
//        allow:
//        deny:
//        reset-enable: false
//        login-username: admin
//        login-password: admin


        return dataSource;
    }
}
