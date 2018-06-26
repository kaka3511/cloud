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
 * @date 2018/6/26 10:24
 */
public class SystemConfigReader {
  private static Map<String, Object> systemConfigMap = new ConcurrentHashMap();
  private static final String BIZ_SYSTEM_PROPERTIES = "/system.properties";

  public SystemConfigReader() {
  }

  public static void reload() {
    InputStream errorMessageStream = SystemConfigReader.class.getResourceAsStream("/system.properties");
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
    return getConfig(key, (String)null);
  }

  public static String getConfig(String key, String defaultValue) {
    String value = defaultValue;
    if (systemConfigMap.containsKey(key)) {
      value = systemConfigMap.get(key).toString();
    }

    return value;
  }

  static {
    InputStream cfgStream = SystemConfigReader.class.getResourceAsStream("/system.properties");
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
