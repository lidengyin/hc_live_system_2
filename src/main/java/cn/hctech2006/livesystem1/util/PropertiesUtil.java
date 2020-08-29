package cn.hctech2006.livesystem1.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取设置
 */
public class PropertiesUtil {


    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    //需要在tomcat启动的时候读取里面的配置
    //静态代码块优于普通代码快优于构造代码块
    //使用静态块读取配置文件
    static {
        String fileName = "mmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }
    //通过key获取value工具类
    public static String getProperty(String key){
        //获取需要的key的值，使用trim阻隔key的影响
        String value = props.getProperty(key.trim());
        //判断获取的值是否为空
        if (value == null){
            return null;
        }
        return value.trim();
    }
    //通过key获取value工具类
    //方法重载，返回defaultValue
    public static String getProperty(String key, String defaultValue){
        //获取需要的key的值，使用trim阻隔key的影响
        String value = props.getProperty(key.trim());
        //判断获取的值是否为空
        if (value == null){
            return defaultValue;
        }
        return value.trim();
    }

}
