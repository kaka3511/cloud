package com.kaka.cloud.config.file;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(文件上传配置)
 * @date 2018/8/1 14:01
 */
@Configuration
public class MultipartConfig {
  /**
   * 文件上传配置
   * @return
   */
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    //文件最大
    factory.setMaxFileSize("10240KB"); //KB,MB
    /// 设置总上传数据总大小
    factory.setMaxRequestSize("102400KB");
    return factory.createMultipartConfig();
  }

}
