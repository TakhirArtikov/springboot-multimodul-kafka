package com.kafka.consumer;

import com.kafka.consumer.entity.Changes;
import com.kafka.consumer.repository.EventDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    private EventDataRepository repository;

    public KafkaConsumer(EventDataRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "changes",
            groupId = "myGroup")
    public void consume(String message){
    log.info("Message received:[{}]" + message);
        Changes change=new Changes();

        change.setChanges(message);
        repository.save(change);
    }
}
