/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbook
 */
@Service
public class KafkaConsumerService {
    
    @KafkaListener(topics = "geral", containerFactory = "kafkaListenerContainerFactory", groupId = "group_json")
    public void consumeJson(Stri user) {
        System.out.println(user);
    }
}
