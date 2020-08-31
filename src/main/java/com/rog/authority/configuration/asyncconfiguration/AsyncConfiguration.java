package com.rog.authority.configuration.asyncconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 14:37
 **/
@SpringBootConfiguration
@Slf4j
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setKeepAliveSeconds(1000);
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("base");
        //(Runnable r, ThreadPoolExecutor exe) ->{}lambda表达式
        executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exe) -> {
            log.warn("当前任务线程池队列已满");
        });
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            //Object... 可变参数，本质是数组的引用地址，只能有一个，必须放到末尾
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                log.error(throwable.getMessage());
                log.error("线程池执行任务发生未知异常" + method.getName());
                for (Object param : objects) {
                    log.info("Parameter value - " + param);
                }
            }
        };
    }
}

