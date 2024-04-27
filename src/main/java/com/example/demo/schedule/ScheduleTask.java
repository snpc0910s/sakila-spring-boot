package com.example.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ScheduleTask {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(cron="0 */1 * * * ?")
    public void scheduleTaskCallMeEveryMinute() {
        log.info("Execute time: {} :  Every minute",dateTimeFormatter.format(LocalDateTime.now()));
    }
//    @Scheduled(cron="*/10 * * * * ?")
//    public void scheduleTaskCallMeTenSecond() {
//        log.info("Execute time: {} : Every ten second",dateTimeFormatter.format(LocalDateTime.now()));
//    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
