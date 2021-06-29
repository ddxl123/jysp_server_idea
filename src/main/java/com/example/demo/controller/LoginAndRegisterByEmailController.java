package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entityvo.requestvo.LoginAndRegisterByEmailVO;
import com.example.demo.entityvo.responsevo.ResponseVO;
import com.example.demo.entityvo.responsevo.ResponseWithLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 10338
 */
@Data
@RestController
@RequestMapping(path = "/login_and_register_by_email")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class LoginAndRegisterByEmailController {

    JavaMailSender javaMailSender;
    MailProperties mailProperties;
    Logger logger;

    @PostMapping(value = "/send_email")
    public ResponseVO sendEmail(@Validated(value = LoginAndRegisterByEmailVO.SendEmail.class) LoginAndRegisterByEmailVO param) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("这是标题");
            mailMessage.setTo(param.getEmail());
            mailMessage.setFrom(mailProperties.getUsername());
            mailMessage.setText("这是正文");
            javaMailSender.send(mailMessage);
        } catch (Throwable throwable) {
            return new ResponseVO(1001, "邮箱发送异常", null)
                    .responseWithLog(new ResponseWithLog(logger, true, throwable));
        }
        return new ResponseVO(1000, "邮箱已发送, 请注意查收!", null);
    }

    @PostMapping(value = "/verify_email")
    public ResponseVO verifyEmail(@Validated(value = LoginAndRegisterByEmailVO.VerifyEmail.class) LoginAndRegisterByEmailVO param) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(1000);
        responseVO.setMessage("注册/登陆成功");
        responseVO.setData(new User());
        return responseVO;
    }

}
