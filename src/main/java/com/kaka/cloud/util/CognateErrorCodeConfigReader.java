package com.kaka.cloud.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 10:32
 */
public class CognateErrorCodeConfigReader {
  private static Map<String, Object> systemConfigMap = new ConcurrentHashMap();
  private static final String BIZ_SYSTEM_PROPERTIES = "/cognateErrorCode.properties";

  public CognateErrorCodeConfigReader() {
  }

  public static void reload() {
    InputStream errorMessageStream = CognateErrorCodeConfigReader.class.getResourceAsStream("/cognateErrorCode.properties");
    Properties properties = new Properties();

    try {
      properties.load(errorMessageStream);
    } catch (IOException var6) {
      ;
    }

    String key = null;
    String value = null;
    Iterator i$ = properties.entrySet().iterator();

    while(i$.hasNext()) {
      Map.Entry<?, ?> entry = (Map.Entry)i$.next();
      if (null != entry.getKey() && null != entry.getValue()) {
        key = entry.getKey().toString();
        value = entry.getValue().toString();
        systemConfigMap.put(key, value);
      }
    }

  }

  public static String getConfig(String key) {
    String value = null;
    if (systemConfigMap.containsKey(key)) {
      value = systemConfigMap.get("ERROR_PRE") + systemConfigMap.get(key).toString();
    }

    return value;
  }

  public static String getConfigNoPre(String key) {
    String value = null;
    if (systemConfigMap.containsKey(key)) {
      value = systemConfigMap.get(key).toString();
    }

    return value;
  }

  static {
    InputStream cfgStream = CognateErrorCodeConfigReader.class.getResourceAsStream("/cognateErrorCode.properties");
    Properties properties = new Properties();

    try {
      properties.load(cfgStream);
    } catch (IOException var6) {
      ;
    }

    String key = null;
    String value = null;
    Iterator i$ = properties.entrySet().iterator();

    while(i$.hasNext()) {
      Map.Entry<?, ?> entry = (Map.Entry)i$.next();
      if (null != entry.getKey() && null != entry.getValue()) {
        key = entry.getKey().toString();
        value = entry.getValue().toString();
        systemConfigMap.put(key, value);
      }
    }

  }
}
