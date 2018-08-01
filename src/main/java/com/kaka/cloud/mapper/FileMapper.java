package com.kaka.cloud.mapper;

import com.kaka.cloud.entity.FileInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/30 15:25
 */
public interface FileMapper {

  void uploadFile(FileInfo fileInfo);

  List<FileInfo> queryFile(@Param(value="fileType") String fileType);
}
