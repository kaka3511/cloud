package com.kaka.cloud.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/21 11:26
 */
public class ServiceRequestDto implements Serializable {
  private static final long serialVersionUID = -1970063306560035142L;
  private Map<String, Object> values = new HashMap();

  public ServiceRequestDto() {
  }

  public ServiceRequestDto(Map<String, Object> map) {
    this.values = map;
  }

  public ServiceRequestDto set(String key, Object value) {
    this.values.put(key, value);
    return this;
  }

  public <T> T get(String key, Class<T> c) {
    return c.cast(this.values.get(key));
  }

  public String getString(String key) {
    return (String) this.get(key, String.class);
  }

  public Object get(String key) {
    return this.values.get(key);
  }

  public Map<String, Object> all() {
    return this.values;
  }

  public Map<String, Object> getValues() {
    return this.values;
  }

  public ServiceRequestDto put(Map<String, Object> data) {
    this.values.putAll(data);
    return this;
  }
}
