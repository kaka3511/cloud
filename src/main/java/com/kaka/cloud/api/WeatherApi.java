package com.kaka.cloud.api;

import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(和风天气Api接口)
 * @date 2018/6/21 11:11
 */
public interface WeatherApi {

  /**
   * 获取实时天气状况
   * @param reqDto
   * @return
   */
  ServiceResultDto getWeatherNow(ServiceRequestDto reqDto);
}
