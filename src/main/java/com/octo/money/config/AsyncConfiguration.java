package com.octo.money.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import com.octo.money.async.ExceptionHandlingAsyncTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer, EnvironmentAware {

    private final Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "async.");
    }

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        log.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(propertyResolver.getProperty("corePoolSize", Integer.class, 2));
        executor.setMaxPoolSize(propertyResolver.getProperty("maxPoolSize", Integer.class, 50));
        executor.setQueueCapacity(propertyResolver.getProperty("queueCapacity", Integer.class, 10000));
        executor.setThreadNamePrefix("money-Executor-");
        return new ExceptionHandlingAsyncTaskExecutor(executor);
    }
}