package ats;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final Logger infoLogger = LogManager.getLogger("ruby_log");
    private static final Properties properties = new Properties();

    /**
     * 资源文件一次获取就可以，不用多次导入
     */
    static {
        try {
            InputStream inputStream = new FileInputStream(new File(getConfigRelativePath() + "myats.properties"));
            properties.load(inputStream);
        } catch (Exception e) {
            infoLogger.error(e.getMessage(), e);
            infoLogger.error(e.getMessage(), e);
        }
    }

    /**
     * 取env.properties的InputStream
     */
    public static Properties getEnvProperties() {
        return properties;
    }

    /**
     * config文件夹的路径
     */
    public static String getConfigRelativePath() {
        return System.getProperty("user.dir") + FILE_SEPARATOR + "config" + FILE_SEPARATOR;
    }

    /**
     * 加密文件夹的路径
     */
    public static String getKeyRelativePath() {
        String keyFolderName = getPropertyValue("keyFolderName");
        if (keyFolderName == null) {
            infoLogger.error("请配置keyFolderName");
            return null;
        }
        return System.getProperty("user.dir") + FILE_SEPARATOR + keyFolderName + FILE_SEPARATOR;
    }

    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public static String getPropertyValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getPropertyValueInt(String key, int defaultValue) {
        int value = defaultValue;
        try {
            String valueStr = properties.getProperty(key);
            value = Integer.parseInt(valueStr);
        } catch (Exception e) {
            infoLogger.error(e.getMessage(), e);
        }
        return value;
    }

    public static boolean getPropertyValueBoolean(String key, boolean defaultValue) {
        boolean value = defaultValue;
        try {
            String valueStr = properties.getProperty(key);
            value = Boolean.parseBoolean(valueStr);
        } catch (Exception e) {
            infoLogger.error(e.getMessage(), e);
        }
        return value;
    }

    public static String[] getPropertyValuesArrays(String key, String[] defalutValue) {
        try {
            String valueStr = properties.getProperty(key);
            if (valueStr == null) {
                return defalutValue;
            }
            return valueStr.split(",");
        } catch (Exception e) {
            infoLogger.error(e.getMessage(), e);
            return null;
        }
    }
}
