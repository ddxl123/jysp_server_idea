package com.example.demo.controller.controller.loginandregister;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.config.passwordconfig.Passworder;
import com.example.demo.controller.requestvo.loginandregister.byemail.SendEmailRequestVO;
import com.example.demo.controller.requestvo.loginandregister.byemail.VerifyEmailRequestVO;
import com.example.demo.controller.responsevo.ResponseVO;
import com.example.demo.controller.responsevo.loginandregister.byemail.VerifyEmailResponseVO;
import com.example.demo.entity.EmailVerify;
import com.example.demo.entity.User;
import com.example.demo.jwt.Jwtter;
import com.example.demo.service.impl.EmailVerifyServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类响应码 --- 1010000
 *
 * @author 10338
 */
@Data
@RestController
@AllArgsConstructor
@RequestMapping(
        path = "/login_and_register_by_email",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ByEmailController {

    JavaMailSender javaMailSender;
    MailProperties mailProperties;
    Logger logger;
    EmailVerifyServiceImpl emailVerifyService;
    UserServiceImpl userService;
    Passworder passworder;

    /**
     * 方法响应码 --- 1010100
     */
    @ApiResponses({
            @ApiResponse(code = 101, message = "发送失败，请重试！"),
            @ApiResponse(code = 102, message = "邮箱已发送, 请注意查收!")
    })
    @PostMapping(path = "/send_email")
    @NonNull
    public ResponseVO<Void> sendEmail(@NonNull @RequestBody @Validated SendEmailRequestVO emailVO) {
        // 随机 5 位数的验证码
        int randomNum = (int) (Math.random() * 9000 + 1000);

        // 插入或更新
        boolean isSuccess = emailVerifyService.saveOrUpdate(
                new EmailVerify()
                        .setEmail(emailVO.getEmail())
                        .setCode(randomNum),
                new LambdaUpdateWrapper<EmailVerify>()
                        .set(EmailVerify::getCode, randomNum),
                EmailVerify::getEmail,
                emailVO.getEmail()
        );

        // 即使以上操作必然会成功，也要防止不成功的情况。
        // 返回的成功与否并非程序异常(但此处可能为异常)。
        // 当仅仅是 update 时，也会返回 false，原因可能是没 find 到，而并非程序异常。
        if (!isSuccess) {
            return new ResponseVO<Void>(101, "发送失败，请重试！", null)
                    .outputLog("存储修改或存储数据失败，此处可能是 sql 内部异常。", null, logger, 1);

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


    /**
     * 方法响应码 --- 1010200
     */
    @ApiResponses({
            @ApiResponse(code = 103, message = "注册成功"),
            @ApiResponse(code = 104, message = "登陆成功"),
            @ApiResponse(code = 105, message = "邮箱重复异常，请联系管理员！"),
            @ApiResponse(code = 106, message = "验证码错误！")
    })
    @PostMapping(value = "/verify_email")
    @NonNull
    public ResponseVO<VerifyEmailResponseVO> verifyEmail(@NonNull @RequestBody @Validated VerifyEmailRequestVO param) {
        // 查找数据库是否存在该邮箱以及相匹配的验证码。
        boolean isExists = emailVerifyService.lambdaQuery()
                .eq(EmailVerify::getEmail, param.getEmail())
                .eq(EmailVerify::getCode, param.getCode())
                .exists();

        // 若邮箱验证码正确
        if (isExists) {
            // 先查询是否存在该用户
            List<User> users = userService.lambdaQuery()
                    .eq(User::getEmail, param.getEmail())
                    .list();
            int userSize = users.size();

            // 不存在该邮箱 —— 注册用户
            if (userSize == 0) {

                // 新建用户
                User newUser = new User()
                        .setAge(0)
                        .setEmail(param.getEmail())
                        .setUsername("还没有起名字")
                        .setPassword(passworder.generateRandomPassword(16, true));// 生成随机加密过的密码

                // 生成 Token
                Jwtter jwtter = new Jwtter();
                boolean isToken = jwtter.generateToken(newUser);
                if (!isToken) {
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(103)
                            .setMessage("注册失败，请重新尝试或联系管理员！")
                            .setData(null)
                            .outputLog("生成 Token 异常！", jwtter.getJwtToken().getThrowable(), logger, 2);
                }

                // 存储用户
                boolean saveResult = userService.save(newUser);
                if (saveResult) {
                    // 存储成功
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(103)
                            .setMessage("注册成功！")
                            .setData(new VerifyEmailResponseVO()
                                    .setUserId(newUser.getId())
                                    .setUsername(newUser.getUsername())
                                    .setEmail(newUser.getEmail())
                                    .setAge(newUser.getAge())
                                    .setToken(jwtter.getJwtToken().getToken())
                            );
                } else {
                    // 存储失败
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(104)
                            .setMessage("注册失败，请联系管理员！")
                            .setData(null)
                            .outputLog("存储新用户返回结果为 false！", null, logger, 2);
                }
            }

            // 存在唯一一个该邮箱 —— 登陆用户
            else if (userSize == 1) {
                User user = users.get(0);
                Jwtter jwtter = new Jwtter();
                boolean isToken = jwtter.generateToken(user);
                if (!isToken) {
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(103)
                            .setMessage("登陆失败，请重新尝试或联系管理员！")
                            .setData(null)
                            .outputLog("生成 Token 异常！", jwtter.getJwtToken().getThrowable(), logger, 2);
                }
                return new ResponseVO<VerifyEmailResponseVO>()
                        .setCode(104)
                        .setMessage("登陆成功！")
                        .setData(new VerifyEmailResponseVO()
                                .setUserId(user.getId())
                                .setUsername(user.getUsername())
                                .setEmail(user.getEmail())
                                .setAge(user.getAge())
                                .setToken(jwtter.getJwtToken().getToken())
                        );
            }

            // 存在多个该邮箱 —— 异常
            else {
                return new ResponseVO<VerifyEmailResponseVO>()
                        .setCode(105)
                        .setMessage("邮箱重复异常，请联系管理员！")
                        .setData(null)
                        .outputLog("数据库存在多个邮箱", null, logger, 2);
            }
        }

        // 若邮箱验证码错误
        else {
            return new ResponseVO<VerifyEmailResponseVO>()
                    .setCode(106)
                    .setMessage("验证码错误！")
                    .setData(null);
        }
    }

}
