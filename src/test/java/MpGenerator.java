import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @author alan smith
 * @version 1.0
 * @ProjectName: mybatis-plus
 * @ClassName: MpGenerator
 * @email: lawsssscat@163.com
 * @Description: MyBatis-plus代码生成器
 * @date 2020/4/15 12:01
 */
public class MpGenerator {

    /**
     * 记录配置信息
     */
    private static Map<String, String> config = new LinkedHashMap<>();

    /**
     * 不同模块配置的分割横幅
     */
    public static void banner(String title) {
        StringBuilder banner = new StringBuilder();
        banner.append("\n*********************************\n");
        banner.append(title + "\n");
        banner.append("*********************************\n");
        System.out.print(banner);
    }

    /**
     * 读取控制台内容
     */
    public static String printter(String tip, String defaultValue) {
        StringBuilder help = new StringBuilder();
        help.append(String.format("┌─%s默认为：%s%n", tip, defaultValue));
        System.out.print(help.toString());
        config.put(tip, defaultValue);
        return defaultValue;
    }

    public static void main(String[] args) {
        /**
         * 代码生成器
         */
        AutoGenerator generator = new AutoGenerator();

        /**
         * 全局配置
         */
        banner("全局配置");
        GlobalConfig globalConfig = new GlobalConfig();
        //生成文件的输出目录
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(printter("生成文件的输出目录", projectPath + "/src/main/java"));
        //Author设置作者
        globalConfig.setAuthor(printter("作者", "lawsssscat"));
        // 是否覆盖已有文件，默认false
        globalConfig.setFileOverride(false);
        // 是否打开输出目录，默认true
        globalConfig.setOpen(false);
        // 是否开启 ActiveRecord 模式，默认false
        globalConfig.setActiveRecord(true);
        // 是否在xml中添加二级缓存配置
        globalConfig.setEnableCache(false);
        // 是否开启 BaseResultMap 模式，默认false
        globalConfig.setBaseResultMap(true);
        // 时间类型对应策略，默认TIME_PACK(使用 java.time 包下的，java8 新的时间类型)
        globalConfig.setDateType(DateType.TIME_PACK);
        // 设置全局配置
        generator.setGlobalConfig(globalConfig);

        /**
         * 数据源配置
         */
        banner("数据源配置");
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型,默认MYSQL
        dataSourceConfig.setDbType(DbType.MYSQL);
        //自定义数据类型转换
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        dataSourceConfig.setUrl(printter("数据源路径", "jdbc:mysql://192.168.56.10:3306/ve_db?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false"));
        dataSourceConfig.setDriverName(printter("数据库驱动", "com.mysql.jdbc.Driver"));
        dataSourceConfig.setUsername(printter("用户名", "root"));
        dataSourceConfig.setPassword(printter("密码", "root"));
        // 设置数据源
        generator.setDataSource(dataSourceConfig);

        /**
         * 包配置
         */
        banner("包配置");
        PackageConfig packageConfig = new PackageConfig();
        // 模块名
        packageConfig.setModuleName(printter("模块名", "veat"));
        // 模块所在的包名，拼接在模块名前。也可在模块名中直接指定全包名
        packageConfig.setParent(printter("模块所在的包", "com.vshop"));
        // 设置包配置
        generator.setPackageInfo(packageConfig);


        /**
         * 自定义配置（定义输出相关的配置）
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // TODO
            }
        };
        //
        // 选择一个模板，输出的xml映射文件的模板
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        //
        // 映射文件的输出目录
        String mapperOutputDir = printter("映射文件输出目录", projectPath + "/src/main/resources/mapper/");
        // 自定义输出配置
        FileOutConfig fileOutConfig = new FileOutConfig(templatePath) {
            /**
             * 自定义输出文件名，如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
             */
            @Override
            public String outputFile(TableInfo tableInfo) {
                return mapperOutputDir +
                        packageConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        };
        // 设置自定义输出配置
        // 自定义配置会被优先输出
        cfg.setFileOutConfigList(Arrays.asList(fileOutConfig));
        // 设置自定义配置
        generator.setCfg(cfg);

        /**
         * 配置模板
         */
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        // 设置模板配置
        generator.setTemplate(templateConfig);

        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        //设置对象字段命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //设置表中列的字段命名格式
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 指定表名 如 ve_user
        //strategy.setInclude(printter("表名", "produce,user").split(","));
        // 表名前缀 如 ve_
        strategy.setTablePrefix(printter("表名前缀", "ve_"));
        // 实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 设置自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityClass("com.jiangfeixiang.mpdemo.BaseEntity");
        // 设置自定义继承的Controller类全称，带包名
        //strategy.setSuperControllerClass("com.jiangfeixiang.mpdemo.BaseController");
        // 设置自定义基础的Entity类，公共字段
        strategy.setSuperEntityColumns("id");
        // 驼峰转连字符 如：@RequestMapping("/manager-user-action-history")
        strategy.setControllerMappingHyphenStyle(true);
        // 设置策略配置
        generator.setStrategy(strategy);


        // 配置完
        /**
         * 打印上面已配置项
         */
        banner("您的配置");
        config.forEach((key, value) -> {
            System.out.print(String.format("%s=%s %n", key, value));
        });

        // 设置模板引擎
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 开始代码生成
        generator.execute();
    }
}

