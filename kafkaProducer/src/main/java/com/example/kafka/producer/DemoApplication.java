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
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            DemoApplication ex = context.getBean(DemoApplication.class);
            ex.setVisible(true);
        });
    }

    private void initUI() {

        JFrame f = new JFrame("Kafka Producer Example");

        JButton submitButton = new JButton("Enviar");
        submitButton.setBounds(100, 100, 140, 40);

        JLabel label = new JLabel();
        label.setText("Digite o texto :");
        label.setBounds(10, 10, 100, 100);

        JLabel label1 = new JLabel();
        label1.setBounds(10, 110, 200, 100);

        JTextField textfield = new JTextField();
        textfield.setBounds(110, 50, 130, 30);

        f.add(label1);
        f.add(textfield);
        f.add(label);
        f.add(submitButton);
        f.setSize(300, 300);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener((ActionEvent arg) -> {
            label1.setText("Mensagem enviada!");
            serviceMessage.enviaMensagem(textfield.getText());
            textfield.setText("");
        });
    }
}
