package com.kaka.cloud.entity;

import com.kaka.cloud.annotation.ExcelField;

import java.io.Serializable;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/24 12:08
 */
public class News implements Serializable{
  private int id;
  @ExcelField(name = "标题名称", maxLength = 40, required = true)
  private String title;
  @ExcelField(name = "新闻链接", maxLength = 45, required = true)
  private String url;
  @ExcelField(name = "爬取时间", maxLength = 10, required = true)
  private String updateTime;

  public News() {
  }

  public News(String title, String url) {
    this.title = title;
    this.url = url;
  }

  public News(int id, String title, String url) {
    this.id = id;
    this.title = title;
    this.url = url;
  }

  public News(int id, String title, String url, String updateTime) {
    this.id = id;
    this.title = title;
    this.url = url;
    this.updateTime = updateTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }
}
