package com.kaka.cloud.mapper;

import com.kaka.cloud.entity.Human;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 11:04
 */
public interface HumanMapper {

  List<Human> getHuman(@Param(value = "id") Integer id, @Param(value = "account") String account, @Param(value = "pwd") String pwd);

  void addHuman(@Param(value = "account") String account, @Param(value = "pwd") String pwd);
}
