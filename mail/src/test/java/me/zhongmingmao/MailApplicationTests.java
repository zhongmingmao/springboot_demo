package me.zhongmingmao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {
    
    private static final String ACCOUNT = "springboot_test@163.com";
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Ignore
    @Test
    public void sendSimpleMailTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ACCOUNT);
        message.setTo(ACCOUNT);
        message.setSubject("Simple Mail");
        message.setText("SimpleMail Content");
        mailSender.send(message);
    }
    
    @Ignore
    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        String relativePath = "/attachment/Java9.jpg";
        String absPath = this.getClass().getResource(relativePath).getPath();
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(ACCOUNT);
        helper.setTo(ACCOUNT);
        helper.setSubject("Attachment Mail: Java9.jpg");
        helper.setText("AttachmentMail Content");
        helper.addAttachment("Java9.jpg",
                new FileSystemResource(new File(absPath)));
        mailSender.send(mimeMessage);
    }
    
    @Ignore
    @Test
    public void sendTemplateMailTest() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(ACCOUNT);
        helper.setTo(ACCOUNT);
        helper.setSubject("Template Mail");
        Context context = new Context();
        context.setVariable("username", "zhongmingmao");
        String text = templateEngine.process("emailTemplate", context);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }
}
