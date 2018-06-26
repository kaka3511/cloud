package com.kaka.cloud.config.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.kaka.cloud.util.MapperUtils;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/5/4 10:21
 */
@Configuration
@Order(3)
@MapperScan(basePackages = "com.kaka.cloud.mapper", sqlSessionTemplateRef = "kakaSqlSessionTemplate")
public class DBConfig {
  @Autowired
  Db config;

  @Bean(name = "kakaDataSource", initMethod = "init", destroyMethod = "close", autowire = Autowire.BY_NAME)
  public DataSource testDataSource() {
    Map<String, String> dsMap = MapperUtils.toMap(MapperUtils
            .toJson(config));
    try {
      DataSource dataSource = DruidDataSourceFactory
              .createDataSource(dsMap);
      return dataSource;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Bean(name = "kakaSqlSessionFactory")
  @Primary
  public SqlSessionFactory testSqlSessionFactory(
          @Qualifier("kakaDataSource") DataSource dataSource)
          throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources("classpath:/mapper/*.xml"));
    return bean.getObject();
  }

  @Bean(name = "kakaTransactionManager")
  public DataSourceTransactionManager testTransactionManager(
          @Qualifier("kakaDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "kakaSqlSessionTemplate")
  @Primary
  public SqlSessionTemplate testSqlSessionTemplate(
          @Qualifier("kakaSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
          throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
