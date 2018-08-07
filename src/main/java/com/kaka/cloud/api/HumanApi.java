package com.kaka.cloud.api;

import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 9:20
 */
public interface HumanApi {

  /**
   * 登录
   * @param reqDto
   * @return
   */
  ServiceResultDto signIn(ServiceRequestDto reqDto);

  /**
   * 注册
   * @param reqDto
   * @return
   */
  ServiceResultDto signUp(ServiceRequestDto reqDto);

  /**
   * 登出
   * @param reqDto
   * @return
   */
  ServiceResultDto signOut(ServiceRequestDto reqDto);

  /**
   * 获取用户信息
   * @param reqDto
   * @return
   */
  ServiceResultDto getHuman(ServiceRequestDto reqDto);
}
