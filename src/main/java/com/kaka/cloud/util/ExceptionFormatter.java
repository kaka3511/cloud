package com.kaka.cloud.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 10:51
 */
public class ExceptionFormatter {
  private static Logger LOG = LoggerFactory.getLogger(ExceptionFormatter.class);
  private static Map<String, Object> errorMessageMap = new ConcurrentHashMap();
  private static final String BIZ_ERROR_PROPERTIES = "/bizErrorMessageFormat.properties";

  public ExceptionFormatter() {
  }

  public static void setErrorMap(Map<String, Object> errorMessageMap) {
    errorMessageMap = errorMessageMap;
  }

  public static String format(String errorNo, Object... message) {
    Object patternObj = errorMessageMap.get(errorNo);
    return patternObj != null ? MessageFormat.format(patternObj.toString(), message) : MessageFormat.format("{0}", message);
  }

  public static String format(Object... message) {
    return MessageFormat.format("{0}", message);
  }

  public static String format(String errorNo) {
    Object patternObj = errorMessageMap.get(errorNo);
    return patternObj != null ? patternObj.toString() : "";
  }

  public static void reload() {
    InputStream errorMessageStream = ExceptionFormatter.class.getResourceAsStream("/bizErrorMessageFormat.properties");
    Properties properties = new Properties();

    try {
      properties.load(errorMessageStream);
    } catch (IOException var6) {
      ;
    }

    String errorNo = null;
    String errorMessage = null;
    Iterator i$ = properties.entrySet().iterator();

    while(i$.hasNext()) {
      Map.Entry<?, ?> entry = (Map.Entry)i$.next();
      if (null != entry.getKey() && null != entry.getValue()) {
        errorNo = entry.getKey().toString();
        errorMessage = entry.getValue().toString();
        errorMessageMap.put(errorNo, errorMessage);
      }
    }

  }

  static {
    InputStream errorMessageStream = ExceptionFormatter.class.getResourceAsStream("/bizErrorMessageFormat.properties");
    Properties properties = new Properties();

    try {
      properties.load(errorMessageStream);
    } catch (IOException var6) {
      ;
    }

    String errorNo = null;
    String errorMessage = null;
    Iterator i$ = properties.entrySet().iterator();

    while(i$.hasNext()) {
      Map.Entry<?, ?> entry = (Map.Entry)i$.next();
      if (null != entry.getKey() && null != entry.getValue()) {
        errorNo = entry.getKey().toString();
        errorMessage = entry.getValue().toString();
        errorMessageMap.put(errorNo, errorMessage);
      }
    }

  }
}

