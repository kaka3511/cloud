package com.kaka.cloud.impl;

import com.alibaba.fastjson.JSON;
import com.kaka.cloud.api.WeatherApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.util.URLUtil;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/21 11:30
 */
@Service("weatherApi")
public class WeatherApiImpl implements WeatherApi {

  @Override
  public ServiceResultDto getWeatherNow(ServiceRequestDto reqDto) {
    Map map = reqDto.all();

    if (map == null) {
      return ServiceResultDto.error("1001", "参数有误！");
    }
    if (map.get("location") == null || map.get("location").toString().trim().equals("")) {
      return ServiceResultDto.error("1002", "location参数必填！");
    }

    String location = map.get("location").toString().trim();
    String urlPath = Constant.WEATHER_API_URL + "key=" + Constant.WEATHER_API_KEY + "&location=" + location;
    String weatherJson = URLUtil.getJson(urlPath, Constant.REQUEST_METHOD_GET, Constant.CHARSET_UTF_8);
    Map originMap = (Map) JSON.parse(weatherJson);
    List weatherMap = (List)originMap.get("HeWeather6");
    Map _0Map = (Map)weatherMap.get(0);
    Map nowMap = (Map)_0Map.get("now");
    String weatherDescription = nowMap.get("cond_txt").toString();
    String weatherTemperature = nowMap.get("fl").toString() + "°C";

    ServiceResultDto resultDto = ServiceResultDto.success();
    resultDto.set("weatherDescription", weatherDescription);
    resultDto.set("weatherTemperature", weatherTemperature);

    return resultDto;
  }

}
