package com.kaka.cloud.impl;

import com.github.pagehelper.PageHelper;
import com.kaka.cloud.api.FileApi;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.entity.FileInfo;
import com.kaka.cloud.mapper.FileMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/30 14:33
 */
@Service("fileApi")
public class FileApiImpl implements FileApi{

  @Autowired
  private FileMapper fileMapper;

  @Override
  public ServiceResultDto uploadFile(ServiceRequestDto requestDto) {
    MultipartFile file = (MultipartFile)requestDto.get("file");
    String baseUrl = System.getProperty("catalina.home");
    String fileType = file.getContentType();
    String originFileName = file.getOriginalFilename();
    String fileName = file.getOriginalFilename().replace(".", UUID.randomUUID().toString() + ".");
    String realUrl = baseUrl + "/webapps/file/" + fileName;
    File filePath = new File(realUrl);
    try {
      file.transferTo(filePath);
    } catch (IOException e) {
      return ServiceResultDto.error("1005", "上传失败！");
    }

    String urlPath = "http://139.199.59.97:8081" + "/file/" + fileName;
    FileInfo fileInfo = new FileInfo(fileType, originFileName, urlPath);
    fileMapper.uploadFile(fileInfo);
    ServiceResultDto resultDto = ServiceResultDto.success();
    return resultDto;
  }

  @Override
  public ServiceResultDto queryFile(ServiceRequestDto requestDto) {
    ServiceResultDto serviceResultDto = ServiceResultDto.success();
    Map map = requestDto.getValues();
    Integer page = 1;
    Integer size = 10;
    String fileType = "";
    if (null != map.get("page") && !"".equals(map.get("page").toString().trim())) {
      page = Integer.parseInt(map.get("page").toString().trim());
    }
    if (null != map.get("size") && !"".equals(map.get("size").toString().trim())) {
      size = Integer.parseInt(map.get("size").toString().trim());
    }
    if (null != map.get("fileType") && !"".equals(map.get("fileType").toString().trim())) {
      fileType = map.get("fileType").toString().trim();
    }

    List<FileInfo> countList = fileMapper.queryFile(fileType);
    PageHelper.startPage(page, size);
    List<FileInfo> dataList = fileMapper.queryFile(fileType);

    int totalNum = countList.size();

    serviceResultDto.set("data", dataList);
    serviceResultDto.set("page", page);
    serviceResultDto.set("size", size);
    serviceResultDto.set("pages", (totalNum + size - 1) / size);
    serviceResultDto.set("total", totalNum);
    return serviceResultDto;
  }


}
