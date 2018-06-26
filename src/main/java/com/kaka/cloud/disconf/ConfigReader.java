package com.kaka.cloud.disconf;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 11:17
 */
public class ConfigReader {
  private static Map<String, String> confs = new LinkedHashMap();
  private static final Logger LOG = LoggerFactory.getLogger(ConfigReader.class);

  public ConfigReader(String path) {
    load(path);
  }

  public static ConfigReader getInstance() {
    return ConfigReader.SingletonHolder.INSTANCE;
  }

  protected static void load(String path) {
    LOG.info("Loading config file {}.", path);
    if (confs != null && !confs.isEmpty()) {
      LOG.info("Loading config file already.", path);
    } else {
      InputStream is = null;
      BufferedReader br = null;
      Properties p = new Properties();

      try {
        is = ConfigReader.class.getResourceAsStream(path);
        br = new BufferedReader(new InputStreamReader(is));
        p.load(br);
      } catch (Exception var8) {
        LOG.error("Loading config file has exception", var8);
      } finally {
        safeClose(is);
        safeClose(br);
      }

      putAll(p);
    }
  }

  public static boolean safeClose(Closeable cb) {
    if (null != cb) {
      try {
        cb.close();
        return true;
      } catch (IOException var2) {
        LOG.error("Close stream has exception", var2);
      }
    }

    return false;
  }

  private static void putAll(Map p) {
    confs.putAll(p);
  }

  public synchronized void clear() {
    confs.clear();
  }

  protected void valid(String key) {
    if (StringUtils.isBlank(key)) {
      throw new NullPointerException("Key can't not be NULL or empty value." + key);
    }
  }

  public void put(String key, String value) {
    confs.put(key, value);
  }

  public List<String> keys() {
    return new ArrayList(confs.keySet());
  }

  public Collection<String> values() {
    return confs.values();
  }

  public String get(String key) {
    this.valid(key);
    return (String)confs.get(key);
  }

  public String getDefault(String key, String dfValue) {
    String value = (String)confs.get(key);
    if (value == null || value.trim().length() == 0) {
      value = dfValue;
    }

    return value;
  }

  public int getInt(String key) {
    this.valid(key);
    return Integer.parseInt(this.get(key));
  }

  public int getIntDefault(String key, int dfValue) {
    String value = (String)confs.get(key);
    return value != null && value.trim().length() != 0 ? Integer.parseInt(value) : dfValue;
  }

  public long getLongDefault(String key, long dfValue) {
    String value = (String)confs.get(key);
    return value != null && value.trim().length() != 0 ? Long.valueOf(this.get(key)) : dfValue;
  }

  public long getLong(String key) {
    this.valid(key);
    return Long.valueOf(this.get(key));
  }

  public boolean getBoolean(String key) {
    this.valid(key);
    return Boolean.valueOf(this.get(key));
  }

  private static class SingletonHolder {
    private static final ConfigReader INSTANCE = new ConfigReader("/disconf.properties");

    private SingletonHolder() {
    }
  }
}

