package com.example.demo.controller.controller.loginandregister;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.config.passwordconfig.Passworder;
import com.example.demo.constant.JwtConstant;
import com.example.demo.constant.code.Code1;
import com.example.demo.controller.requestvo.loginandregister.byemail.SendEmailRequestVO;
import com.example.demo.controller.requestvo.loginandregister.byemail.VerifyEmailRequestVO;
import com.example.demo.controller.responsevo.ResponseVO;
import com.example.demo.controller.responsevo.loginandregister.byemail.VerifyEmailResponseVO;
import com.example.demo.entity.EmailVerify;
import com.example.demo.entity.User;
import com.example.demo.jwt.Jwtter;
import com.example.demo.jwt.tokener.JwtGenerateTokener;
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
 * @author 10338
 */
@Data
@RestController
@AllArgsConstructor
@RequestMapping(
        path = JwtConstant.NO_JWT_URL + "/login_and_register_by_email",
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

    @ApiResponses({
            @ApiResponse(code = Code1.C1_01_01, message = "???????????????????????????"),
            @ApiResponse(code = Code1.C1_01_02, message = "???????????????, ???????????????!")
    })
    @PostMapping(path = "/send_email")
    @NonNull
    public ResponseVO<Void> sendEmail(@NonNull @RequestBody @Validated SendEmailRequestVO emailVO) {
        // ?????? 5 ??????????????????
        int randomNum = (int) (Math.random() * 9000 + 1000);

        // ???????????????
        boolean isSuccess = emailVerifyService.saveOrUpdate(
                new EmailVerify()
                        .setEmail(emailVO.getEmail())
                        .setCode(randomNum),
                new LambdaUpdateWrapper<EmailVerify>()
                        .set(EmailVerify::getCode, randomNum),
                EmailVerify::getEmail,
                emailVO.getEmail()
        );

        // ?????????????????????????????????????????????????????????????????????
        // ???????????????????????????????????????(????????????????????????)???
        // ???????????? update ?????????????????? false????????????????????? find ??????????????????????????????
        if (!isSuccess) {
            return new ResponseVO<Void>(Code1.C1_01_01, "???????????????????????????", null)
                    .outputLog("??????????????????????????????????????????????????? sql ???????????????", null, logger, 1);

        }

        // ??????????????????
        // ??????????????????????????????????????????
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("?????????: " + randomNum);
        mailMessage.setTo(emailVO.getEmail());
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setText("?????????????????????");
        javaMailSender.send(mailMessage);

        return new ResponseVO<>(Code1.C1_01_02, "???????????????, ???????????????!", null);
    }


    @ApiResponses({
            @ApiResponse(code = Code1.C1_02_01, message = "???????????????????????????????????????????????????"),
            @ApiResponse(code = Code1.C1_02_02, message = "???????????????"),
            @ApiResponse(code = Code1.C1_02_03, message = "????????????????????????????????????"),
            @ApiResponse(code = Code1.C1_02_04, message = "???????????????????????????????????????????????????"),
            @ApiResponse(code = Code1.C1_02_05, message = "???????????????"),
            @ApiResponse(code = Code1.C1_02_06, message = "??????????????????????????????????????????"),
            @ApiResponse(code = Code1.C1_02_07, message = "??????????????????")
    })
    @PostMapping(value = "/verify_email")
    @NonNull
    public ResponseVO<VerifyEmailResponseVO> verifyEmail(@NonNull @RequestBody @Validated VerifyEmailRequestVO param) {
        // ??????????????????????????????????????????????????????????????????
        boolean isExists = emailVerifyService.lambdaQuery()
                .eq(EmailVerify::getEmail, param.getEmail())
                .eq(EmailVerify::getCode, param.getCode())
                .exists();

        // ????????????????????????
        if (isExists) {
            // ??????????????????????????????
            List<User> users = userService.lambdaQuery()
                    .eq(User::getEmail, param.getEmail())
                    .list();
            int userSize = users.size();

            // ?????????????????? ?????? ????????????
            if (userSize == 0) {

                // ????????????
                User newUser = new User()
                        .setAge(0)
                        .setEmail(param.getEmail())
                        .setUsername("??????????????????")
                        .setPassword(passworder.generateRandomPassword(16, true));// ??????????????????????????????

                // ?????? Token
                JwtGenerateTokener jwtGenerateTokener = new Jwtter().generateToken(newUser);
                if (jwtGenerateTokener.getJwtExceptionPackage().hasVerifyException()) {
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(Code1.C1_02_01)
                            .setMessage("???????????????????????????????????????????????????")
                            .setData(null)
                            .outputLog(
                                    "?????? Token ?????????",
                                    jwtGenerateTokener.getJwtExceptionPackage().getExistGenerateException(),
                                    logger,
                                    2);
                }

                // ????????????
                boolean saveResult = userService.save(newUser);
                if (saveResult) {
                    // ????????????
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(Code1.C1_02_02)
                            .setMessage("???????????????")
                            .setData(new VerifyEmailResponseVO()
                                    .setUserId(newUser.getId())
                                    .setUsername(newUser.getUsername())
                                    .setEmail(newUser.getEmail())
                                    .setAge(newUser.getAge())
                                    .setToken(jwtGenerateTokener.getToken())
                            );
                } else {
                    // ????????????
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(Code1.C1_02_03)
                            .setMessage("????????????????????????????????????")
                            .setData(null)
                            .outputLog("?????????????????????????????? false???", null, logger, 2);
                }
            }

            // ??????????????????????????? ?????? ????????????
            else if (userSize == 1) {
                User user = users.get(0);
                // ?????? Token
                JwtGenerateTokener jwtGenerateTokener = new Jwtter().generateToken(user);
                if (jwtGenerateTokener.getJwtExceptionPackage().hasGenerateException()) {
                    return new ResponseVO<VerifyEmailResponseVO>()
                            .setCode(Code1.C1_02_04)
                            .setMessage("???????????????????????????????????????????????????")
                            .setData(null)
                            .outputLog(
                                    "?????? Token ?????????",
                                    jwtGenerateTokener.getJwtExceptionPackage().getExistGenerateException(),
                                    logger,
                                    2);
                }

                return new ResponseVO<VerifyEmailResponseVO>()
                        .setCode(Code1.C1_02_05)
                        .setMessage("???????????????")
                        .setData(new VerifyEmailResponseVO()
                                .setUserId(user.getId())
                                .setUsername(user.getUsername())
                                .setEmail(user.getEmail())
                                .setAge(user.getAge())
                                .setToken(jwtGenerateTokener.getToken())
                        );
            }

            // ????????????????????? ?????? ??????
            else {
                return new ResponseVO<VerifyEmailResponseVO>()
                        .setCode(Code1.C1_02_06)
                        .setMessage("??????????????????????????????????????????")
                        .setData(null)
                        .outputLog("???????????????????????????", null, logger, 2);
            }
        }

        // ????????????????????????
        else {
            return new ResponseVO<VerifyEmailResponseVO>()
                    .setCode(Code1.C1_02_07)
                    .setMessage("??????????????????")
                    .setData(null);
        }
    }

}
