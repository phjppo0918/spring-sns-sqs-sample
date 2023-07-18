package com.example.subscribeboardservice.aws;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SqsMessageListener {
    ObjectMapper objectMapper;
    @SqsListener(value = "${spring.cloud.aws.sqs.queue-name}")
    public void receive(String payload) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(payload);
        final String result = jsonNode.get("Message").asText();
        log.info("{}", result);
    }
}
