package com.example.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Context;
import java.util.Set;

@Controller
public class GreetingController {

    public static ConfigurableApplicationContext contextMain;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/jms")
    public String jms(Model model) {
        Receiver receiver = contextMain.getBean(Receiver.class);
        model.addAttribute("mails", receiver.emails);
        return "jms";
    }

    public void jmsCalls(ConfigurableApplicationContext context) throws InterruptedException {
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        contextMain = context;

        Set<Thread> threads2 = Thread.getAllStackTraces().keySet();
        System.out.println("Aantal threads: " + threads2.size());
        threads2.forEach(t -> System.out.println(t.getName()));

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending email messages.");

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            jmsTemplate.convertAndSend("mailbox", new Email(i + "@example.com", "Hello"));
        }
    }
}
