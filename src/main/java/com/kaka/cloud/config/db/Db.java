package com.kaka.cloud.config.db;

import org.springframework.stereotype.Service;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/5/4 10:16
 */
@Service
public class Db {
  private String username = "localhost";
  private String password = "Kaka3511!";
//  private String url = "jdbc:oracle:thin:@172.30.0.242:1520:oradb01";
  private String url = "jdbc:mysql://139.199.59.97:3306/cloud";

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
