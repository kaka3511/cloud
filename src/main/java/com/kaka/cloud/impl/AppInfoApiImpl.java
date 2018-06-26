package com.kaka.cloud.impl;

import com.kaka.cloud.api.AppInfoApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.mapper.AppInfoMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 14:20
 */
@Service("appInfoApi")
public class AppInfoApiImpl implements AppInfoApi {

  @Autowired
  private AppInfoMapper appInfoMapper;

  @Override
  public ServiceResultDto getAppInfo(ServiceRequestDto reqDto) {
    ServiceResultDto resultDto = ServiceResultDto.success();
    resultDto.set("appInfo", appInfoMapper.getAppInfo(1));
    return resultDto;
  }
}
