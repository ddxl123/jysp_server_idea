package com.example.demo.controller.requestvo.loginandregister.byemail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@Data
public class VerifyEmailRequestVO {

    /**
     * 邮箱
     */
    @ApiModelProperty(
            value = "5100,邮箱格式不正确!" + "<br>" +
                    "5101,邮箱不能为空!"
    )
    @Email(message = "5100,邮箱格式不正确!")
    @NotBlank(message = "5101,邮箱不能为空!")
    private String email;

    /**
     * 邮箱验证码
     */
    @ApiModelProperty(value = "5102,验证码不能为空")
    @NotNull(message = "5102,验证码不能为空")
    private Integer code;
}



