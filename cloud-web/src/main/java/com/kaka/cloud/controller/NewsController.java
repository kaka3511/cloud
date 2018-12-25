package com.kaka.cloud.controller;

import com.kaka.cloud.api.NewsApi;
import com.kaka.cloud.common.KakaResultDto;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.kaka.cloud.entity.News;
import com.kaka.cloud.util.ImportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/24 12:02
 */
@Controller
@EnableAutoConfiguration
@CrossOrigin(value = "*" ,maxAge = 360)
@RequestMapping(value = "/news")
public class NewsController {

  @Autowired
  private NewsApi newsApi;

  @RequestMapping(value = "/queryNews", method = RequestMethod.GET)
  @ResponseBody
  public KakaResultDto queryNews(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "keyword", required = false) String keyword) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.set("page", page);
    serviceRequestDto.set("size", limit);
    serviceRequestDto.set("title", keyword);
    ServiceResultDto serviceResultDto =  newsApi.queryNews(serviceRequestDto);

    KakaResultDto kakaResultDto = KakaResultDto.success();
    kakaResultDto.setCode(0);
    kakaResultDto.setMsg("");
    kakaResultDto.setCount(serviceResultDto.get("total", Integer.class));
    kakaResultDto.setData(serviceResultDto.get("data", List.class));
    return kakaResultDto;
  }

  @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
  @ResponseBody
  public KakaResultDto updateNews() {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    ServiceResultDto serviceResultDto = newsApi.updateNews(serviceRequestDto);
    KakaResultDto kakaResultDto = KakaResultDto.success();
    kakaResultDto.setCode(0);
    kakaResultDto.setMsg(serviceResultDto.get("msg", String.class));
    return kakaResultDto;
  }

  @RequestMapping(value = "/delNews", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto delNews(@RequestBody Map map) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.put(map);
    return newsApi.delNews(serviceRequestDto);
  }

  @RequestMapping(value = "/modifyNews", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto modifyNews(@RequestBody Map map) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.put(map);
    return newsApi.modifyNews(serviceRequestDto);
  }

  @RequestMapping(value = "/get4EchartNews", method = RequestMethod.GET)
  @ResponseBody
  public ServiceResultDto get4EchartNews() {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    return newsApi.get4EchartNews(serviceRequestDto);
  }

  @RequestMapping(value = "/exportNews", method = RequestMethod.GET)
  @ResponseBody
  public void exportNews(HttpServletResponse response) {
    ServiceRequestDto reqDto = new ServiceRequestDto();
    ServiceResultDto resultDto = newsApi.exportNews(reqDto);
    Map<String, Object> map = (Map<String, Object>) resultDto.getResponseBody();
    byte[] content = (byte[]) map.get("file_data");
    String title = map.get("title") + "";

    try {
      InputStream is = new ByteArrayInputStream(content);
      // 设置response参数，可以打开下载页面
      response.reset();
      response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
      response.setHeader("Content-Disposition", "attachment;filename=" + new String((title).getBytes("utf-8"), "iso-8859-1"));
      response.setContentLength(content.length);
      ServletOutputStream outputStream = response.getOutputStream();
      BufferedInputStream bis = new BufferedInputStream(is);
      BufferedOutputStream bos = new BufferedOutputStream(outputStream);
      byte[] buff = new byte[8192];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        bos.write(buff, 0, bytesRead);
      }
      bis.close();
      bos.close();
      outputStream.flush();
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/uploadNews", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto uploadNews(@RequestParam("file") MultipartFile file) {
    ServiceRequestDto reqDto = new ServiceRequestDto();
    if (file.isEmpty()) {
      return ServiceResultDto.error("5001", "文件不存在！");
    }
    int size = (int) file.getSize();
    if (size > 1024 * 1024 * 10) {
      return ServiceResultDto.error("5002", "文件过大！");
    }
    try {
      byte[] bytes = file.getBytes();
      reqDto.all().put("data", bytes);
    } catch (Exception e) {
      return ServiceResultDto.error("5003", e.getMessage());
    }
    return newsApi.uploadNews(reqDto);
  }

}
