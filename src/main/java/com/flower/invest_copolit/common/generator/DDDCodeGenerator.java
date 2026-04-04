package com.flower.invest_copolit.common.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/**
 * 适配 DDD 架构的 MyBatis-Plus 代码生成器
 */
public class DDDCodeGenerator {

    // --- 可配置参数 ---
    private static final String AUTHOR = "copolit"; // 设置作者
    private static final String[] TABLES = {"sys_user"}; // 需要生成的表名
    private static final String MODULE_NAME = "user"; // 业务领域名 (Bounded Context)
    // ----------------

    public static void main(String[] args) {
        DatabaseConfig config = loadConfig();
        if (config == null) {
            System.err.println("错误：无法从 application.yml 加载数据库配置！");
            return;
        }

        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(config.url, config.username, config.password)
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            .enableSwagger()
                            .outputDir(projectPath + "/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.flower.invest_copolit")
                            .moduleName(MODULE_NAME)
                            // 适配 DDD 目录结构
                            .controller("interfaces.rest")
                            .entity("infrastructure.persistence.dataobject")
                            .mapper("infrastructure.persistence.mapper")
                            .service("application.service")
                            .serviceImpl("application.service.impl")
                            // XML 输出到 resources 目录下
                            .pathInfo(Collections.singletonMap(OutputFile.xml, 
                                    projectPath + "/src/main/resources/mapper/" + MODULE_NAME));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES)
                            .addTablePrefix("t_", "sys_") // 过滤表前缀
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            // 生成的实体类默认带 PO 或 DO 后缀，按需开启
                            // .formatFileName("%sDO")
                            .controllerBuilder()
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableMapperAnnotation();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        System.out.println("DDD 架构代码生成完成！领域：" + MODULE_NAME);
    }

    @SuppressWarnings("unchecked")
    private static DatabaseConfig loadConfig() {
        try (InputStream inputStream = DDDCodeGenerator.class.getClassLoader().getResourceAsStream("application.yml")) {
            Yaml yaml = new Yaml();
            Map<String, Object> obj = yaml.load(inputStream);
            Map<String, Object> spring = (Map<String, Object>) obj.get("spring");
            Map<String, Object> datasource = (Map<String, Object>) spring.get("datasource");
            
            DatabaseConfig config = new DatabaseConfig();
            config.url = (String) datasource.get("url");
            config.username = (String) datasource.get("username");
            config.password = String.valueOf(datasource.get("password"));
            
            if (config.url != null && config.url.endsWith("/")) {
                config.url += "invest_copolit?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
            }
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class DatabaseConfig {
        String url;
        String username;
        String password;
    }
}
