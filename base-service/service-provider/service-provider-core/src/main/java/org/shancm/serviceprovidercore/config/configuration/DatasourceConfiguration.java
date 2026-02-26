package org.shancm.serviceprovidercore.config.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.StringJoiner;

/**
 * @author by shancm
 * @description DatasourceConfiguration
 * @since 2026-02-19 18:05
 */
@Configuration
public class DatasourceConfiguration {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.username}")
    private String datasourceUsername = "postgres";
    @Value("${spring.datasource.password}")
    private String datasourcePassword = "mysql";
    private final static String DRIVE_CLASS_NAME = "org.postgresql.Driver";
    private final static String VALIDATION_QUERY = "SELECT 1";

    @Bean
    public DataSource dataSource() {
        //文章:https://developer.aliyun.com/article/932770
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        //如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
        dataSource.setDriverClassName(DRIVE_CLASS_NAME);

        // 连接池大小配置
        //初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
        dataSource.setInitialSize(5);
        // 最小空闲连接数
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);

        //连接存活检测 && 回收与超时设置
        dataSource.setValidationQuery(VALIDATION_QUERY);
        //申请连接时如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。如果检测失败，则连接将被从池中去除
        dataSource.setTestWhileIdle(true);
        //1)检查空闲连接的频率.Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。2) testWhileIdle的判断依据，默认为1分钟
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //连接的最小生存时间.连接保持空闲而不被驱逐的最小时间
        dataSource.setMinEvictableIdleTimeMillis(300000);
        //借用连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnBorrow(false);
        //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(false);

        // 性能优化与监控
        // 是否缓存preparedStatement（PSCache），对支持游标的数据库（如Oracle）性能提升较大，MySQL建议关闭，PostgreSQL可开启
        dataSource.setPoolPreparedStatements(true);
        //设置PSCache值
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        //连接出错后再尝试连接三次
        dataSource.setConnectionErrorRetryAttempts(3);
        //数据库服务宕机自动重连机制
        dataSource.setBreakAfterAcquireFailure(true);
        //连接出错后重试时间间隔
        dataSource.setTimeBetweenConnectErrorMillis(30000);
        //异步初始化策略
        dataSource.setAsyncInit(true);
        //是否自动回收超时连接
        dataSource.setRemoveAbandoned(true);
        //超时时间(以秒数为单位) 超过此值后，druid将强制回收该连接
        dataSource.setRemoveAbandonedTimeout(180);
        //事务超时时间 ***文档默认6000***
        dataSource.setTransactionQueryTimeout(30000);

        // ===== 慢SQL核心 =====
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("druid.stat.mergeSql=" + true);
        joiner.add("druid.stat.slowSqlMillis=" + 3000);
        joiner.add("druid.stat.logSlowSql=" + true);

        dataSource.setConnectionProperties(joiner.toString());

        try {
            dataSource.setFilters("stat,wall,log4j2");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to set Druid filters", e);
        }

        return dataSource;
    }

}
