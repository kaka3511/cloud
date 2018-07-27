package com.kaka.cloud.mapper;

import com.kaka.cloud.entity.News;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/24 12:11
 */
public interface NewsMapper {

  void insertNews(News news);

  List<News> queryNews(@Param(value="title") String title, @Param(value="url") String url);

  void delNews(@Param(value="id") String id);

  void modifyNews(News news);
}
