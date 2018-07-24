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

  private Integer code;
  private String msg;
  private Integer count;
  private List<?> data = new ArrayList();

  public KakaResultDto() {
  }

  public KakaResultDto(Integer code, String msg, Integer count, List<?> data) {
    this.code = code;
    this.msg = msg;
    this.count = count;
    this.data = data;
  }

  public static KakaResultDto success() {
    KakaResultDto kakaResultDto = new KakaResultDto();
    kakaResultDto.setCode(0);
    return kakaResultDto;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<?> getData() {
    return data;
  }

  public void setData(List<?> data) {
    this.data = data;
  }
}
