package com.kaka.cloud.api;

import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 10:16
 */
public interface RedisApi {

  ServiceResultDto set(ServiceRequestDto reqDto);
  ServiceResultDto get(ServiceRequestDto reqDto);
  ServiceResultDto del(ServiceRequestDto reqDto);
//  ServiceResultDto set(String key, Object obj);

//  ServiceResultDto get(String key);

//  ServiceResultDto del(String key);

}
