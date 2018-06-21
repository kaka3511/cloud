package com.kaka.cloud.controller;

import com.kaka.cloud.api.WeatherApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(天气api接口)
 * @date 2018/6/21 10:49
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/weather")
public class WeatherController {

  @Autowired
  private WeatherApi weatherApi;

  @RequestMapping(value = "/getWeatherNow", method = RequestMethod.GET)
  @ResponseBody
  public ServiceResultDto getWeatherNow(@RequestParam(value = "location", required = false) String location) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.set("location", location);
    return weatherApi.getWeatherNow(serviceRequestDto);
  }

}
