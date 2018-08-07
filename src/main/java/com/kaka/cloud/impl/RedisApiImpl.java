package com.kaka.cloud.impl;

import com.kaka.cloud.api.RedisApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.config.redis.RedisConfig;
import com.kaka.cloud.util.SeriaUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 10:14
 */
@Service("redisApi")
public class RedisApiImpl implements RedisApi{

    @Autowired
    JedisPool jedisPool;

    @Autowired
    RedisConfig redisConfig;

    public Jedis getInstance(){
      Jedis jedis = new Jedis(redisConfig.getHostName(),
              redisConfig.getPort());
      return jedis;
    }

  @Override
  public ServiceResultDto set(ServiceRequestDto reqDto) {
    Map map = reqDto.getValues();
    String key = "";
    Object obj = null;
    if (null != map.get("key") && !"".equals(map.get("key").toString().trim())) {
      key = map.get("key").toString().trim();
    }
    if (null != map.get("obj")) {
      obj = map.get("obj");
    }
    Jedis jedis = getInstance();
    jedis.setex(key.getBytes(),600, SeriaUtil.serialize(obj));
    ServiceResultDto resultDto = ServiceResultDto.success();
    return resultDto;
  }

  @Override
  public ServiceResultDto get(ServiceRequestDto reqDto) {
    Map map = reqDto.getValues();
    String key = "";
    if (null != map.get("key") && !"".equals(map.get("key").toString().trim())) {
      key = map.get("key").toString().trim();
    }
    Jedis jedis = getInstance();
    byte[] bytes = jedis.get(key.getBytes());
    if (bytes == null) {
      return ServiceResultDto.error("2001", "账号未登录！");
    }
    Object obj = SeriaUtil.unserizlize(bytes);
    ServiceResultDto resultDto = ServiceResultDto.success();
    resultDto.set("obj", obj);
    return resultDto;
  }

  @Override
  public ServiceResultDto del(ServiceRequestDto reqDto) {
    Map map = reqDto.getValues();
    String key = "";
    if (null != map.get("key") && !"".equals(map.get("key").toString().trim())) {
      key = map.get("key").toString().trim();
    }
    Jedis jedis = getInstance();
    jedis.del(key.getBytes());
    ServiceResultDto resultDto = ServiceResultDto.success();
    return resultDto;
    }

}
