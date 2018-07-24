package com.kaka.cloud.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(配合layui使用)
 * @date 2018/7/24 17:52
 */
public class KakaResultDto implements Serializable {

  private static final long serialVersionUID = -6144000382695493111L;

  private String code;
  private String msg;
  private String count;
  private List<?> data = new ArrayList();

  public KakaResultDto() {
  }

  public KakaResultDto(String code, String msg, String count, List<?> data) {
    this.code = code;
    this.msg = msg;
    this.count = count;
    this.data = data;
  }

  public static KakaResultDto success() {
    KakaResultDto kakaResultDto = new KakaResultDto();
    kakaResultDto.setCode("200");
    return kakaResultDto;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public List<?> getData() {
    return data;
  }

  public void setData(List<?> data) {
    this.data = data;
  }
}
