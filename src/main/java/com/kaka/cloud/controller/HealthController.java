package com.kaka.cloud.controller;

import com.kaka.cloud.api.AppInfoApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 11:22
 */
@Controller
@EnableAutoConfiguration
@CrossOrigin(value = "*" ,maxAge = 360)
public class HealthController {

  @Autowired
  private AppInfoApi appInfoApi;

  @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
  @ResponseBody
  public ServiceResultDto healthCheck() {
    return appInfoApi.getAppInfo(new ServiceRequestDto());
  }

  @RequestMapping(value = "/healthCheck2", method = RequestMethod.GET)
  @ResponseBody
  public String healthCheck2() throws Exception{
    String baseUrl = "http://www.layui.com/demo/table/user/";

    HttpURLConnection connection = null;
    InputStream is = null;
    BufferedReader br = null;
    String result = null;// 返回结果字符串
      // 创建远程url连接对象
      URL url = new URL(baseUrl);
      // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
      connection = (HttpURLConnection) url.openConnection();
      // 设置连接方式：get
      connection.setRequestMethod("GET");
      // 设置连接主机服务器的超时时间：15000毫秒
      connection.setConnectTimeout(15000);
      // 设置读取远程返回的数据时间：60000毫秒
      connection.setReadTimeout(60000);
      // 发送请求
      connection.connect();
      // 通过connection连接，获取输入流
      if (connection.getResponseCode() == 200) {
        is = connection.getInputStream();
        // 封装输入流is，并指定字符集
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        // 存放数据
        StringBuffer sbf = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null) {
          sbf.append(temp);
          sbf.append("\r\n");
        }
        result = sbf.toString();
      }
      return result;
  }
}
