package com.kaka.cloud.config.diconf;

import com.baidu.disconf.client.config.DisClientConfig;
import com.baidu.disconf.client.config.DisClientSysConfig;
import com.baidu.disconf.client.fetcher.FetcherFactory;
import com.baidu.disconf.client.fetcher.FetcherMgr;
import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;
import com.baidu.disconf.core.common.path.DisconfWebPathMgr;
import com.kaka.cloud.disconf.WebCommonCst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Title: ErrorMessageConfig.java
 * @Package com.iboxpay.mbp.basic.disconf
 * @Description: TODO(用一句话描述该文件做什么)
 * @author fangjun  
 * @date 2017年8月22日 下午6:26:18
 * @version V1.0
 */
@Service
public class ErrorMessageConfig {

    private Logger log = LoggerFactory.getLogger(ErrorMessageConfig.class);

    public void initErrorMap() {

        FetcherMgr fetcherMgr = null;
        String fileName = WebCommonCst.CR.get("disconf.error_file");
        try {

            String classpath = ErrorMessageConfig.class.getClassLoader().getResource("").getPath();
            DisClientSysConfig sysConfig = DisClientSysConfig.getInstance();
            DisClientConfig config = DisClientConfig.getInstance();
            fetcherMgr = FetcherFactory.getFetcherMgr();
            String url = DisconfWebPathMgr.getRemoteUrlParameter(sysConfig.CONF_SERVER_STORE_ACTION, config.APP,
                   config.VERSION, config.ENV, fileName, DisConfigTypeEnum.FILE);
            fetcherMgr.downloadFileFromServer(url, fileName,classpath);

        } catch (Exception e) {
            log.error("load failed "+fileName,e);
            e.printStackTrace();
        } finally {
            fetcherMgr.release();
        }

    }


}
