//package com.kaka.cloud.config.diconf;
//
//import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
//import com.baidu.disconf.client.common.update.IDisconfUpdate;
//import com.kaka.cloud.disconf.SystemConfig;
//import com.kaka.cloud.util.SystemConfigReader;
//import org.springframework.stereotype.Service;
//
//@Service
//@DisconfUpdateService(classes = { SystemConfig.class })
//public class SystemConfigCallbackService implements IDisconfUpdate {
//
//  @Override
//  public void reload() throws Exception {
//    SystemConfigReader.reload();// 刷新
//  }
//
//}