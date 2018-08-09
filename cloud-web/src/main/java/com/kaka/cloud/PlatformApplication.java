package com.kaka.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/21 10:48
 */
@SpringBootApplication
@ImportResource(value = {"classpath:dubbo/applicationContext-dubbo-provider.xml"})
//@MapperScan("com.kaka.cloud.mapper")

public class PlatformApplication extends SpringBootServletInitializer {

  public static void main(String[] args){
    SpringApplication.run(PlatformApplication.class, args);
  }
}
