package com.example.kafka.producer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import com.example.kafka.producer.controler.KafkaServiceMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author eder.crespo
 */
@SpringBootApplication
public class DemoApplication extends JFrame {

    @Autowired
    private KafkaServiceMessage serviceMessage;
    
    public DemoApplication() {
        initUI();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(DemoApplication.class)
                .headless(false)
                .run(args);

        EventQueue.invokeLater(() -> {
            DemoApplication ex = context.getBean(DemoApplication.class);
            ex.setVisible(true);
        });
    }

    private void initUI() {

        JFrame frame = new JFrame("Kafka Producer");

        JButton submitButton = new JButton("Enviar");
        submitButton.setBounds(100, 100, 140, 40);

        JLabel label = new JLabel();
        label.setText("Digite a mensagem :");
        label.setBounds(10, 10, 100, 100);

        JTextField textfield = new JTextField();
        textfield.setBounds(110, 50, 130, 30);

        frame.add(textfield);
        frame.add(label);
        frame.add(submitButton);
        frame.setSize(350, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener((ActionEvent arg) -> {
            serviceMessage.enviaMensagem(textfield.getText());
            textfield.setText("");
        });
    }
}
