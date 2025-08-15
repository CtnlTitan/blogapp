package com.example.blogapp.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    void sendMailTest(){
        emailService.sendMail("ctnldevansh@gmail.com",
                            "Test Mail",
                            "This is mail service Testing");
    }
}
