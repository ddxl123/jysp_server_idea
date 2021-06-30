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
    @Email(message = "5003,不是邮箱类型")
    @NotBlank(message = "5004,邮箱不能为空")
    @ApiModelProperty(
            value = "5003,不是邮箱类型" + "<br>" +
                    "5004,邮箱不能为空")
    private String email;

    /**
     * 邮箱验证码
     */
    @NotNull(message = "5005,验证码不能为空")
    @ApiModelProperty(value = "5005,验证码不能为空")
    private Integer code;
}



