package com.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic dbCreate(){
        return TopicBuilder.name("dbCreate")
                .build();
    }
    @Bean
    public NewTopic dbRead(){
        return TopicBuilder.name("dbRead")
                .build();
    }
    @Bean
    public NewTopic dbUpdate(){
        return TopicBuilder.name("dbUpdate")
                .build();
    }
    @Bean
    public NewTopic dbDelete(){
        return TopicBuilder.name("dbDelete")
                .build();
    }

}
