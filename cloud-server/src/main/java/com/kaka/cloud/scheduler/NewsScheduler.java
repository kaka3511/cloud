package com.kaka.cloud.scheduler;

import com.kaka.cloud.api.NewsApi;
import com.kaka.cloud.common.ServiceRequestDto;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/7/25 10:43
 */
@EnableScheduling
@Component
public class NewsScheduler implements SchedulingConfigurer {
  //一分钟执行一次
  private final static String CRON_DEFAULT = "0 0/60 * * * ?";

  @Autowired
  private NewsApi newsApi;

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.addTriggerTask(new Runnable() {
      @Override
      public void run() {
        newsApi.updateNews(new ServiceRequestDto());
      }
    }, new Trigger() {
      @Override
      public Date nextExecutionTime(TriggerContext triggerContext) {
        // 任务触发，可修改任务的执行周期
        Date nextExec = null;
        try {
          CronTrigger trigger = new CronTrigger(CRON_DEFAULT);
          nextExec = trigger.nextExecutionTime(triggerContext);
        } catch (Exception e) {
          CronTrigger trigger = new CronTrigger(CRON_DEFAULT);
          nextExec = trigger.nextExecutionTime(triggerContext);
        }
        return nextExec;
      }
    });
  }
}
