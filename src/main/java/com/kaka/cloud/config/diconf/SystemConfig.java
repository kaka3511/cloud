package com.kaka.cloud.config.diconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import org.springframework.stereotype.Service;

@Service
@DisconfFile(filename = "system.properties")
public class SystemConfig {


}
