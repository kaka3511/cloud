package com.kaka.cloud.config.diconf;//package com.kaka.cloud.config.diconf;
//
//import com.baidu.disconf.client.DisconfMgrBean;
//import com.baidu.disconf.client.DisconfMgrBeanSecond;
//import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;
//import com.baidu.disconf.client.addons.properties.ReloadingPropertyPlaceholderConfigurer;
//import com.kaka.cloud.disconf.DruidConfig;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Properties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author fuwei
// * @version V1.0
// * @Description: TODO(用一句话描述该文件做什么)
// * @date 2018/7/26 10:30
// */
//@Configuration
//public class Disconfiguration {
//
//
//  @Bean(name = "disconfMgrBean", destroyMethod = "destroy")
//  public DisconfMgrBean disconfMgrBean() {
//    DisconfMgrBean disconfMgrBean = new DisconfMgrBean();
//
//    disconfMgrBean.setScanPackage("com.kaka.cloud.disconf");
//
//    return disconfMgrBean;
//  }
//
//  @Bean(name = "disconfMgrBean2", destroyMethod = "destroy", initMethod = "init")
//  public DisconfMgrBeanSecond disconfMgrBean2() {
//    DisconfMgrBeanSecond disconfMgrBean = new DisconfMgrBeanSecond();
//
//    return disconfMgrBean;
//  }
//}
