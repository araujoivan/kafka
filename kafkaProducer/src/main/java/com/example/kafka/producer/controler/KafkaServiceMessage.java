package com.example.kafka.producer.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author eder.crespo
 */
@Service
public class KafkaServiceMessage {
    
    @Autowired 
    private KafkaTemplate<String, String> template;
    private static final String TOPIC = "geral";
    
    public void enviaMensagem(String mensagem) {
        template.send(TOPIC, mensagem);
    } 
}
