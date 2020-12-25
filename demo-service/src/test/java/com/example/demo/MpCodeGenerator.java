package com.example.demo;

import com.DemoApplication;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-plus 代码生成器配置
 * zhangyd
 * 2020/5/11 18:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class MpCodeGenerator {

    // 当前工程路径   配合outPutDir使用，例如多模块开发 Demo/test1，Demo/test2
    // projectPath拿到的是Demo路径，把outPutDir设置成/test1即可
    private static final String projectPath = System.getProperty("user.dir");

    @Test
    public void run(){

        //1.创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //2.全局配置
        GlobalConfig gc = new GlobalConfig();

        //输出目录-->更该第1个地方
        gc.setOutputDir("C:/working/git_project/spring-boot-demo/demo-service/src/main/java/");

        //作者
        gc.setAuthor("zhangyd");

        //生成后是否打开资源管理器
        gc.setOpen(false);

        //重新生成时文件是否覆盖
        gc.setFileOverride(false);

        //去掉Service接口的首字母I
        gc.setServiceName("%sService");

        //主键策略 ID_WORKER_STR对应String ID_WORKER对应Long
        gc.setIdType(IdType.AUTO);

        //定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);

        //开启Swagger2模式
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        //3.数据源配置  -->更该第2个地方
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.0.103:3306/test?useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //4.包配置 -->更该第3个地方
        PackageConfig pc = new PackageConfig();
        //模块名
        pc.setModuleName("tina");
        //启动类的包路径 如com.online.edu.service 需拆分parent:com.online.edu  module:service
        pc.setParent("com.example.demo");

        pc.setController("controller");
        pc.setEntity("model");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        //自定义xml文件生成路径
//        mpg.setCfg(getInjectionConfig());

        //5.策略配置
        StrategyConfig strategy = new StrategyConfig();
        //对应的表名   -->更该第4个地方
        strategy.setInclude("tb_user");
//        strategy.setTablePrefix("t_");//去掉表明的前缀
        //数据库字段映射到实体类的转换策略 如下为驼峰式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //开启lombok
        strategy.setEntityLombokModel(true);
        //restful开启
        strategy.setRestControllerStyle(true);
        //url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        //执行
        mpg.execute();
    }

    /**
     * 自定义xml文件生成路径
     * 这里注意会生成两个xml，一个是在你指定的下面，一个是在mapper包下的xml
     * 暂时无法解决，因为源码中的判断，判断的是tableInfo和pathInfo的xml属性是否为null，这两个类都是默认生成属性的
     * 且对if (null != injectionConfig)自定义生成的判断在默认的前面，所以会生成两遍。
     * 具体可见AbstractTemplateEngine batchOutput()的方法
     *
     * @return
     */
//    public static InjectionConfig getInjectionConfig() {
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//
//            }
//        };
//        String templatePath = "/templates/mapper.xml.vm";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/模块名/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        return cfg;
//    }
}
