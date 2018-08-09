package com.kaka.cloud.util;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/25 11:04
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LogUtils {
  public static final Logger ERROR_LOG = LoggerFactory.getLogger("error");
  public static final Logger ACCESS_LOG = LoggerFactory.getLogger("access");
  public static final Logger INTERFACE_LOG = LoggerFactory.getLogger("interface");
  public static final Logger REMOTE_LOG = LoggerFactory.getLogger("remote");
  public static final Logger OTHER_LOG = LoggerFactory.getLogger("other");
  public static final String INTERFACE_NAME = "InterfaceTag";
  public static final String METHOD_NAME = "ServerId";
  public static final String PROTOCOL = "Protocol";
  public static final String LOG_TYPE = "LogType";

  public LogUtils() {
  }

  public static void logInterfaceReq(String text) {
    addParams();
    MDC.put("LogType", LogType.REQUEST.getValue());
    INTERFACE_LOG.info(text);
    removeParams();
    MDC.remove("LogType");
  }

  public static void logInterfaceProtocolReq(String text, Protocol protocol) {
    MDC.put("Protocol", protocol.getValue());
    logInterfaceReq(text);
    MDC.remove("Protocol");
  }

  private static void removeParams() {
    MDC.remove("InterfaceTag");
    MDC.remove("ServerId");
  }

  private static void addParams() {
    if (MDC.get("InterfaceTag") == null) {
      MDC.put("InterfaceTag", getCallerClass());
    }

    if (MDC.get("ServerId") == null) {
      MDC.put("ServerId", "- " + getCallerMethod());
    }

  }

  private static String getCallerMethod() {
    return getCallName(2);
  }

  private static String getCallerClass() {
    return getCallName(1);
  }

  private static String getCallName(int type) {
    StackTraceElement[] stacks = (new Throwable()).getStackTrace();
    Integer index = null;

    for(int i = 0; i < stacks.length; ++i) {
      if (!LogUtils.class.getName().equals(stacks[i].getClassName())) {
        index = i;
        break;
      }
    }

    if (type == 1) {
      String clazz = index != null ? stacks[index].getClassName() : "";
      if (clazz.lastIndexOf(46) > -1) {
        clazz = clazz.substring(clazz.lastIndexOf(46) + 1);
      }

      return getBlock(clazz);
    } else {
      return index != null ? getBlock(stacks[index].getMethodName()) : "";
    }
  }

  public static void logInterfaceResp(String text) {
    addParams();
    MDC.put("LogType", LogType.RESPONSE.getValue());
    INTERFACE_LOG.info(text);
    removeParams();
    MDC.remove("LogType");
  }

  public static void logInterfaceProtocolResp(String text, Protocol protocol) {
    MDC.put("Protocol", protocol.getValue());
    logInterfaceResp(text);
    MDC.remove("Protocol");
  }

  public static void logRemoteRequest(String url, String parameters) {
    addParams();
    MDC.put("LogType", LogType.REQUEST.getValue());
    REMOTE_LOG.info("CALL " + url + ", PARAMETERS IS " + parameters);
    MDC.remove("LogType");
    removeParams();
  }

  public static void logRemoteResponse(String url, String parameters) {
    addParams();
    MDC.put("LogType", LogType.RESPONSE.getValue());
    REMOTE_LOG.info("CALL " + url + ", RESULTJSON IS " + parameters);
    MDC.remove("LogType");
    removeParams();
  }

  public static void logRemoteException(String url, Exception e) {
    addParams();
    REMOTE_LOG.error("CALL " + url + e.getMessage());
    removeParams();
  }

  public static void logRemoteProtocolReq(String msg, Protocol protocol) {
    logRemoteByProtocol(msg, protocol, LogType.REQUEST);
  }

  public static void logRemoteProtocolResp(String msg, Protocol protocol) {
    logRemoteByProtocol(msg, protocol, LogType.RESPONSE);
  }

  private static void logRemoteByProtocol(String msg, Protocol protocol, LogType logType) {
    addParams();
    MDC.put("LogType", logType.getValue());
    MDC.put("Protocol", protocol.getValue());
    REMOTE_LOG.info(msg);
    removeParams();
    MDC.remove("LogType");
    MDC.remove("Protocol");
  }

  public static void logError(String message, Throwable e) {
    addParams();
    ERROR_LOG.error(message, e);
    removeParams();
  }

  public static void logAccess(String text) {
    ACCESS_LOG.info(text);
  }

  public static void logOther(String text) {
    addParams();
    OTHER_LOG.info(text);
    removeParams();
  }

  public static String getBlock(Object msg) {
    if (msg == null) {
      msg = "";
    }

    return "[" + msg.toString() + "]";
  }

  public static String formatMsg(Object msg) {
    if (msg == null) {
      msg = "null";
    }

    return msg.toString() + "|";
  }

  public static enum LogType {
    REQUEST("[request]"),
    RESPONSE("[response]");

    String value;

    private LogType(String value) {
      this.value = value;
    }

    public String getValue() {
      return this.value;
    }
  }

  public static enum Protocol {
    HTTP("[HTTP]"),
    DUBBO("[DUBBO]"),
    WEBSERVICE("[WEBSERVICE]");

    String value;

    private Protocol(String value) {
      this.value = value;
    }

    public String getValue() {
      return this.value;
    }
  }
}

