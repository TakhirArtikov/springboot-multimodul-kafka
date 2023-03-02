package com.kafka.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ChangeProducer {

    private KafkaTemplate<String,String> kafkaTemplate;

    public ChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topicName="changes";

        EventHandler eventHandler=new ChangeHandler(kafkaTemplate,topicName);
        String url="https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder=new EventSource.Builder(eventHandler, URI.create(url));

        EventSource eventSource=builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(1);

    }
}
