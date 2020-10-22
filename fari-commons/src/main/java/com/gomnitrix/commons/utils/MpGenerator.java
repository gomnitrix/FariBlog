package com.gomnitrix.commons.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class MpGenerator {
    public static void main(String[] args){
        // 设置引用模板
        AutoGenerator generator = new AutoGenerator();
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("/Users/gaobowei/IdeaProjects/fariblog/MPOutputs");
        globalConfig.setAuthor("gomnitrix");
        globalConfig.setOpen(false);
        // 是否覆盖同名文件，默认是false
        globalConfig.setFileOverride(false);
        // 是否支持ActiveRecord特性，默认false（不支持）
        /*
        在Mybatis-Plus中提供了ActiveRecord的模式，
        支持 ActiveRecord 形式调用，
        实体类只需继承 Model 类即可实现基本 CRUD 操作，
        简单来说就是一个实体类继承Model类，
        并通过注解与数据库的表名进行关联，
        这样就可以通过实体类直接进行表的简单增删改查操作，
        这样也确实极大的方便了开发人员。
         */
        globalConfig.setActiveRecord(true);
        // xml中的resultmap 默认false
        globalConfig.setBaseResultMap(true);
        // xml中的columList---没有默认值
        globalConfig.setBaseColumnList(true);
        /* 自定义文件命名，注意%s会自动填充表实体属性 */
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        // 加载全局配置
        generator.setGlobalConfig(globalConfig);

        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://81.71.139.43:3306/fariblog?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("omnitrix");
        dataSourceConfig.setPassword("981017");
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("test");
        pc.setParent("xx.flower");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("com.gomnitrix.farigateway.service");
        // 默认在service路径下的impl下，设个设置则可以指定其他路径
        //pc.setServiceImpl("com.gomnitrix.farigateway.service.impl");
        // 默认在mapper路径下的xml，这个设置则可以指定其他路径
        //pc.setXml("mapper.xml");
        generator.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表前缀，多个英文逗号分割或者 new String[]{"",""}
        strategy.setTablePrefix("user_");
        // 表明生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 表明，多个英文逗号分割或者 new String[]{"",""}
        strategy.setInclude("t_user", "t_blog");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);// 驼峰转连字符
        strategy.setEntityTableFieldAnnotationEnable(true);// 是否生成实体时，生成字段注解
        // 自己的父类实体，没有就不用设置
        //strategy.setSuperEntityClass();
        // 自己的父类控制器，没有就不用设置
        //strategy.setSuperControllerClass();
        // 字段自动操作策略
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        // 表字段与属性映射关系
        strategy.setTableFillList(tableFillList);
        strategy.setEntitySerialVersionUID(true);

        generator.setStrategy(strategy);

        /*
         * 1. 如果.xml文件是生成到Mapper类下面的xml文件夹下，这个配置不需要，如果配置了则不生成.xml文件了
         * 2. 如果.xml文件不是默认生成到Mapper类下面的xml文件夹下，而是指定到某个路径下，
         *      则该配置需要配置tc.setXml(null)，如果不配置则Mapper类下面的xml文件夹下还会同时生成一份默认的。
         */
//        TemplateConfig tc = new TemplateConfig();
//        tc.setXml(null);// 这个标注了则不生成XML文件了
//        generator.setTemplate(tc);

        // 注入配置， 该配置不在代码中配置出来的话，则生该自动生成的文件不会生成对应的文件！！！！
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        generator.setCfg(cfg);

        // 执行mybatis plus自动从表生成对应一系列的文件
        generator.execute();

    }
}