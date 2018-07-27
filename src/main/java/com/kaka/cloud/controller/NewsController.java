package com.kaka.cloud.controller;

import com.kaka.cloud.api.NewsApi;
import com.kaka.cloud.api.WeatherApi;
import com.kaka.cloud.common.KakaResultDto;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    return newsApi.updateNews(serviceRequestDto);
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
}
