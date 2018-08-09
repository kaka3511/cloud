package com.kaka.cloud.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/21 11:27
 */
public class ServiceResultDto implements Serializable {
  private static final long serialVersionUID = -6144000382695493830L;

  private String resultCode;
  private String errorCode;
  private String errorDesc;

  private String dataType = "0";
  private Map<String, Object> responseBody = new HashMap();
  private List<?> responseList = new ArrayList();

  public ServiceResultDto() {
  }

  public ServiceResultDto(Map<String, Object> map) {
    this.setResultCode("0");
    this.setResponseBody(map);
  }

  public ServiceResultDto(List list) {
    this.setResultCode("0");
    this.setResponseBody(list);
  }

  public ServiceResultDto set(String key, Object value) {
    this.responseBody.put(key, value);
    return this;
  }

  public String getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorDesc() {
    return this.errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }

  public String getString(String key) {
    return (String)this.get(key, String.class);
  }

  public Long getLong(String key) {
    return (Long)this.get(key, Long.class);
  }

  public Integer getInteger(String key) {
    return (Integer)this.get(key, Integer.class);
  }

  public <T> T get(String key, Class<T> c) {
    return c.cast(this.responseBody.get(key));
  }

  public ServiceResultDto put(Map<String, Object> data) {
    if (data == null) {
      return this;
    } else {
      Iterator var3 = data.entrySet().iterator();

      while(var3.hasNext()) {
        Map.Entry<String, Object> e = (Map.Entry)var3.next();
        this.put((String)e.getKey(), e.getValue());
      }

      return this;
    }
  }

  public void putAll(Map<String, Object> data) {
    this.responseBody.putAll(data);
  }

  public void put(String key, Object value) {
    if ("resultCode".equals(key)) {
      this.setResultCode(value.toString());
    } else if ("errorCode".equals(key)) {
      this.setErrorCode(value.toString());
    } else if ("errorDesc".equals(key)) {
      this.setErrorDesc(value.toString());
    } else {
      this.responseBody.put(key, value);
    }

  }

  public Map<String, Object> all() {
    Map<String, Object> m = this.baseValues();
    m.putAll(this.responseBody);
    return m;
  }

  private Map<String, Object> baseValues() {
    Map<String, Object> baseValues = new HashMap();
    putIfNotNull(baseValues, "errorCode", this.getErrorCode());
    putIfNotNull(baseValues, "errorDesc", this.getErrorDesc());
    putIfNotNull(baseValues, "resultCode", this.getResultCode());
    return baseValues;
  }

  private static void putIfNotNull(Map<String, Object> m, String key, Object value) {
    if (value != null) {
      m.put(key, value);
    }

  }

  public static ServiceResultDto success() {
    ServiceResultDto result = new ServiceResultDto();
    result.setResultCode("0");
    return result;
  }

  public static ServiceResultDto error(String errorCode, String errorDesc) {
    ServiceResultDto result = new ServiceResultDto();
    result.setResultCode("1");
    result.setErrorCode(errorCode);
    result.setErrorDesc(errorDesc);
    return result;
  }

  public boolean isOk() {
    return this.getResultCode().equals("0");
  }

  public Object getResponseBody() {
    if (this.dataType.equals("0")) {
      return this.responseBody;
    } else {
      return this.dataType.equals("1") ? this.responseList : null;
    }
  }

  public ServiceResultDto setResponseBody(Map responseBody) {
    this.dataType = "0";
    this.responseBody = responseBody;
    return this;
  }

  public ServiceResultDto setResponseBody(List responseBody) {
    this.dataType = "1";
    this.responseList = responseBody;
    return this;
  }
}
