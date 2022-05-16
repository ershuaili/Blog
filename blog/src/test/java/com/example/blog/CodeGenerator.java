package com.example.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://47.96.145.7:3306/biz_blog?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String username = "root";
        String password = "l158E965S2022#";


        //表名集合
        List<String> tables = new ArrayList<>();
        tables.add("biz_article");
        tables.add("biz_article_look");
        tables.add("biz_article_tags");
        tables.add("biz_comment");
        tables.add("biz_data_change_log");
        tables.add("biz_dictionary");
        tables.add("biz_like");
        tables.add("biz_role");
        tables.add("biz_tags");
        tables.add("biz_type");
        tables.add("biz_update_recorde");
        tables.add("biz_user");
        tables.add("biz_user_action_log");
        tables.add("biz_user_role");


        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.disableOpenDir()  //禁止打开输出目录
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")   //指定输出目录
                            .author("李二帅")   //作者名
                            // .enableKotlin()      //开启 kotlin 模式
                            .enableSwagger()     //开启 swagger 模式
                            .dateType(DateType.TIME_PACK)   //时间策略
                            .commentDate("yyyy-MM-dd");   //注释日期
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.blog")     //父包名
                            .entity("entity")                 //Entity 包名
                            .service("service")             //	Service 包名
                            .serviceImpl("service.impl")    //Service Impl 包名
                            .mapper("mapper")               //Mapper 包名
                            .xml("mapper.xml")              //	Mapper XML 包名
                            .controller("controller")       //Controller 包名
                            .other("config")                //自定义文件包名	输出自定义文件时所用到的包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));//指定xml位置
                })
                // 模板配置
                .templateConfig(builder -> builder
                        // .disable(TemplateType.ENTITY)
                        .entity("templates/entity.java")
                        .service("templates/service.java")
                        .serviceImpl("templates/serviceImpl.java")
                        .controller("templates/controller.java"))
                //策略配置
                .strategyConfig(builder -> builder.addInclude(tables)
                        .addTablePrefix("biz_")//表名前缀，配置后生成的代码不会有此前缀
                        .serviceBuilder()
                        .formatServiceFileName("%sService")//服务层接口名后缀
                        .formatServiceImplFileName("%sServiceImpl")//服务层实现类名后缀
                        .entityBuilder()
                        .formatFileName("%sEntity")
                        .enableLombok()//实体类使用lombok,需要自己引入依赖
                        .logicDeleteColumnName("deleteFlag")//逻辑删除字段，使用delete方法删除数据时会将status设置为1。调用update方法时并不会将该字段放入修改字段中，而是在条件字段中
                        .enableTableFieldAnnotation()//加上字段注解@TableField
                        .controllerBuilder()
                        .formatFileName("%sController")//控制类名称后缀
                        .enableRestStyle()
                        .mapperBuilder()
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
