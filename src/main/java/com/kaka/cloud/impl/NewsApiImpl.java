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
import java.util.ArrayList;
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
    ServiceResultDto resultDto = ServiceResultDto.success();
    String baseUrl = "http://news.163.com/world/";

    Document doc= null;
    try {
      doc = Jsoup.connect(baseUrl).get();
    } catch (IOException e) {
      LogUtils.logOther("系统内部错误");
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
    resultDto.set("msg", "更新" + updateCount + "数据");
    LogUtils.logOther("更新" + updateCount + "数据");
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

  @Override
  public ServiceResultDto get4EchartNews(ServiceRequestDto reqDto) {
    List<News> dataList = newsMapper.queryNews(null, null);
    List<String> dataTitle = new ArrayList<>();

    dataTitle.add("00:00:00-03:59:59");
    dataTitle.add("04:00:00-07:59:59");
    dataTitle.add("08:00:00-11:59:59");
    dataTitle.add("12:00:00-15:59:59");
    dataTitle.add("16:00:00-19:59:59");
    dataTitle.add("20:00:00-23:59:59");

    List<Integer> dataValue = new ArrayList<>();
    int _0_section = 0, _1_section = 0, _2_section = 0, _3_section = 0, _4_section = 0, _5_section = 0;

    for(News news: dataList) {
      String updateTimeSub = news.getUpdateTime().substring(11);
      if (updateTimeSub.compareTo("00:00:00") >= 0 && updateTimeSub.compareTo("03:59:59") <= 0) {
        _0_section ++;
      }
      if (updateTimeSub.compareTo("04:00:00") >= 0 && updateTimeSub.compareTo("07:59:59") <= 0) {
        _1_section ++;
      }
      if (updateTimeSub.compareTo("08:00:00") >= 0 && updateTimeSub.compareTo("11:59:59") <= 0) {
        _2_section ++;
      }
      if (updateTimeSub.compareTo("12:00:00") >= 0 && updateTimeSub.compareTo("15:59:59") <= 0) {
        _3_section ++;
      }
      if (updateTimeSub.compareTo("16:00:00") >= 0 && updateTimeSub.compareTo("19:59:59") <= 0) {
        _4_section ++;
      }
      if (updateTimeSub.compareTo("20:00:00") >= 0 && updateTimeSub.compareTo("23:59:59") <= 0) {
        _5_section ++;
      }
    }

    dataValue.add(_0_section);
    dataValue.add(_1_section);
    dataValue.add(_2_section);
    dataValue.add(_3_section);
    dataValue.add(_4_section);
    dataValue.add(_5_section);

    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    serviceResultDto.set("dataTitle", dataTitle);
    serviceResultDto.set("dataValue", dataValue);
    return serviceResultDto;
  }
}
