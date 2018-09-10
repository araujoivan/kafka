/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.kafka.producer.controler;

import com.jabrains.kafkamodel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macbook
 */
@RestController
@RequestMapping("kafka")
public class UserController {
    
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
    private static final String TOPIC = "userBio";
    
    @GetMapping("/publish/{name}/{age}")
    public String post(@PathVariable("name") final String name, @PathVariable("age") final Integer age) {
        kafkaTemplate.send(TOPIC, new User(name, age));
        return "Published Succefully";
    } 
    
}
