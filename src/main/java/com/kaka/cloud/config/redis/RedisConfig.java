package com.kaka.cloud.config.redis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 9:40
 */

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "redis", locations = "classpath:redis.properties")
public class RedisConfig {


  private String hostName;

  private int port;

  private String password;

  private int timeout;

  @Bean
  public JedisPoolConfig getRedisConfig(){
    JedisPoolConfig config = new JedisPoolConfig();
    return config;
  }

  @Bean
  public JedisPool getJedisPool(){
    JedisPoolConfig config = getRedisConfig();
    JedisPool pool = new JedisPool(config,hostName,port,timeout,password);
    return pool;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }
}