package com.example.kafka.producer;

import java.awt.EventQueue;

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

    private final JLabel messageSentLabel = new JLabel();

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

        setTitle("Kafka Producer Example");

        messageSentLabel.setText("Digite uma menssagem e clique em Enviar:");
        messageSentLabel.setBounds(10, 10, 270, 100);

        JLabel messageLabel = new JLabel();
        messageLabel.setText("Mensagem:");
        messageLabel.setBounds(10, 40, 90, 100);

        JTextField messageTextField = new JTextField();
        messageTextField.setBounds(105, 90, 270, 30);

        JButton sendMessageButton = new JButton("Enviar");
        sendMessageButton.setBounds(105, 130, 140, 40);

        add(messageSentLabel);
        add(messageLabel);
        add(messageTextField);
        add(sendMessageButton);
        setSize(400, 220);
        setLayout(null);
        setVisible(Boolean.TRUE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sendMessageButton.addActionListener((a) -> {

            String message = messageTextField.getText();

            if (message != null && !message.isEmpty()) {
                serviceMessage.enviaMensagem(messageTextField.getText());
                messageSentLabel.setText("Mensagem ".concat(message).concat(" enviada!"));
            } else {
                messageSentLabel.setText("Digite uma menssagem e clique em Enviar:");
            }
        });
    }
}
