package com.kaka.cloud.impl;

import com.github.pagehelper.PageHelper;
import com.kaka.cloud.api.NewsApi;
import com.kaka.cloud.common.KakaResultDto;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.entity.News;
import com.kaka.cloud.mapper.NewsMapper;
import com.kaka.cloud.util.LogUtils;
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
  public KakaResultDto updateNews(ServiceRequestDto reqDto) {
    KakaResultDto kakaResultDto = KakaResultDto.success();
    String baseUrl = "http://news.163.com/world/";

    Document doc= null;
    try {
      doc = Jsoup.connect(baseUrl).get();
    } catch (IOException e) {
      kakaResultDto.setMsg("系统内部错误");
      return kakaResultDto;
    }

    Elements elements = doc.select(".ns_area .today_news ul li a");

    int updateCount = 0;
    for (Element element:elements) {
      String title = element.attr("title");
      String url = element.attr("href");
      List<News> list = newsMapper.queryNews(null, url);
      if (list.size() == 0) {
        updateCount ++;
      }else {
        continue;
      }
      News insertObj = new News(title,url);
      newsMapper.insertNews(insertObj);
    }
    kakaResultDto.setMsg("更新" + updateCount + "数据");
    LogUtils.logOther("更新" + updateCount + "数据");
    return kakaResultDto;
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

    String title = "";
    if ((null != map.get("title") && !"".equals(map.get("title").toString().trim()))) {
      title = map.get("title").toString().trim();
    }

    List<News> countList = newsMapper.queryNews(title, null);
    PageHelper.startPage(page, size);
    List<News> dataList = newsMapper.queryNews(title, null);

    int totalNum = countList.size();

    serviceResultDto.set("data", dataList);
    serviceResultDto.set("page", page);
    serviceResultDto.set("size", size);
    serviceResultDto.set("pages", (totalNum + size - 1) / size);
    serviceResultDto.set("total", totalNum);
    return serviceResultDto;
  }

  @Override
  public ServiceResultDto delNews(ServiceRequestDto reqDto) {
    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    Map map = reqDto.getValues();
    String id = map.get("id").toString();
    newsMapper.delNews(id);
    return serviceResultDto;
  }

  @Override
  public ServiceResultDto modifyNews(ServiceRequestDto reqDto) {
    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    Map map = reqDto.getValues();
    Integer id = Integer.parseInt(map.get("id").toString());
    String title = "";
    String url = "";
    if (null != map.get("title") && !"".equals(map.get("title").toString().trim())) {
      title = map.get("title").toString().trim();
    }
    if (null != map.get("url") && !"".equals(map.get("url").toString().trim())) {
      url = map.get("url").toString().trim();
    }
    News news = new News(id,title,url);
    newsMapper.modifyNews(news);
    return serviceResultDto;
  }
}
