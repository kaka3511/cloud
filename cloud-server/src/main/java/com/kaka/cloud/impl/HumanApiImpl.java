package com.kaka.cloud.impl;

import com.kaka.cloud.api.HumanApi;
import com.kaka.cloud.api.RedisApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.entity.Human;
import com.kaka.cloud.mapper.HumanMapper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 9:29
 */
@Service("humanApi")
public class HumanApiImpl implements HumanApi {

  @Autowired
  RedisApi redisApi;

  @Autowired
  private HumanMapper humanMapper;

  @Override
  public ServiceResultDto signIn(ServiceRequestDto reqDto) {
    Map map = reqDto.getValues();
    String account = "";
    String pwd = "";
    try{
      account = map.get("account").toString().trim();
      pwd = map.get("pwd").toString().trim();
    }catch (Exception e) {
      return ServiceResultDto.error("1006", "账号或密码必传！");
    }
    if (account.equals("") || pwd.equals("")) {
      return ServiceResultDto.error("1006", "账号或密码必传！");
    }

    List<Human> resList = humanMapper.getHuman(null, account, pwd);
    if (resList == null || resList.size() == 0) {
      return ServiceResultDto.error("1007", "账号或密码错误！");
    }

    //redis缓存
    String token = UUID.randomUUID().toString();
    ServiceRequestDto requestDto = new ServiceRequestDto();
    requestDto.set("key", token);
    requestDto.set("obj", resList.get(0));
    redisApi.set(requestDto);

    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    serviceResultDto.set("token", token);
    return serviceResultDto;
  }

  @Override
  public ServiceResultDto signUp(ServiceRequestDto reqDto) {
    Map map = reqDto.getValues();
    String account = "";
    String pwd = "";
    try{
      account = map.get("account").toString().trim();
      pwd = map.get("pwd").toString().trim();
    }catch (Exception e) {
      return ServiceResultDto.error("1006", "账号或密码必传！");
    }
    if (account.equals("") || pwd.equals("")) {
      return ServiceResultDto.error("1006", "账号或密码必传！");
    }
    //不允许用户名相同
    List<Human> resList = humanMapper.getHuman(null, account, null);
    if (resList != null && resList.size() > 0) {
      return ServiceResultDto.error("1009", "账号已被使用！");
    }

    humanMapper.addHuman(account, pwd);
    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    return serviceResultDto;
  }

  @Override
  public ServiceResultDto signOut(ServiceRequestDto reqDto) {
    Map map = reqDto.getValues();
    String token = map.get("token").toString().trim();
    ServiceRequestDto requestDto = new ServiceRequestDto();
    requestDto.set("key", token);
    ServiceResultDto resultDto = redisApi.get(requestDto);
    Object object = resultDto.get("obj", Human.class);
    if (object == null) {
      return ServiceResultDto.error("1008", "账号未登录！");
    }
    return redisApi.del(requestDto);
  }

  @Override
  public ServiceResultDto getHuman(ServiceRequestDto reqDto) {
    return null;
  }
}
