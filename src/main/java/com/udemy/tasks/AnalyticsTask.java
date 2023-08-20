package com.udemy.tasks;

import com.udemy.service.UserService;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton

//causes lombok to generate a logger field

@Slf4j
public class AnalyticsTask {
    
    private final UserService userService;
    

    public AnalyticsTask(UserService userService) {
        this.userService = userService;
    }


    //this annotation provides us a way to schedule the tasks 
    //We can give initial delay if we want Also we can do fixed delay 
    //in our case we will have to give fixed delay as we want this task to run after every 5 seconds

    ///Information : THere is a problem using @scheduled for example our project will run on many different servers what will have is every 5 seconds for example we will send (n = number of servers) emails to every single user and this will create a huge problem thats why we will try to solve this problem using Shedlock that only 1 server execute the scheduled tasks 
    @Scheduled(fixedDelay = "5s")
    void execute(){
        long userCount = userService.getUserCount();
        //to display it since its void we will log it thats why we added the annotation of the logger above
        log.info("User Count is:{}",userCount);
    }
}
