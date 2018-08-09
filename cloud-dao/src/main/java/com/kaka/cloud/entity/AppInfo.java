package com.kaka.cloud.entity;

import java.io.Serializable;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 14:24
 */
public class AppInfo implements Serializable {
  private Integer id;
  private String appName;
  private String appVersion;

  public AppInfo() {
  }

  public AppInfo(Integer id, String appName, String appVersion) {
    this.id = id;
    this.appName = appName;
    this.appVersion = appVersion;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }
}
