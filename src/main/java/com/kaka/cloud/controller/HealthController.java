package com.kaka.cloud.controller;

import com.kaka.cloud.api.AppInfoApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
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
  public String healthCheck2(){
    return "";
  }
}
