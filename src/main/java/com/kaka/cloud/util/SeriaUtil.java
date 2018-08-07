package com.kaka.cloud.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 10:35
 */
public class SeriaUtil {

  //序列化
  public static byte [] serialize(Object obj){
    ObjectOutputStream obi=null;
    ByteArrayOutputStream bai=null;
    try {
      bai=new ByteArrayOutputStream();
      obi=new ObjectOutputStream(bai);
      obi.writeObject(obj);
      byte[] byt=bai.toByteArray();
      return byt;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  //反序列化
  public static Object unserizlize(byte[] byt){
    ObjectInputStream oii=null;
    ByteArrayInputStream bis=null;
    bis=new ByteArrayInputStream(byt);
    try {
      oii=new ObjectInputStream(bis);
      Object obj=oii.readObject();
      return obj;
    } catch (Exception e) {

      e.printStackTrace();
    }
    return null;
  }
}
