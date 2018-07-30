package com.kaka.cloud.api;

import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/30 14:32
 */
public interface FileApi {

  /**
   * 上传文件接口
   * @param requestDto
   * @return
   */
  ServiceResultDto uploadFile(ServiceRequestDto requestDto);


  /**
   * 查询文件接口
   * @param requestDto
   * @return
   */
  ServiceResultDto queryFile(ServiceRequestDto requestDto);
}
