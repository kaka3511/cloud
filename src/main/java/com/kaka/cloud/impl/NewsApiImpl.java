package com.kaka.cloud.impl;

import com.github.pagehelper.PageHelper;
import com.kaka.cloud.api.NewsApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.entity.News;
import com.kaka.cloud.mapper.NewsMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/24 12:05
 */
@Service("newsApi")
public class NewsApiImpl implements NewsApi {

  @Autowired
  private NewsMapper newsMapper;
  /**
   *
   * @param reqDto
   * @return
   */
  @Override
  public ServiceResultDto updateNews(ServiceRequestDto reqDto) {
    String baseUrl = "http://news.163.com/world/";

    Document doc= null;
    try {
      doc = Jsoup.connect(baseUrl).get();
    } catch (IOException e) {
      return ServiceResultDto.error("500", "系统内部错误！");
    }

    Elements elements = doc.select(".ns_area .today_news ul li a");

    for (Element element:elements) {
      String title = element.attr("title");
      List<News> list = newsMapper.queryNews(title);
      if (list.size() > 0) {
        return ServiceResultDto.error("1003", "当前已是最新数据！");
      }
      String url = element.attr("href");
      News insertObj = new News(title,url);
      newsMapper.insertNews(insertObj);
    }
    ServiceResultDto resultDto = ServiceResultDto.success();
    resultDto.set("data", "");
    resultDto.set("msg", "更新完成！");
    return resultDto;
  }

  @Override
  public ServiceResultDto queryNews(ServiceRequestDto reqDto) {
    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    Map map = reqDto.getValues();
    Integer page = 1;
    Integer size = 10;
    if (null != map.get("page") && !"".equals(map.get("page").toString().trim())) {
      page = Integer.parseInt(map.get("page").toString().trim());
    }
    if (null != map.get("size") && !"".equals(map.get("size").toString().trim())) {
      size = Integer.parseInt(map.get("size").toString().trim());
    }

    List<News> countList = newsMapper.queryNews("");
    PageHelper.startPage(page, size);
    List<News> dataList = newsMapper.queryNews("");

    int totalNum = countList.size();

    Map<String, Object> resultMap = new HashMap(16);
    resultMap.put("data", dataList);
    resultMap.put("page", page);
    resultMap.put("size", size);
    resultMap.put("pages", (totalNum + size - 1) / size);
    resultMap.put("total", totalNum);
    serviceResultDto.setResponseBody(resultMap);
    return serviceResultDto;
  }
}
