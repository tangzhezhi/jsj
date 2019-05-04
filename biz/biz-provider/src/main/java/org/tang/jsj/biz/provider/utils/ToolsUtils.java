package org.tang.jsj.biz.provider.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.util.Properties;

public class ToolsUtils {

    public static final Properties appConfig = new Properties();	//zookeeper配置项

    public static Long generatorId(){
        return IdUtil.createSnowflake(1,1).nextId();
    }

    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

}
