package com.ybj.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author sendMail
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Api(value = "发送邮件", tags={"用于发送邮件"})
@RestController
public class SendMail {

    @Autowired
    JavaMailSender javaMailSender;

    public static final String pexelApiKey = "563492ad6f91700001000001b5fd4659e7774dc1b41e8d055e043a2a";


    @GetMapping("/sendMail")
    @ApiOperation(value = "发送邮件测试",notes = "发送简单邮件")
    public void sendTextMail(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("312ybj@Gmail.com");
        msg.setFrom("1793870688@qq.com");
        msg.setSubject("springboot test mail");
        msg.setText("hello this mail is from springboot");
        javaMailSender.send(msg);
    }


    @GetMapping("/sendHtmlMail")
    public void sendHtmlMail() throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        msg.setFrom("1793870688@qq.com");
        helper.setTo("312ybj@Gmail.com");
        helper.setSubject("Testing from Spring Boot");
        helper.setText("<h1>Check attachment for image!</h1>", true);

        URL photoUrl = new URL("https://images.pexels.com/photos/3586911/pexels-photo-3586911.jpeg");
        URLConnection conn = photoUrl.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
        conn.setRequestProperty("Authorization", pexelApiKey);
        conn.connect();
        InputStream inputStream =  conn.getInputStream();
        helper.addAttachment("this is a photo",new ByteArrayResource(IOUtils.toByteArray(inputStream)));
        javaMailSender.send(msg);
    }


}
