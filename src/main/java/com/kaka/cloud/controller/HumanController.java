package com.kaka.cloud.controller;

import com.kaka.cloud.api.HumanApi;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 9:45
 */
@Controller
@EnableAutoConfiguration
@CrossOrigin(value = "*" ,maxAge = 360)
@RequestMapping(value = "/human")
public class HumanController {

  @Autowired
  private HumanApi humanApi;


  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto signIn(@RequestBody Map map) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.put(map);
    return humanApi.signIn(serviceRequestDto);
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto signUp(@RequestBody Map map) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.put(map);
    return humanApi.signUp(serviceRequestDto);
  }

  @RequestMapping(value = "/signOut", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto signOut(@RequestBody Map map) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.put(map);
    return humanApi.signOut(serviceRequestDto);
  }

  @RequestMapping(value = "/getHuman", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto getHuman(@RequestBody Map map) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.put(map);
    return humanApi.getHuman(serviceRequestDto);
  }
}
