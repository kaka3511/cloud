//package com.kaka.cloud.config.diconf;
//
//import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
//import com.baidu.disconf.client.common.update.IDisconfUpdate;
//import com.kaka.cloud.disconf.BizErrorMessageConfig;
//import com.kaka.cloud.disconf.CognateErrorCodeConfig;
//import com.kaka.cloud.util.CognateErrorCodeConfigReader;
//import com.kaka.cloud.util.ExceptionFormatter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @Title: ErrorMessageConfigCallbackService.java
// * @Package com.iboxpay.mbp.listener
// * @Description: 更新配置时的回调函数
// * @author fangjun
// * @date 2017年8月30日 下午5:34:14
// * @version V1.0
// */
//@Service
//@DisconfUpdateService(classes = { BizErrorMessageConfig.class,
//		CognateErrorCodeConfig.class })
//public class ErrorMessageConfigCallbackService implements IDisconfUpdate {
//
//	@Autowired
//	private ErrorMessageConfig errorMessageConfig;
//
//	@Override
//	public void reload() throws Exception {
//		errorMessageConfig.initErrorMap();
//		ExceptionFormatter.reload();// 刷新错误码
//		CognateErrorCodeConfigReader.reload();
//	}
//
//}
