package com.acui.springboot04task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot04TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知-今晚开会");
        message.setText("今晚7：30开会");

        message.setTo("17603209617@163.com");
        message.setFrom("xinfeng.cui@foxmail.com");

        mailSender.send(message);
    }

    @Test
    public void test02() throws Exception{
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今晚7：30开会<b/>", true);

        helper.setTo("17603209617@163.com");
        helper.setFrom("xinfeng.cui@foxmail.com");

        //上传文件
        helper.addAttachment("wallhaven-q61w25_2560x1600.png",new File("D:\\Users\\acui\\Pictures\\wallpaper\\wallhaven-q61w25_2560x1600.png"));
        helper.addAttachment("wallhaven-g8oee3_2560x1600.png",new File("D:\\Users\\acui\\Pictures\\wallpaper\\wallhaven-g8oee3_2560x1600.png"));

        mailSender.send(mimeMessage);
    }
}
