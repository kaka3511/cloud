package com.kaka.cloud;

import com.kaka.cloud.util.CognateErrorCodeConfigReader;
import com.kaka.cloud.util.ExceptionFormatter;
import com.kaka.cloud.util.SystemConfigReader;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/26 10:29
 */
public class TestUtil {
  public static void main(String[] args) {
//    System.out.println(SystemConfigReader.getConfig("system.machine.validate.key"));
    System.out.println(ExceptionFormatter
            .format(CognateErrorCodeConfigReader.getConfig("ERROR_TARGET_NOT_FOUND")));
  }
}
