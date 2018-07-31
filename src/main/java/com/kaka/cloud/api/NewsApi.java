package com.kaka.cloud.api;

import com.kaka.cloud.common.KakaResultDto;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(网易新闻爬取测试)
 * @date 2018/7/24 12:03
 */
public interface NewsApi {
  /**
   * 更新爬取内容
   * @param reqDto
   * @return
   */
  KakaResultDto updateNews(ServiceRequestDto reqDto);

  /**
   * 获取已爬取新闻
   * @param reqDto
   * @return
   */
  ServiceResultDto queryNews(ServiceRequestDto reqDto);


  /**
   * 删除新闻
   * @param reqDto
   * @return
   */
  ServiceResultDto delNews(ServiceRequestDto reqDto);

  /**
   * 修改新闻
   * @param reqDto
   * @return
   */
  ServiceResultDto modifyNews(ServiceRequestDto reqDto);

  /**
   * 查出24小时内6个区间段内各自的新增新闻量
   * @param reqDto
   * @return
   */
  ServiceResultDto get4EchartNews(ServiceRequestDto reqDto);

}
