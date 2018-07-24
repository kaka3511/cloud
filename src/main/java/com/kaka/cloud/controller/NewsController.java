package com.kaka.cloud.controller;

import com.kaka.cloud.api.NewsApi;
import com.kaka.cloud.api.WeatherApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  public ServiceResultDto queryNews(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "size", required = false) Integer size) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.set("title", title);
    serviceRequestDto.set("page", page);
    serviceRequestDto.set("size", size);
    return newsApi.queryNews(serviceRequestDto);
  }

  @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto updateNews() {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    return newsApi.updateNews(serviceRequestDto);
  }
}
