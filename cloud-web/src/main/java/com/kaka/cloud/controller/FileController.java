package com.kaka.cloud.controller;

import com.kaka.cloud.api.FileApi;
import com.kaka.cloud.common.KakaResultDto;
import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(文件上传测试)
 * @date 2018/7/30 14:21
 */
@Controller
@EnableAutoConfiguration
@CrossOrigin(value = "*" ,maxAge = 360)
@RequestMapping(value = "/file")
public class FileController {

  @Autowired
  private FileApi fileApi;

  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ServiceResultDto uploadFile(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return ServiceResultDto.error("1003", "文件不存在！");
    }
    int size = (int) file.getSize();
    if (size > 1024 * 1024 * 10) {
      return ServiceResultDto.error("1004", "文件过大！");
    }

    ServiceRequestDto reqDto = new ServiceRequestDto();
    reqDto.all().put("file", file);
    return fileApi.uploadFile(reqDto);
  }

  @RequestMapping(value = "/queryFile", method = RequestMethod.GET)
  @ResponseBody
  public KakaResultDto queryFile(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "fileType", required = false) String fileType) {
    ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
    serviceRequestDto.set("page", page);
    serviceRequestDto.set("size", limit);
    serviceRequestDto.set("fileType", fileType);
    ServiceResultDto serviceResultDto =  fileApi.queryFile(serviceRequestDto);

    KakaResultDto kakaResultDto = KakaResultDto.success();
    kakaResultDto.setCode(0);
    kakaResultDto.setMsg("");
    kakaResultDto.setCount(serviceResultDto.get("total", Integer.class));
    kakaResultDto.setData(serviceResultDto.get("data", List.class));
    return kakaResultDto;
  }
}
