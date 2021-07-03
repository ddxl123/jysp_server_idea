package com.example.demo.controller.controllers.loginandregister;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.controller.requestvo.loginandregister.byemail.SendEmailRequestVO;
import com.example.demo.controller.requestvo.loginandregister.byemail.VerifyEmailRequestVO;
import com.example.demo.controller.responsevo.ResponseVO;
import com.example.demo.entity.EmailVerify;
import com.example.demo.entity.User;
import com.example.demo.service.impl.EmailVerifyServiceImpl;
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
    EmailVerifyServiceImpl emailVerifyServiceImpl;


    @PostMapping(path = "/send_email")
    @ApiOperation(
            value = "给用户发邮箱",
            notes = "101,发送失败，请重试！" + "<br>" +
                    "102,邮箱已发送, 请注意查收!"
    )
    public ResponseVO<Void> sendEmail(@RequestBody @Validated SendEmailRequestVO emailVO) {
        // 随机 5 位数的验证码
        int randomNum = (int) (Math.random() * 9000 + 1000);
        EmailVerify emailVerify = new EmailVerify()
                .setEmail(emailVO.getEmail())
                .setCode(randomNum);

        // 存在该 email 时更新，不存在时插入。
        // 会根据 emailVerify 中全部非空字段对数据表进行更新。
        boolean isSuccess = emailVerifyServiceImpl.saveOrUpdate(emailVerify,
                new UpdateWrapper<EmailVerify>().lambda()
                        .eq(EmailVerify::getEmail, emailVerify.getEmail()));

        // 即使以上操作必然会成功，也要防止不成功的情况。
        // 返回的成功与否并非程序异常(但此处可能为异常)。
        // 当仅仅是 update 时，也会返回 false，原因可能是没 find 到，而并非程序异常。
        if (!isSuccess) {
            return new ResponseVO<Void>(101, "发送失败，请重试！", null)
                    .outputLog("存储修改或存储数据失败，可能是 sql 内部异常。", null, logger, 1);

        }

        // 存储成功后，
        // 将邮箱发送成功的消息进行响应
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("验证码: " + randomNum);
        mailMessage.setTo(emailVO.getEmail());
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setText("验证码在标题上");
        javaMailSender.send(mailMessage);

        return new ResponseVO<>(102, "邮箱已发送, 请注意查收!", null);
    }

    @PostMapping(value = "/verify_email")
    public ResponseVO<User> verifyEmail(@RequestBody @Validated VerifyEmailRequestVO param) {
        ResponseVO<User> responseVO = new ResponseVO<>();
        responseVO.setCode(1000);
        responseVO.setMessage("注册/登陆成功");
        responseVO.setData(new User());
        return responseVO;
    }

}
