package com.example.kafka.consumer;

import java.awt.EventQueue;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author eder.crespo
 */
@SpringBootApplication
public class KafkaConsumerApplication extends JFrame {

    private static final String TOPIC = "geral";

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(KafkaConsumerApplication.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            KafkaConsumerApplication application = context.getBean(KafkaConsumerApplication.class);
            application.setVisible(Boolean.FALSE);
        });
    }

    @KafkaListener(topics = TOPIC, containerFactory = "kafkaListenerContainerFactory", groupId = "group_json")
    public void getMessage(String message) {

        JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Kafka Consumer");
        dialog.setModal(false);
        dialog.setVisible(Boolean.TRUE);

        new Timer(4000, (e) -> {
            dialog.setVisible(Boolean.FALSE);
        }).start();
    }
}
