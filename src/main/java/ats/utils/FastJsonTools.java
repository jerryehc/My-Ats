package ats.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;


public class FastJsonTools {
    private static Logger logger = LogManager.getLogger(FastJsonTools.class);

    public static String createJsonString(Object object) {
        String jsonString = "";
        try {
            jsonString = JSON.toJSONString(object);
        } catch (Exception e) {
            logger.debug("对象转换成JSON异常。", e);
        }

        return jsonString;
    }

}