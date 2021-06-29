package com.example.demo.entityvo.requestvo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@Data
public class LoginAndRegisterByEmailVO {

    @Email(message = "5101,不是邮箱类型", groups = {LoginAndRegisterByEmailVO.SendEmail.class, LoginAndRegisterByEmailVO.VerifyEmail.class})
    @NotBlank(message = "5102,邮箱不能为空", groups = {LoginAndRegisterByEmailVO.SendEmail.class, LoginAndRegisterByEmailVO.VerifyEmail.class})
    private String email;

    @NotNull(message = "5103,验证码不能为空", groups = {LoginAndRegisterByEmailVO.VerifyEmail.class})
    private Integer code;

    public interface SendEmail {

    }

    public interface VerifyEmail {

    }
}


