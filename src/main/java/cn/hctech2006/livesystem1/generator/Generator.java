package cn.hctech2006.livesystem1.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void main(String []args) throws Exception{
        //MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //当前代码重复时，覆盖旧的代码
        boolean overwrite = true;
        //读取BMG配置文件
        InputStream is = Generator.class.getResourceAsStream(
                "/generator/generatorConfig.xml");
        //配置解析器
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //配置器
        Configuration config = cp.parseConfiguration(is);
        //关闭输入流
        is.close();
        //默认回滚
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建MBG
        MyBatisGenerator mbg = new MyBatisGenerator(config,callback,warnings);
        //执行生成代码
        mbg.generate(null);
        //输出警告信息
        for(String warn: warnings){
            System.out.println(warn);
        }
    }

}