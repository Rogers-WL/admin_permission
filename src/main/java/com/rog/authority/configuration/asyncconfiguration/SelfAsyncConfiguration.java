package com.rog.authority.configuration.asyncconfiguration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 14:38
 **/
@SpringBootConfiguration
public class SelfAsyncConfiguration {
    @Bean(name = "MyAsync")
    public ThreadPoolTaskExecutor myThread(){
        ThreadPoolTaskExecutor taskPoll = new ThreadPoolTaskExecutor();
        //核心线程数
        taskPoll.setCorePoolSize(2);
        //线程池维护的最大数量
        taskPoll.setMaxPoolSize(6);
        //缓存队列。队满是申请增加核心线程数量
        taskPoll.setQueueCapacity(2);
        //线程存活时间，超出核心线程池的空闲进程会销毁
        taskPoll.setKeepAliveSeconds(100);
        taskPoll.setThreadGroupName("gro");
        taskPoll.setThreadNamePrefix("tas-");

        //缓存队列满切线程数目达到最大是采用拒绝策略
        //abort:丢弃任务并抛出异常
        //discard:丢弃任务但不抛出异常
        //discardOldes:丢弃最新的任务，重新执行此任务（重复此过程）
        //CallerRuns:重新添加当前任务，自动重复调用execute方法直至成功
        taskPoll.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        taskPoll.initialize();
        return taskPoll;

    }
}