package com.kaka.cloud.api;

import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 14:18
 */
public interface AppInfoApi {

  /**
   * 获取app信息
   * @param reqDto
   * @return
   */
  ServiceResultDto getAppInfo(ServiceRequestDto reqDto);
}
