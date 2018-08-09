package com.kaka.cloud.entity;

import java.io.Serializable;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 9:28
 */
public class Human implements Serializable{
  private int id;
  private String account;
  private String pwd;
  private String createTime;

  public Human() {
  }

  public Human(int id, String account, String pwd, String createTime) {
    this.id = id;
    this.account = account;
    this.pwd = pwd;
    this.createTime = createTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}
