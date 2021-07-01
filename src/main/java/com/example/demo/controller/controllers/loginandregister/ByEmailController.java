package com.example.demo.controller.controllers.loginandregister;

import com.example.demo.controller.requestvo.loginandregister.byemail.SendEmailRequestVO;
import com.example.demo.controller.requestvo.loginandregister.byemail.VerifyEmailRequestVO;
import com.example.demo.controller.responsevo.ResponseVO;
import com.example.demo.controller.responsevo.ResponseWithLog;
import com.example.demo.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

/**
 * @author 10338
 */
@Data
@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping(
        path = "/login_and_register_by_email",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ByEmailController {

    JavaMailSender javaMailSender;
    MailProperties mailProperties;
    Logger logger;


    @PostMapping(path = "/send_email")
    @ApiOperation(value = "给用户发邮箱",
            notes = "1001,邮箱发送异常" + "<br>" +
                    "1000,邮箱已发送, 请注意查收!")
    public ResponseVO<Void> sendEmail(@RequestBody @Validated @Email SendEmailRequestVO email) {
        try {
            // 随机 5 位数的验证码
            int randomNum = (int) (Math.random() * 9000 + 1000);

            // 将随机验证码存入数据库


            // 将邮箱发送成功的消息进行响应
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码: " + randomNum);
            mailMessage.setTo(email.getEmail());
            mailMessage.setFrom(mailProperties.getUsername());
            mailMessage.setText("验证码在标题上");
            javaMailSender.send(mailMessage);
        } catch (Throwable throwable) {
            return new ResponseVO<Void>(1001, "邮箱发送异常", null)
                    .outputLog(new ResponseWithLog(logger, true, throwable));
        }
        return new ResponseVO<>(1000, "邮箱已发送, 请注意查收!", null);
    }

    @PostMapping(value = "/verify_email")
    public ResponseVO<User> verifyEmail(@Validated VerifyEmailRequestVO param) {
        ResponseVO<User> responseVO = new ResponseVO<>();
        responseVO.setCode(1000);
        responseVO.setMessage("注册/登陆成功");
        responseVO.setData(new User());
        return responseVO;
    }

}
