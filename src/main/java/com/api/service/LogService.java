package com.api.service;

import com.api.common.sequence.SequenceGenerator;
import com.api.model.Log;
import com.api.repository.mongo.LogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogService {
    private LogRepository logRepository;
    private SequenceGenerator sequenceGenerator;

    public LogService(LogRepository logRepository, SequenceGenerator sequenceGenerator) {
        this.logRepository = logRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    private void logAdding(String data){
        long seq = sequenceGenerator.generateSequence(LogService.class);

        Log log = Log.builder()
                .id(String.valueOf(seq))
                .logMessage(data)
                .build();
        logRepository.save(log);
    }

    @KafkaListener(
            topics = {"${kafka.topic.create}", "${kafka.topic.read}", "${kafka.topic.update}", "${kafka.topic.delete}"},
            groupId = "${kafka.groupId}"
    )
    void listener(String data){
        logAdding(data);
    }

}
