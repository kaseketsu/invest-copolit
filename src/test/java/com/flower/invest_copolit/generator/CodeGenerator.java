package com.flower.invest_copolit.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * MyBatis-Plus 代码生成器
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/invest_copolit?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "password";

        // 项目根路径
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("copolit") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.flower.invest_copolit") // 设置父包名
                            // .moduleName("asset") // 设置父包模块名 (按需修改，如 asset, market, ai 等)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("asset", "portfolio_position", "asset_thesis", "asset_keyword") // 设置需要生成的表名 (可按需修改)
                            .addTablePrefix("t_", "sys_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok() // 开启 lombok 模型
                            .enableTableFieldAnnotation() // 开启字段注解
                            .controllerBuilder()
                            .enableRestStyle() // 开启 RESTful 风格
                            .mapperBuilder()
                            .enableMapperAnnotation(); // 开启 @Mapper 注解
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
