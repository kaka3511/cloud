package com.kaka.cloud.entity;

import java.io.Serializable;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/30 14:35
 */
public class FileInfo implements Serializable {

  private int id;
  private String fileType;
  private String fileName;
  private String url;
  private String updateTime;

  public FileInfo() {
  }

  public FileInfo(String fileType, String fileName, String url) {
    this.fileType = fileType;
    this.fileName = fileName;
    this.url = url;
  }

  public FileInfo(String fileType, String fileName, String url, String updateTime) {
    this.fileType = fileType;
    this.fileName = fileName;
    this.url = url;
    this.updateTime = updateTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
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
