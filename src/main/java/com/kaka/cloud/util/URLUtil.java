package com.kaka.cloud.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/21 13:41
 */
public class URLUtil {

  public static String getJson(String urlPath, String method, String charset) {
    try {
      URL url = new URL(urlPath);
      HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
      httpUrlConn.setDoInput(true);
      httpUrlConn.setRequestMethod(method);
      //获取输入流
      InputStream input = httpUrlConn.getInputStream();
      //将字节输入流转换为字符输入流
      InputStreamReader read = new InputStreamReader(input, charset);
      //为字符输入流添加缓冲
      BufferedReader br = new BufferedReader(read);
      // 读取返回结果
      String line = null;
      String data = "";
      //读取数据
      while ((line = br.readLine()) != null) {
        data += line;
      }
      // 释放资源
      br.close();
      read.close();
      input.close();
      httpUrlConn.disconnect();
      return data;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
