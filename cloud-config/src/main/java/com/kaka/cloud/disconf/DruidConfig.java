package com.kaka.cloud.disconf;//package com.kaka.cloud.disconf;
//
//import com.baidu.disconf.client.common.annotations.DisconfFile;
//import com.baidu.disconf.client.common.annotations.DisconfFileItem;
//import org.springframework.stereotype.Component;
//
//@Component
//@DisconfFile(filename = "jdbc.properties")
//public class DruidConfig {
//
//  private String username;
//  private String password;
//  private String url;
//
//  @DisconfFileItem(name = "druid.username", associateField = "username")
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//
//  @DisconfFileItem(name = "druid.password", associateField = "password")
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  @DisconfFileItem(name = "druid.url", associateField = "url")
//  public String getUrl() {
//    return url;
//  }
//
//  public void setUrl(String url) {
//    this.url = url;
//  }
//
//  @Override
//  public String toString() {
//    return "DruidConfg [username=" + username + ", password=" + password + ", url=" + url + "]";
//  }
//
//}
