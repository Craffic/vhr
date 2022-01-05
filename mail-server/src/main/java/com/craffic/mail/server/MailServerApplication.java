package com.craffic.mail.server;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailServerApplication.class, args);
        System.out.println("mail server启动完成......");
    }

    @Bean
    Queue queue(){
        return new Queue("Craffic.mail.welcome");
    }
}
