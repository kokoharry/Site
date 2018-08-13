package com.kokoharry.site.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by luyb on 2017/10/18.
 */
public class ConfigProperties {

    private static Logger logger = LogManager.getLogger(ConfigProperties.class);
    /**
     * 配置文件路径
     */
    private static final String CONFIGFILE = "/config.properties";
    /**
     * 属性读取类
     */
    private static Properties props = null;
    /**
     * 初始化加载属性文件
     */
    static {
        reloadProps();
    }

    /**
     * 重新加载属性文件
     */
    public static void reloadProps() {
        InputStream stream = ConfigProperties.class.getResourceAsStream(CONFIGFILE);
        try {
            props = new Properties();
            props.load(stream);
            stream.close();
        } catch (Exception e) {
            logger.error("can not load config.properties", e);
        }
    }

    /**
     * 通过 prop 配置文件加载类获取对应的属性配置值 如果为空返回 “”
     *
     * @param key 属性key
     * @return
     */
    public static String getValuesFromProp(String key) {
        String value = "";
        if (props != null) {
            value = props.getProperty(key, "");
        }
        return value;
    }
}
