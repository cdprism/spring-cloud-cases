package org.shancm.serviceconsumer;

/*import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.StrategyConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.HashSet;*/



public class CodeGen {

    /*public static void main(String[] args) {
        // ========== 1. 配置 PostgreSQL 数据源 ==========
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=public&stringtype=unspecified");
        dataSource.setUsername("postgres");
        dataSource.setPassword("mysql");
        dataSource.setDriverClassName("org.postgresql.Driver");

        // ========== 2. 全局配置 ==========
        GlobalConfig globalConfig = new GlobalConfig();

        // 2.1 包路径配置
        globalConfig.getPackageConfig()
                .setBasePackage("org.shancm.serviceconsumer")
                .setSourceDir(System.getProperty("user.dir") + "/src/main/java");

        // 2.2 策略配置（PostgreSQL 关键配置）
        // ⭐指定生成的表名⭐
        HashSet<String> tables = new HashSet<>();
        tables.add("t_order");
        StrategyConfig strategy = globalConfig.getStrategyConfig();
        strategy.setGenerateSchema("public")                        // ⭐ 指定 PostgreSQL schema（重要）
                .setGenerateTables(tables)                         // 白名单，只生成指定表（可保留原始大小写）
                .setTablePrefix("t_");                             // 去除表前缀，例如 t_user_info -> UserInfo
//                .setLogicDeleteColumn("is_deleted")               // 逻辑删除字段
//                .setVersionColumn("version")                      // 乐观锁字段
                // ⭐ 针对 PostgreSQL 特殊字段类型可单独配置（如 jsonb, array）
//                .setColumnConfig("user_info", "tags", column -> column.jdbcType("OTHER").type("java.lang.String[]"))
//                .setColumnConfig("order_detail", "metadata", column -> column.jdbcType("OTHER").type("com.example.JsonbType"))
        // 注意：PostgreSQL 表名和字段名默认会转为小写，如需保留大小写，可在 generateTables 中使用双引号包裹（如 "\"User\""），但一般推荐统一小写。

        // 2.3 注释配置
        globalConfig.getJavadocConfig()
                .setAuthor("shancm")
//                .setSince("1.0.0")
                ;

        // 2.4 Entity 配置（通用最佳实践）
        globalConfig.enableEntity()                                   // 启用 Entity 生成
                .setWithLombok(true)                                  // 使用 Lombok
//                .setWithSwagger(true)                                 // 使用 Swagger 注解（@ApiModel等）
//                .setSwaggerVersion(EntityConfig.SwaggerVersion.FOX)   // ⭐Swagger 2.x 版本 早已弃用
                .setWithActiveRecord(false)                           // 不启用 Active Record 模式（通常用 Mapper）
                .setJdkVersion(21);                                    // JDK 版本
//                .setOverwriteEnable(false)                            // 不覆盖已存在的 Entity 文件
//                .setEntityWithBaseClassEnable(true);                  // ⭐ 启用 Base 类分离，避免自定义代码被覆盖

        // 2.5 Mapper 配置
        globalConfig.enableMapper()                                   // 启用 Mapper 生成
                .setMapperAnnotation(true)                            // 添加 @Mapper 注解（Spring Boot 可识别）
                .setOverwriteEnable(false);                           // 不覆盖已存在的 Mapper

        // 2.6 Service 配置
        globalConfig.enableService()                                  // 启用 Service 接口生成
                .setOverwriteEnable(false);
        globalConfig.enableServiceImpl()                              // 启用 ServiceImpl 实现类生成
                .setOverwriteEnable(false)
                .setCacheExample(false);                              // 不生成缓存示例代码，保持干净

        // 2.7 Controller 配置（RESTful）
        globalConfig.enableController()                               // 启用 Controller 生成
//                .setRestController(true)                              // ⭐ 使用 @RestController 而非 @Controller
//                .setRequestMappingPrefix("/api/v1")                   // 统一 API 前缀
                .setClassPrefix("")                                    // 不添加额外前缀
                .setClassSuffix("Controller")                         // 类后缀
                .setOverwriteEnable(false);

        // 可选：关闭不需要的生成产物（如 TableDef）
        // globalConfig.disableTableDef();

        // ========== 3. 执行生成 ==========
        Generator generator = new Generator(dataSource, globalConfig);
        generator.generate();

        System.out.println("✨PostgreSQL 代码生成完成！请刷新项目。✅");
    }*/
}
